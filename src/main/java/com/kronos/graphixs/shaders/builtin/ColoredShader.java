package com.kronos.graphixs.shaders.builtin;

import com.kronos.Kronos;
import com.kronos.graphixs.shaders.render.ShaderProgram;

public class ColoredShader extends ShaderProgram {

	public ColoredShader() {
		super(Kronos.loader.tryLoad("shaders/vertex.vs"), Kronos.loader.tryLoad("shaders/fragment.fs"));

	}

}
