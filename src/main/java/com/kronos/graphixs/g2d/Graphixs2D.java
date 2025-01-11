package com.kronos.graphixs.g2d;

import com.kronos.core.util.SListener;
import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.shaders.render.ShaderProgram;

public class Graphixs2D extends Abstract2DGraphixs implements SListener {

	/**
	 * @param graphixs_pane
	 * @param provider
	 * @param shader
	 */
	public Graphixs2D(FrameBuffer graphixs_pane, ScreenProvider provider, ShaderProgram shader) {
		super(graphixs_pane, provider, shader);
		this.createDefaultLogger();
	}

}
