package com.kronos.graphixs.shaders;

import org.joml.Vector2f;

import com.kronos.Kronos;

public class PixelatedShaderProgram extends ShaderProgram {
	Vector2f view;
	int pixelation;
	int use_pixel;
	int texture;

	public PixelatedShaderProgram() {
		super(Kronos.loader.tryLoad("shaders/pixelate-vs.vs"), Kronos.loader.tryLoad("shaders/pixelate.fs"));

	}

	@Override
	public void setAttribs() {
		this.addAttribute("in_pos", 0);
		this.addAttribute("uv", 1);
	}

	public void update(int p, int t, int u, Vector2f view) {
		this.pixelation = p;
		this.texture = t;
		this.use_pixel = u;
		this.view = view;
	}

	@Override
	public void setUniforms() {
		this.addUniform("pixelation", pixelation);
		this.addUniform("tex", 0);
		this.addUniform("edges", 1);
		this.addUniform("view", view);
		this.addUniform("use", use_pixel);
	}

	public void config() {
		if (use_pixel == 1) {
			use_pixel = 0;
		} else {
			use_pixel = 1;
		}

	}

}
