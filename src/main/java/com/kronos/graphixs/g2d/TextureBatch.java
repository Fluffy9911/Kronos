package com.kronos.graphixs.g2d;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.joml.Matrix4f;

import com.kronos.Kronos;
import com.kronos.graphixs.geometry.meshing.TexturedMesh;
import com.kronos.graphixs.texture.Texture;

public class TextureBatch {
	HashMap<TexturedMesh, Matrix4f> mesh;
	Abstract2DGraphixs g;

	public TextureBatch() {
		super();

		mesh = new HashMap<>();

	}

	public void begin(Abstract2DGraphixs g) {
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
		mesh.put(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h)), new Matrix4f());
	}

	public void drawTexture(int x, int y, int w, int h, Texture img) {
		mesh.put(new TexturedMesh(img, new ScreenCord(x, y, w, h)), new Matrix4f());
	}

	public void drawTexture(int x, int y, int w, int h, BufferedImage img, String sid) {
		mesh.put(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h), sid), new Matrix4f());
	}

	public void drawTexture(int x, int y, int w, int h, Texture img, String sid) {
		mesh.put(new TexturedMesh(img, new ScreenCord(x, y, w, h), sid), new Matrix4f());
	}

	public void drawTexture(int x, int y, int w, int h, BufferedImage img, Matrix4f m) {
		mesh.put(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h)), m);
	}

	public void drawTexture(int x, int y, int w, int h, Texture img, Matrix4f m) {
		mesh.put(new TexturedMesh(img, new ScreenCord(x, y, w, h)), m);
	}

	public void drawTexture(int x, int y, int w, int h, BufferedImage img, String sid, Matrix4f m) {
		mesh.put(new TexturedMesh(new Texture(img), new ScreenCord(x, y, w, h), sid), m);
	}

	public void drawTexture(int x, int y, int w, int h, Texture img, String sid, Matrix4f m) {
		mesh.put(new TexturedMesh(img, new ScreenCord(x, y, w, h), sid), m);
	}

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param string
	 */
	public void drawTexture(int x, int y, int w, int h, String string) {
		mesh.add(new TexturedMesh(Kronos.graphixs.getTexture(string), new ScreenCord(x, y, w, h)));

	}
}
