/**
 * 
 */
package com.kronos.graphixs.shaders.compute;

import org.joml.Vector3i;
import org.lwjgl.opengl.GL43C;

import com.kronos.graphixs.shaders.BaseShader;

/**
 * 
 */
public abstract class ComputeShader extends BaseShader {
	String source;
	protected int shaderID = -1;
	Vector3i size = new Vector3i(1, 1, 1);

	public ComputeShader(String source, Vector3i size) {
		super();
		this.source = source;
		this.size = size;
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
	public void compileShader() {
		shaderID = compileAndCreateShader(GL43C.GL_COMPUTE_SHADER, source, "COMPUTE");

		programId = linkAndAttach(shaderID);
		this.shaderLogger.debug(this.getShaderCompilationStatus());

	}

	/**
	 * @return the size
	 */
	public Vector3i getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Vector3i size) {
		this.size = size;
	}

	@Override
	public void use() {
		GL43C.glUseProgram(programId);
		GL43C.glDispatchCompute(size.x, size.y, size.z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @see org.joml.Vector3i#set(int, int, int)
	 */
	public Vector3i set(int x, int y, int z) {
		return size.set(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @see org.joml.Vector3i#add(int, int, int)
	 */
	public Vector3i add(int x, int y, int z) {
		return size.add(x, y, z);
	}

}
