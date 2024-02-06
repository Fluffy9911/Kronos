package com.kronos.graphixs.g2d.pixelcanvas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.Texture;

public class Canvas2D {
	int[][] canvas;
	protected int width;
	protected int height;

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

	public void setAll(int f, int r) {
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[i].length; j++) {
				if (getRGBA(i, j) == f) {
					setPixel(i, j, r);
				}

			}
		}
	}

	/**
	 * @return the canvas
	 */
	public int[][] getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(int[][] canvas) {
		this.canvas = canvas;
	}

	public int[][] copyFrame() {
		int[][] t = new int[width][height];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {

				t[i][j] = canvas[i][j];
			}
		}
		return t;
	}

	public void outToLOC(String name) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.OPAQUE);
		int[][] t = new int[width][height];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				img.setRGB(i, j, canvas[i][j]);
			}
		}
		try {
			ImageIO.write(img, "png",
					new File("C:\\Users\\James.M\\OneDrive\\Desktop\\testfolder\\o\\" + name + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
