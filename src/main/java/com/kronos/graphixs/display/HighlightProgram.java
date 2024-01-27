package com.kronos.graphixs.display;

public class HighlightProgram extends TextureProgram {

	public HighlightProgram(String vs, String fs) {
		super(vs, fs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUniforms() {
		// TODO Auto-generated method stub
		super.setUniforms();
		this.addUniform("intesity", .2f);
	}

}
