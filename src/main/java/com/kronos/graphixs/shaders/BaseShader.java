/**
 * 
 */
package com.kronos.graphixs.shaders;

import static org.lwjgl.opengl.GL30C.glBindBufferBase;
import static org.lwjgl.opengl.GL43C.GL_BUFFER_BINDING;
import static org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BLOCK;
import static org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BUFFER;
import static org.lwjgl.opengl.GL43C.glGetProgramResourceIndex;
import static org.lwjgl.opengl.GL43C.glGetProgramResourceiv;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.apache.logging.log4j.Logger;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.resources.Resource;
import com.kronos.graphixs.shaders.bufferobjects.BufferObject;
import com.kronos.graphixs.shaders.render.ShaderUniform;

/**
 * 
 */
public abstract class BaseShader implements Resource, ShaderUniform {
	protected Logger shaderLogger = Kronos.debug.getLogger();
	protected int programId = -1;

	@Deprecated
	public String getShaderCompilationStatus() {
		return GL20.glGetProgramInfoLog(programId);

	}

	public void logShaderError(String error) {
		shaderLogger.error("Shader Has Thrown an error: {}", error);
	}

	public abstract void compileShader();

	public int compileAndCreateShader(int type, String source, String t) {
		Graphixs g = Kronos.graphixs;
		int shaderID = GL20.glCreateShader(type);
		g.glErrors();
		GL20.glShaderSource(shaderID, source);
		g.glErrors();
		GL20.glCompileShader(shaderID);
		g.glErrors();
		ShaderUtils.checkShaderCompilationStatus(shaderID, t);
		return shaderID;
	}

	public int linkAndAttach(int i) {
		int id = GL40.glCreateProgram();
		GL40.glAttachShader(id, i);
		GL20.glLinkProgram(id);
		GL20.glValidateProgram(id);
		GL20.glDeleteShader(i);
		return id;
	}

	public int linkAndAttachAll(int... is) {
		int id = GL40.glCreateProgram();
		for (int i = 0; i < is.length; i++) {
			GL40.glAttachShader(id, is[i]);
		}
		GL20.glLinkProgram(id);
		GL20.glValidateProgram(id);
		for (int i = 0; i < is.length; i++) {
			GL20.glDeleteShader(is[i]);
		}
		return id;
	}

	@Override
	public void addUniform(String id, int value) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			GL20.glUniform1i(location, value);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public void addUniform(String id, float value) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			GL20.glUniform1f(location, value);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public void addUniform(String id, Vector4f vec4) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public void addUniform(String id, Vector3f vec4) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			GL20.glUniform3f(location, vec4.x, vec4.y, vec4.z);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public void addUniform(String id, Vector2f vec4) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			GL20.glUniform2f(location, vec4.x, vec4.y);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public void addUniform(String id, Matrix4f mat4) {
		int location = GL20.glGetUniformLocation(programId, id);
		if (location != -1) {
			float[] matrixData = new float[16];
			mat4.get(matrixData);
			GL20.glUniformMatrix4fv(location, false, matrixData);
		} else {
			Kronos.debug.getLogger().error("Uniform " + id + " not found in shader.");
		}
	}

	@Override
	public Vector2i bindBufferObject(BufferObject bo, String bufferName, BaseShader renderShader) {
		renderShader.use();
		IntBuffer props = BufferUtils.createIntBuffer(1);
		IntBuffer params = BufferUtils.createIntBuffer(1);
		props.put(0, GL_BUFFER_BINDING);
		int index = glGetProgramResourceIndex(programId, GL_SHADER_STORAGE_BLOCK, bufferName);

		glGetProgramResourceiv(renderShader.programId, GL_SHADER_STORAGE_BLOCK, index, props, null, params);
		int binding = params.get(0);

		int ssboID = GL40.glGenBuffers();
		FloatBuffer bufferdata = BufferUtils.createFloatBuffer(bo.getSize());
		GL40.glBindBuffer(GL_SHADER_STORAGE_BUFFER, ssboID);

		bo.append(bufferdata);

		bufferdata.flip();
		GL40.glBufferData(GL_SHADER_STORAGE_BUFFER, bufferdata, GL40.GL_STATIC_DRAW);
		GL40.glBindBuffer(GL_SHADER_STORAGE_BUFFER, 0);
		return new Vector2i(binding, ssboID);
	}

	public void bindSSBO(Vector2i buf) {
		glBindBufferBase(GL_SHADER_STORAGE_BUFFER, buf.x, buf.y);
	}

	@Override
	public void setAttribs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUniforms() {
		// TODO Auto-generated method stub

	}

	@Override
	public void use() {
		if (programId == -1) {
			logShaderError("Shader expected a valid program, currently has -1, shader will not be used");
			return;
		}
		GL40.glUseProgram(programId);
	}

}
