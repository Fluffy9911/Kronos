package com.kronos.graphixs.g2d;

import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.Config;

public class Graphixs2D {

	Config g_config;

	FrameBuffer graphixs_pane;

	ScreenProvider provider;

	ShaderProgram shader;

	public void batchRender() {

		shader.addUniform("proj", provider.collectTransform());

		// render

	}

}
