package com.kronos.graphixs.g2d.pixelcanvas;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.Texture;

public class Canvas2D {
	int[][] canvas;
	int width, height;

	public Canvas2D(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		canvas = new int[width][height];
	}

	public boolean isWithinRange(int x, int y) {
		return ((x >= 0 && y >= 0) && (x < width && y < height));
	}

	public boolean setPixel(int x, int y, int rgba) {
		if (isWithinRange(x, y)) {
			canvas[x][y] = rgba;
			return true;
		} else {
			return false;
		}
	}

	public int getRGBA(int x, int y) {
		if (isWithinRange(x, y)) {
			return canvas[x][y];

		} else {
			return -1;
		}
	}

	public boolean setPixel(int x, int y, Color rgba) {
		if (isWithinRange(x, y)) {
			canvas[x][y] = rgba.rgb();
			return true;
		} else {
			return false;
		}
	}

	public Color getRGBAColor(int x, int y) {
		if (isWithinRange(x, y)) {
			return new Color(canvas[x][y]);

		} else {
			return new Color(-1);

		}
	}

	public Texture toTexture() {
		return new Texture(canvas);
	}

	public void clear(int rgba) {
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[i].length; j++) {
				canvas[i][j] = rgba;
			}
		}
	}

	public void noise() {
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[i].length; j++) {
				canvas[i][j] = Color.random().rgb();
			}
		}
	}

	public void noiseShort() {
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[i].length; j++) {
				int rgb = Color.random().rgb();
				canvas[i][j] = new Color(0).getRGBAShort(rgb);
			}
		}
	}
}
