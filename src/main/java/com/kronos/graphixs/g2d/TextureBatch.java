package com.kronos.graphixs.g2d;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.geometry.meshing.TexturedMesh;

public class TextureBatch {
	List<TexturedMesh> mesh;
	Graphixs2D g;

	public TextureBatch() {
		super();

		mesh = new ArrayList<>();

	}

	public void begin(Graphixs2D g) {
		this.g = g;
	}

	public void render() {
		if (g == null) {

		} else {
			g.batchRender(mesh);
		}
	}

	public void end() {
		mesh.clear();
	}

	public void drawTexture(int x, int y, int w, int h, BufferedImage img) {
		mesh.add(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h)));
	}

	public void drawTexture(int x, int y, int w, int h, Texture img) {
		mesh.add(new TexturedMesh(img, new ScreenCord(x, y, w, h)));
	}

	public void drawTexture(int x, int y, int w, int h, BufferedImage img, String sid) {
		mesh.add(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h), sid));
	}

	public void drawTexture(int x, int y, int w, int h, Texture img, String sid) {
		mesh.add(new TexturedMesh(img, new ScreenCord(x, y, w, h), sid));
	}
}
