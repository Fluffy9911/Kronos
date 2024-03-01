package com.kronos.graphixs.geometry.shape;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector3f;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.pixelcanvas.Canvas2D;

public class Shape extends Canvas2D {
	public HashMap<Color, Color> mapped;

	public Shape(BufferedImage img) {
		super(img.getWidth(), img.getHeight());
		Color za = new Color(1, 1, 1, 0);
		Color white = Colors.White;
		Kronos.debug.getLogger().debug("Starting Shape Building proccess");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				this.setPixel(i, j, img.getRGB(i, j));
			}

		}
		mapped = new HashMap<>();
	}

	public Shape(Shape ss) {
		super(ss.width, ss.height);
		this.setCanvas(ss.getCanvas());
	}

	public void addMapping(Color c, Color c2) {
		mapped.put(c, c2);
	}

	public Texture buildTextureForRender(int rgb) {
		Shape cv = new Shape(this);
		cv.setCanvas(this.copyFrame());
		for (Map.Entry<Color, Color> entry : mapped.entrySet()) {
			Color key = entry.getKey();
			Color val = entry.getValue();
			cv.setAll(key.rgb(), val.rgb());
		}
		mapped.clear();
		return cv.toTexture();

	}

	public Vector3f difference(Color c, Color cmp) {
		return new Vector3f(c.getA() - cmp.getA(), c.getG() - cmp.getG(), c.getB() - cmp.getB());
	}

}
