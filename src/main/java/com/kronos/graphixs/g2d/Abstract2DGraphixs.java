/**
 * 
 */
package com.kronos.graphixs.g2d;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.joml.Matrix4f;
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

/**
 * 
 */
public class Abstract2DGraphixs implements SListener {

	Config g_config;
	protected FrameBuffer graphixs_pane;
	protected ScreenProvider provider;
	protected ShaderProgram shader;
	int rendered_textures = 0;
	ComponentHandler handler;
	protected Logger l;

	public Abstract2DGraphixs(FrameBuffer graphixs_pane, ScreenProvider provider, ShaderProgram shader) {
		this.graphixs_pane = graphixs_pane;
		this.provider = provider;
		this.shader = shader;
	}

	public void batchRender(HashMap<TexturedMesh, Matrix4f> meshes) {
		rendered_textures = 0;

		for (Map.Entry<TexturedMesh, Matrix4f> entry : meshes.entrySet()) {
			TexturedMesh tm = entry.getKey();
			Matrix4f val = entry.getValue();

			tm.getDraw().use();
			tm.getDraw().addUniform("proj", provider.collectTransform());
<<<<<<< Updated upstream
			// tm.getDraw().addUniform("transform", val);
=======
			tm.getDraw().addUniform("transform", val);
			tm.getDraw().addUniform("view", provider.cam.getView());
>>>>>>> Stashed changes
			tm.render();
			rendered_textures++;
			tm.getTexture().unbind();
		}

		GL40.glUseProgram(0);

	}

	public TextureBatch createBatch(Abstract2DGraphixs graphixs2d) {
		TextureBatch tb = new TextureBatch();
		tb.begin(this);
		return tb;
	}

	public void renderQuad() {
		Kronos.graphixs.drawPPQuad(Kronos.graphixs.getShader("pp_tex"));
	}

	@Override
	public void updateSC(ScreenConfig sc) {
		// Kronos.graphixs.buffers.put("graphixs2d_pane", new FrameBuffer(sc.width(),
		// sc.height(), true));
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

	public ComponentHandler createHandler(Graphixs2D graphixs2d) {
		l.debug("Created ComponentHandler");
		handler = new ComponentHandler(this);
		return handler;

	}

	public void updateGraphixs() {
		if (handler != null) {
			handler.update();

		}
	}

	/**
	 * 
	 */
	protected void createDefaultLogger() {
		// TODO Auto-generated method stub
		this.l = Kronos.debug.getLogger();
	}
}