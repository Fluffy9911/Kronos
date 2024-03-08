package com.kronos.graphixs.g2d;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.core.util.SListener;
import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.ui.ComponentHandler;
import com.kronos.graphixs.geometry.meshing.TexturedMesh;
import com.kronos.graphixs.shaders.render.ShaderProgram;
import com.kronos.io.Config;

public class Graphixs2D implements SListener {

	Config g_config;

	FrameBuffer graphixs_pane;

	ScreenProvider provider;

	ShaderProgram shader;
	int rendered_textures = 0;

	ComponentHandler handler;
	Logger l;

	public Graphixs2D(FrameBuffer graphixs_pane, ScreenProvider provider, ShaderProgram shader) {
		super();
		this.graphixs_pane = graphixs_pane;
		this.provider = provider;
		this.shader = shader;
		l = Kronos.debug.getLogger();
	}

	public void batchRender(List<TexturedMesh> meshes) {
		rendered_textures = 0;

//		graphixs_pane.start();
//		Kronos.graphixs.clearScreen(Colors.White);
		// render

		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			TexturedMesh tm = (TexturedMesh) iterator.next();
			tm.getDraw().use();
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

	/**
	 * Draws a line using OpenGL's fixed function pipeline.
	 *
	 * @param x   Starting x-coordinate
	 * @param y   Starting y-coordinate
	 * @param ex  Ending x-coordinate
	 * @param ey  Ending y-coordinate
	 * @param col Color of the line
	 */
	public void drawLineFF(int x, int y, int ex, int ey, Color col) {
		GL11.glBegin(GL11.GL_LINES);

		GL11.glColor3f(col.getR(), col.getG(), col.getB());
		GL11.glVertex2i(x, y);
		GL11.glVertex2i(ex, ey);

		GL11.glEnd();
	}

	public ComponentHandler createHandler() {
		l.debug("Created ComponentHandler");
		handler = new ComponentHandler(this);
		return handler;

	}

	public void updateGraphixs() {
		if (handler != null) {
			handler.update();

		}
	}
}
