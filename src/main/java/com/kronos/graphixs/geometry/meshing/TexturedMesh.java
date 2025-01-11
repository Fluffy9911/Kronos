package com.kronos.graphixs.geometry.meshing;

import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.shaders.render.RenderShader;
import com.kronos.graphixs.texture.Texture;

public class TexturedMesh {
	Mesh mesh;
	Texture texture;
	ScreenCord cord;
	RenderShader draw;

	public TexturedMesh(Texture texture, ScreenCord cord) {
		super();
		this.texture = texture;
		this.cord = cord;
		texture.bind();
		mesh = Builtin.textured_quad(cord.getX(), cord.getY(), cord.getW(), cord.getH());
		draw = Kronos.graphixs.getShader("texture");
	}

	public TexturedMesh(Texture texture, ScreenCord cord, int depth) {
		super();
		this.texture = texture;
		this.cord = cord;
		texture.bind();
		mesh = Builtin.textured_quad(cord.getX(), cord.getY(), cord.getW(), cord.getH());
		draw = Kronos.graphixs.getShader("texture");
	}

	public TexturedMesh(Texture texture, ScreenCord cord, String draw) {
		super();
		this.texture = texture;
		this.cord = cord;
		this.draw = Kronos.graphixs.getShader(draw);
		texture.bind();
		mesh = Builtin.textured_quad(cord.getX(), cord.getY(), cord.getW(), cord.getH());
		if (draw == null) {
			this.draw = Kronos.graphixs.getShader("texture");
		}
	}

	public void render() {
		GL40.glActiveTexture(GL40.GL_TEXTURE0);
		texture.bind();

		draw.setUniforms();

		mesh.render(draw);
		texture.unbind();

	}

	public RenderShader getDraw() {
		return draw;
	}

	public Texture getTexture() {
		return texture;
	}

}
