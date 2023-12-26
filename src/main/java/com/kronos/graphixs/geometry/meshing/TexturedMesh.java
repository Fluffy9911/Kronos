package com.kronos.graphixs.geometry.meshing;

import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.shaders.Shader;

public class TexturedMesh {
	Mesh mesh;
	Texture texture;
	ScreenCord cord;

	public TexturedMesh(Texture texture, ScreenCord cord) {
		super();
		this.texture = texture;
		this.cord = cord;
		texture.bind();
		mesh = Builtin.textured_quad(cord.getX(), cord.getY(), cord.getW(), cord.getH());
	}

	public void render(Shader s) {
		GL40.glActiveTexture(GL40.GL_TEXTURE0);
		texture.bind();
		s.addUniform("tex", 0);

		mesh.render(s);
		texture.unbind();

	}

}
