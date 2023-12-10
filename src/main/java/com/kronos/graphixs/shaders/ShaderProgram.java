package com.kronos.graphixs.shaders;

import org.lwjgl.opengl.GL40;

public class ShaderProgram extends Shader {

	public ShaderProgram(String vs, String fs) {
		super(vs, fs);
		// Kronos.graphixs.add(this);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		GL40.glDeleteProgram(program_id);

	}

	@Override
	public void setAttribs() {
		this.addAttribute("in_pos", 0);
		this.addAttribute("color", 1);

	}

	@Override
	public void setUniforms() {
		// TODO Auto-generated method stub

	}

}
