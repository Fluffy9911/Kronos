package com.kronos.graphixs.display;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.kronos.graphixs.color.Color;

public class Texture {
	public int textureId = 0;
	int width = 0, height = 0;

	public Texture(String filePath) {
		this(loadImage(filePath));
	}

	public Texture(int tid) {
		textureId = tid;
	}

	public Texture(int textureId, int width, int height) {
		super();
		this.textureId = textureId;
		this.width = width;
		this.height = height;
	}

	public Texture(BufferedImage image) {
		textureId = loadTexture(image);
	}

	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
	}

	public void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	private static BufferedImage loadImage(String filePath) {
		try {

			return ImageIO.read(Texture.class.getResourceAsStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public int loadTexture(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();

		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);

		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4); // 4 for RGBA

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = pixels[y * width + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				if ((byte) ((pixel >> 24) & 0xFF) == 0)
					buffer.put((byte) 0); // Alpha component
				else
					buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
			}
		}

		buffer.flip();

		textureId = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

		// Setup texture parameters
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		// Upload the texture data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				buffer);

		return textureId;
	}

	public void cleanup() {
		GL11.glDeleteTextures(textureId);
	}

	public static Texture singleColor(int w, int h, Color c) {
		int width = w;
		int height = h;

		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4); // 4 for RGBA

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = c.rgb();
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
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

	public int getTextureId() {
		return textureId;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
