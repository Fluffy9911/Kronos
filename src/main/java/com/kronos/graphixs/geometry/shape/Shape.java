package com.kronos.graphixs.geometry.shape;

import java.awt.image.BufferedImage;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.pixelcanvas.Canvas2D;

public class Shape extends Canvas2D {

	public Shape(BufferedImage img) {
		super(img.getWidth(), img.getHeight());
		Color za = new Color(1, 1, 1, 0);
		Color white = Colors.White;
		Kronos.debug.getLogger().debug("Starting Shape Building proccess");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				if (img.getRGB(i, j) == Colors.Black.rgb()) {

					setPixel(i, j, Colors.Black.rgb());
				} else {

					setPixel(i, j, za.rgb());
				}

			}

		}

	}

	public Shape(Shape ss) {
		super(ss.width, ss.height);
		this.setCanvas(ss.getCanvas());
	}

	public Texture buildTextureForRender(int rgb) {
		Canvas2D cv = new Canvas2D(this.width, this.height);
		cv.setCanvas(getCanvas());
		cv.setAll(Colors.Black.rgb(), rgb);
		return cv.toTexture();
	}

}
