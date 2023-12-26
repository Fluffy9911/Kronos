package com.kronos.graphixs.g2d;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.geometry.meshing.TexturedMesh;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.Config;

public class Graphixs2D {

	Config g_config;

	FrameBuffer graphixs_pane;

	ScreenProvider provider;

	ShaderProgram shader;

	public Graphixs2D(FrameBuffer graphixs_pane, ScreenProvider provider, ShaderProgram shader) {
		super();
		this.graphixs_pane = graphixs_pane;
		this.provider = provider;
		this.shader = shader;
	}

	public void batchRender(List<TexturedMesh> meshes) {
		GL40.glUseProgram(shader.getProgram_id());
		shader.addUniform("proj", provider.collectTransform());
		// graphixs_pane.start();

		// render

		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			TexturedMesh tm = (TexturedMesh) iterator.next();
			tm.render(shader);
		}
		// graphixs_pane.end();
		GL40.glUseProgram(0);
	}

	public TextureBatch createBatch() {
		TextureBatch tb = new TextureBatch();
		tb.begin(this);
		return tb;
	}

}
