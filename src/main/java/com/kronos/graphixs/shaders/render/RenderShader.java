package com.kronos.graphixs.shaders.render;

import static org.lwjgl.opengl.GL43C.GL_BUFFER_BINDING;
import static org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BLOCK;
import static org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BUFFER;
import static org.lwjgl.opengl.GL43C.glGetProgramResourceIndex;
import static org.lwjgl.opengl.GL43C.glGetProgramResourceiv;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.graphixs.Light;
import com.kronos.graphixs.shaders.BaseShader;
import com.kronos.graphixs.shaders.ShaderUtils;
import com.kronos.graphixs.shaders.bufferobjects.BufferObject;

public abstract class RenderShader extends BaseShader {
	public String vertexSource, fragmentSource;

	private int vertexId = -1, fragmentId = -1;
	ArrayList<ShaderAttribute> attributes;

	public RenderShader(String vs, String fs) {
		this.vertexSource = vs;
		this.fragmentSource = fs;
		attributes = new ArrayList<>();

	}

	@Override
	public abstract void setAttribs();

	public void addAttribute(String attributeName, int loc) {
		attributes.add(new ShaderAttribute(attributeName, loc));
	}

	public int getAttribute(String name) {
		return GL20.glGetAttribLocation(programId, name);
	}

	public void appendVectorArray(Vector3f[] vects, String name, int loc) {
		Kronos.debug.getLogger().debug("Binding: {} To programid: {} at location: {}", name, programId, loc);
		FloatBuffer fb = createVecBuffer(vects);
		int bufferId = GL40.glGenBuffers();
		GL40.glBindBuffer(GL40.GL_UNIFORM_BUFFER, bufferId);
		GL40.glBufferData(GL40.GL_UNIFORM_BUFFER, fb, GL40.GL_STATIC_DRAW);
		GL40.glBindBuffer(GL40.GL_UNIFORM_BUFFER, 0);

		int blockIndex = GL40.glGetUniformBlockIndex(programId, name);
		if (blockIndex == -1) {
			Kronos.debug.getLogger().error("Uniform block index not found for: {}", name);
			// Handle the error appropriately, e.g., throw an exception or return.
			return;
		}

		GL40.glUniformBlockBinding(programId, blockIndex, loc);
		int error = GL40.glGetError();
		if (error != GL40.GL_NO_ERROR) {
			Kronos.debug.getLogger().error("Failed to set uniform block binding. Error code: {}", error);
			// Handle the error appropriately, e.g., throw an exception or return.
			return;
		}

		GL40.glBindBufferBase(GL40.GL_UNIFORM_BUFFER, loc, bufferId);
		error = GL40.glGetError();
		if (error != GL40.GL_NO_ERROR) {
			Kronos.debug.getLogger().error("Failed to bind uniform buffer to binding point {}. Error code: {}", loc,
					error);
			// Handle the error appropriately, e.g., throw an exception or return.
		}
	}

	/**
	 * @param vects
	 * @return
	 */
	private FloatBuffer createVecBuffer(Vector3f[] vects) {
		FloatBuffer fb = BufferUtils.createFloatBuffer(vects.length * 3);
		for (int i = 0; i < vects.length; i++) {
			vects[i].get(fb);
		}
		return fb;
	}

	@Override
	public void compileShader() {
		setAttribs();
		int vertexShaderID = compileAndCreateShader();

		int fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShaderID, fragmentSource);
		GL20.glCompileShader(fragmentShaderID);
		ShaderUtils.checkShaderCompilationStatus(this, fragmentShaderID, "Fragment RenderShader");

		programId = GL20.glCreateProgram();
		GL20.glAttachShader(programId, vertexShaderID);
		GL20.glAttachShader(programId, fragmentShaderID);
		vertexId = vertexShaderID;
		fragmentId = fragmentShaderID;
		linkProgram();

	}

	/**
	 * @return
	 */
	public int compileAndCreateShader() {
		if (vertexSource != null) {
			int vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
			GL20.glShaderSource(vertexShaderID, vertexSource);
			GL20.glCompileShader(vertexShaderID);
			ShaderUtils.checkShaderCompilationStatus(this, vertexShaderID, "Vertex RenderShader");
			return vertexShaderID;
		}
		return -1;
	}

	/**
	 * @param vertexShaderID
	 * @param fragmentShaderID
	 * @deprecated Use {@link #linkProgram()} instead
	 */
	@Deprecated
	private void link() {
		linkProgram();
	}

	/**
	 * @param vertexShaderID
	 * @param fragmentShaderID
	 */
	private void linkProgram() {

		for (Iterator iterator = attributes.iterator(); iterator.hasNext();) {
			ShaderAttribute sa = (ShaderAttribute) iterator.next();
			sa.apply(programId);
		}
		GL20.glLinkProgram(programId);
		GL20.glValidateProgram(programId);

		GL20.glDeleteShader(vertexId);
		GL20.glDeleteShader(fragmentId);
	}

	/**
	 * @deprecated Use
	 *             {@link ShaderUtils#checkShaderCompilationStatus(RenderShader,int,String)}
	 *             instead
	 */
	@Deprecated
	public static void checkShaderCompilationStatus(BaseShader renderShader, int shaderID, String shaderType) {
		ShaderUtils.checkShaderCompilationStatus(renderShader, shaderID, shaderType);
	}

	/**
	 * @return the program_id
	 */
	public int getShaderProgramID() {
		return programId;
	}

	/**
	 * @return the vid
	 */
	public int getVertexShaderID() {
		return vertexId;
	}

	/**
	 * @return the fid
	 */
	public int getFragmentShaderID() {
		return fragmentId;
	}

	@Override
	public abstract void setUniforms();

	@Override
	public void use() {

		GL40.glUseProgram(programId);
		setUniforms();
	}

	/**
	 * 
	 * @deprecated Use {@link #bindBufferObject(BufferObject, String)} instead
	 * @param lights
	 * @return
	 */
	@Deprecated
	public Vector2i bindLightsSSBO(List<Light> lights) {
		this.use();
		IntBuffer props = BufferUtils.createIntBuffer(1);
		IntBuffer params = BufferUtils.createIntBuffer(1);
		props.put(0, GL_BUFFER_BINDING);
		int lightIndex = glGetProgramResourceIndex(programId, GL_SHADER_STORAGE_BLOCK, "Lights");

		glGetProgramResourceiv(this.programId, GL_SHADER_STORAGE_BLOCK, lightIndex, props, null, params);
		int binding = params.get(0);

		int ssboID = GL40.glGenBuffers();
		FloatBuffer lightData = BufferUtils.createFloatBuffer(lights.size() * 20); // 4 floats per vector
		GL40.glBindBuffer(GL_SHADER_STORAGE_BUFFER, ssboID);
		for (Light light : lights) {
			lightData.put(light.getPosition().x);
			lightData.put(light.getPosition().y);
			lightData.put(light.getPosition().z);
			lightData.put(0.0f); // padding for alignment

			lightData.put(light.getConstant());
			lightData.put(light.getLinear());
			lightData.put(light.getQuad());
			lightData.put(0.0f); // padding for alignment

			lightData.put(light.getAmbient().x);
			lightData.put(light.getAmbient().y);
			lightData.put(light.getAmbient().z);
			lightData.put(0.0f); // padding for alignment

			lightData.put(light.getDiffuse().x);
			lightData.put(light.getDiffuse().y);
			lightData.put(light.getDiffuse().z);
			lightData.put(0.0f); // padding for alignment

			lightData.put(light.getSpecular().x);
			lightData.put(light.getSpecular().y);
			lightData.put(light.getSpecular().z);
			lightData.put(light.getDis()); // padding for alignment
		}

		lightData.flip();
		GL40.glBufferData(GL_SHADER_STORAGE_BUFFER, lightData, GL40.GL_STATIC_DRAW);
		GL40.glBindBuffer(GL_SHADER_STORAGE_BUFFER, 0);
		return new Vector2i(binding, ssboID);
	}

}
