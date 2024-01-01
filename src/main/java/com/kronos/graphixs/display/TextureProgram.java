package com.kronos.graphixs.display;

import com.kronos.graphixs.shaders.ShaderProgram;

public class TextureProgram extends ShaderProgram {

	public TextureProgram(String vs, String fs) {
		super(vs, fs);
		// TODO Auto-generated constructor stub
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
	public void setAttribs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUniforms() {
		this.addUniform("tex", 0);

	}

}
