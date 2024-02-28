package com.kronos.graphixs.texture;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.kronos.graphixs.color.Color;

public class TextureBuilder {

	public static Texture buildTextureBordered(int w, int h, int bw, Color base, Color b) {
		int width = w;
		int height = h;
		if (width < 0) {
			width = 1;
		}
		if (height < 0) {
			height = 1;
		}
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4); // 4 for RGBA

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = base.rgb();
				if (x == 0 || y == 0 || x == width || y == height) {
					pixel = b.rgb();
				}

				if ((x - bw) <= 0 || (y - bw) <= 0 || (x + bw) >= width || (y + bw) >= height) {
					pixel = b.rgb();
				}

				put(buffer, pixel);
			}
		}

		buffer.flip();

		int td = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, td);

		// Setup texture parameters
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		// Upload the texture data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				buffer);
		return new Texture(td, w, h);
	}

	/**
	 * @param buffer
	 * @param pixel
	 */
	public static void put(ByteBuffer buffer, int pixel) {
		buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
		buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
		buffer.put((byte) (pixel & 0xFF)); // Blue component
		buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
	}

	public static Texture buildRadialGradientTexture(int w, int h, Color centerColor, Color outerColor) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);

		float radius = w / 2.0f;
		float centerX = w / 2.0f;
		float centerY = h / 2.0f;

		for (int y = 0; y < w; y++) {
			for (int x = 0; x < h; x++) {
				float distance = (float) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
				float percent = 1.0f - Math.min(distance / radius, 1.0f);

				Color pixelColor = Color.interpolate(centerColor, outerColor, percent);
				put(buffer, pixelColor.rgb());
			}
		}

		buffer.flip();

		int td = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, td);

		// Setup texture parameters
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		// Upload the texture data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, w, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		return new Texture(td, w, h);
	}
}
