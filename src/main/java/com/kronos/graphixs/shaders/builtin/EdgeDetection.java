package com.kronos.graphixs.shaders.builtin;

import org.joml.Vector2f;

import com.kronos.Kronos;
import com.kronos.graphixs.shaders.render.ShaderProgram;

public class EdgeDetection extends ShaderProgram {
	Vector2f view;

	int texture;

	public EdgeDetection() {
		super(Kronos.loader.tryLoad("shaders/edge_detection_vert.vs"),
				Kronos.loader.tryLoad("shaders/edge_detection_frag.fs"));

	}

	/**
	 * @param texture the texture to set
	 */
	public void setTexture(int texture) {
		this.texture = texture;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Vector2f view) {
		this.view = view;
	}

	@Override
	public void setAttribs() {
		this.addAttribute("in_pos", 0);
		this.addAttribute("uv", 1);
	}

	@Override
	public void setUniforms() {

		this.addUniform("tex", 0);
		this.addUniform("view", view);

	}

}
