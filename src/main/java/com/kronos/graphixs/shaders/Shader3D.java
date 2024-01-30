/**
 * 
 */
package com.kronos.graphixs.shaders;

import com.kronos.graphixs.display.camera.Camera;

/**
 * 
 */
public class Shader3D extends ShaderProgram {
	Camera pc;

	public Shader3D(String vs, String fs, Camera cam) {
		super(vs, fs);
		this.pc = cam;
	}

	@Override
	public void setUniforms() {
		this.addUniform("proj", pc.getProjection());
		this.addUniform("view", pc.getView());
		this.addUniform("model", pc.getModel());
		this.addUniform("omodel", pc.getModel());
		this.addUniform("viewPos", pc.getLookat());
	}

	public Camera getCamera() {
		return pc;
	}

	public void setCamera(Camera pc) {
		this.pc = pc;
	}

}
