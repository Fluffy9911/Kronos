package com.kronos.graphixs.g2d.builder;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.geometry.shape.Shape;
import com.kronos.graphixs.texture.Texture;

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

}
