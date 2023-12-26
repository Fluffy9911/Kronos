package com.kronos.graphixs.shaders;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.graphixs.resources.Resource;

public abstract class Shader implements Resource {
	public String vs, fs;
	int program_id = -1;

	private int vid, fid;
	List<ShaderAttribute> attribs;

	public Shader(String vs, String fs) {
		this.vs = vs;
		this.fs = fs;
		attribs = new ArrayList<>();

	}

	public abstract void setAttribs();

	public void addAttribute(String attributeName, int loc) {
		attribs.add(new ShaderAttribute(attributeName, loc));
	}

	public int getAttribute(String name) {
		return GL20.glGetAttribLocation(program_id, name);
	}

	public void appendVectorArray(Vector3f[] vects, String name, int loc) {
		Kronos.debug.getLogger().debug("Binding: {} To programid: {} at location: {}", name, program_id, loc);
		FloatBuffer fb = createVecBuffer(vects);
		int bufferId = GL40.glGenBuffers();
		GL40.glBindBuffer(GL40.GL_UNIFORM_BUFFER, bufferId);
		GL40.glBufferData(GL40.GL_UNIFORM_BUFFER, fb, GL40.GL_STATIC_DRAW);
		GL40.glBindBuffer(GL40.GL_UNIFORM_BUFFER, 0);

		int blockIndex = GL40.glGetUniformBlockIndex(program_id, name);
		if (blockIndex == -1) {
			Kronos.debug.getLogger().error("Uniform block index not found for: {}", name);
			// Handle the error appropriately, e.g., throw an exception or return.
			return;
		}

		GL40.glUniformBlockBinding(program_id, blockIndex, loc);
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

	public void addUniform(String id, int value) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			GL20.glUniform1i(location, value);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void addUniform(String id, float value) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			GL20.glUniform1f(location, value);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void addUniform(String id, Vector4f vec4) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void addUniform(String id, Vector3f vec4) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			GL20.glUniform3f(location, vec4.x, vec4.y, vec4.z);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void addUniform(String id, Vector2f vec4) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			GL20.glUniform2f(location, vec4.x, vec4.y);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void addUniform(String id, Matrix4f mat4) {
		int location = GL20.glGetUniformLocation(program_id, id);
		if (location != -1) {
			float[] matrixData = new float[16];
			mat4.get(matrixData);
			GL20.glUniformMatrix4fv(location, false, matrixData);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	public void compileShader() {
		setAttribs();
		int vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexShaderID, vs);
		GL20.glCompileShader(vertexShaderID);
		checkShaderCompilationStatus(vertexShaderID, "Vertex Shader");

		int fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShaderID, fs);
		GL20.glCompileShader(fragmentShaderID);
		checkShaderCompilationStatus(fragmentShaderID, "Fragment Shader");

		program_id = GL20.glCreateProgram();
		GL20.glAttachShader(program_id, vertexShaderID);
		GL20.glAttachShader(program_id, fragmentShaderID);
		vid = vertexShaderID;
		fid = fragmentShaderID;
		link();

	}

	/**
	 * @param vertexShaderID
	 * @param fragmentShaderID
	 */
	private void link() {

		for (Iterator iterator = attribs.iterator(); iterator.hasNext();) {
			ShaderAttribute sa = (ShaderAttribute) iterator.next();
			sa.apply(program_id);
		}
		GL20.glLinkProgram(program_id);
		GL20.glValidateProgram(program_id);

		GL20.glDeleteShader(vid);
		GL20.glDeleteShader(fid);
	}

	private void checkShaderCompilationStatus(int shaderID, String shaderType) {
		int status = GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS);
		if (status != GL20.GL_TRUE) {
			String infoLog = GL20.glGetShaderInfoLog(shaderID);
			Kronos.debug.getLogger().error("Error compiling " + shaderType + ":");
			Kronos.debug.getLogger().error(infoLog);
		}
	}

	public String getShaderCompilationStatus() {
		return GL20.glGetProgramInfoLog(program_id);

	}

	/**
	 * @return the program_id
	 */
	public int getProgram_id() {
		return program_id;
	}

	/**
	 * @return the vid
	 */
	public int getVid() {
		return vid;
	}

	/**
	 * @return the fid
	 */
	public int getFid() {
		return fid;
	}

	public abstract void setUniforms();

	public void use() {

		GL40.glUseProgram(program_id);
		setUniforms();
	}

	public static class ShaderAttribute {
		String name;
		int loc;

		public ShaderAttribute(String name, int loc) {
			this.name = name;
			this.loc = loc;
		}

		public void apply(int pid) {
			GL20.glBindAttribLocation(pid, loc, name);
		}

	}

}
