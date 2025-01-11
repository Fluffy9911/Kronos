/**
 * 
 */
package com.kronos.graphixs.shaders.geometry;

import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.shaders.BaseShader;

/**
 * 
 */
public abstract class GeometryShader extends BaseShader {
	public String geometrySource;
	protected int shaderID = -1;

	public GeometryShader(String source) {
		this.geometrySource = source;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void use() {
		if (programId == -1) {
			logShaderError("Geometry Shader expected a valid program, currently has -1, shader will not be used");
			return;
		}
		GL40.glUseProgram(programId);

	}

	@Override
	public void compileShader() {
		shaderID = this.compileAndCreateShader(GL40.GL_GEOMETRY_SHADER, geometrySource, "Geometry Shader");
		programId = this.linkAndAttach(shaderID);
	}

}
