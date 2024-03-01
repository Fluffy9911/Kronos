package com.kronos.graphixs.g2d.builder;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.geometry.shape.Shape;

public class ShapeRenderer {
	public static HashMap<String, Shape> shapes;

	static {

		shapes = new HashMap<String, Shape>();

	}

	public ShapeRenderer() {
	}

	public void loadIn(String id, BufferedImage img) {
		shapes.put(id, new Shape(img));
	}

	public void renderShape(String sid, Color c, int x, int y, int width, int height, TextureBatch tb) {
		Shape s = shapes.get(sid);

		Texture draw = s.buildTextureForRender(c.rgb());

		tb.drawTexture(x, y, width, height, draw);
	}

	/**
	 * @return the shapes
	 */
	public static HashMap<String, Shape> getShapes() {
		return shapes;
	}

	/**
	 * @param shapes the shapes to set
	 */
	public static void setShapes(HashMap<String, Shape> shapes) {
		ShapeRenderer.shapes = shapes;
	}

}
