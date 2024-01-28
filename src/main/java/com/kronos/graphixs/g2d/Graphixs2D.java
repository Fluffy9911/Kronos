package com.kronos.graphixs.g2d;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.core.util.SListener;
import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.geometry.meshing.TexturedMesh;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.Config;

public class Graphixs2D implements SListener {

	Config g_config;

	FrameBuffer graphixs_pane;

	ScreenProvider provider;

	ShaderProgram shader;
	int rendered_textures = 0;

	public Graphixs2D(FrameBuffer graphixs_pane, ScreenProvider provider, ShaderProgram shader) {
		super();
		this.graphixs_pane = graphixs_pane;
		this.provider = provider;
		this.shader = shader;
	}

	public void batchRender(List<TexturedMesh> meshes) {
		rendered_textures = 0;

//		graphixs_pane.start();
//		Kronos.graphixs.clearScreen(Colors.White);
		// render

		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			TexturedMesh tm = (TexturedMesh) iterator.next();
			GL40.glUseProgram(tm.getDraw().getProgram_id());
			tm.getDraw().addUniform("proj", provider.collectTransform());

			tm.render();
			rendered_textures++;
			tm.getTexture().unbind();
		}

//		graphixs_pane.end();
		GL40.glUseProgram(0);

	}

	public TextureBatch createBatch() {
		TextureBatch tb = new TextureBatch();
		tb.begin(this);
		return tb;
	}

	public void renderQuad() {
		Kronos.graphixs.drawPPQuad(Kronos.graphixs.getShader("pp_tex"));
	}

	@Override
	public void updateSC(ScreenConfig sc) {
//		Kronos.graphixs.buffers.put("graphixs2d_pane", new FrameBuffer(sc.width(), sc.height(), true));
		graphixs_pane = Kronos.graphixs.getBuffer("graphixs2d_pane");

	}

	public ScreenProvider getProvider() {
		return provider;
	}

}
