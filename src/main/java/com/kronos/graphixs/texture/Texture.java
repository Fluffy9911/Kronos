package com.kronos.graphixs.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43C;

import com.kronos.graphixs.color.Color;

public class Texture {
	public int textureId = 0;
	int width = 0, height = 0;
	TextureInfo info = BASE;

	public Texture(String filePath) {
		this(loadImage(filePath));
		info = BASE;
	}

	public Texture(int tid) {
		textureId = tid;
		info = BASE;
	}

	public Texture(int[][] pixels) {
		width = pixels.length;
		height = pixels[0].length;
		info = BASE;
		textureId = loadTexture(pixels);

	}

	public Texture(int textureId, int width, int height) {
		super();
		info = BASE;
		this.textureId = textureId;
		this.width = width;
		this.height = height;
	}

	public Texture(int width, int height, ByteBuffer buf) {
		super();
		info = BASE;

		this.width = width;
		this.height = height;
		loadTexture(buf, width, height);
	}

	public Texture(BufferedImage image) {
		info = BASE;
		textureId = loadTexture(image);
	}

	public void bind() {
		GL40.glActiveTexture(info.level());
		GL11.glBindTexture(info.dimension(), textureId);

	}

	public void unbind() {
		GL11.glBindTexture(info.dimension(), 0);
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
		GL11.glBindTexture(info.dimension(), textureId);

		for (Map.Entry<Integer, Integer> entry : info.params().entrySet()) {
			Integer key = entry.getKey();
			Integer val = entry.getValue();
			GL11.glTexParameteri(info.dimension(), key, val);
		}

		// Upload the texture data
		GL11.glTexImage2D(info.dimension(), 0, info.formatInternal(), width, height, 0, info.format(), info.type(),
				buffer);

		return textureId;
	}

	public int loadTexture(ByteBuffer image, int w, int h) {
		width = w;
		height = h;

		textureId = GL11.glGenTextures();
		GL11.glBindTexture(info.dimension(), textureId);

		for (Map.Entry<Integer, Integer> entry : info.params().entrySet()) {
			Integer key = entry.getKey();
			Integer val = entry.getValue();
			GL11.glTexParameteri(info.dimension(), key, val);
		}

		// Upload the texture data
		GL11.glTexImage2D(info.dimension(), 0, info.formatInternal(), width, height, 0, info.format(), info.type(),
				image);

		return textureId;
	}

	public int loadTexture(int[][] pixels) {

		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4); // 4 for RGBA

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = pixels[x][y];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
//				if ((byte) ((pixel >> 24) & 0xFF) == 0)
//					buffer.put((byte) 1); // Alpha component
//				else
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
			}
		}

		buffer.flip();
		System.out.println("built");
		textureId = GL11.glGenTextures();
		GL11.glBindTexture(info.dimension(), textureId);

		for (Map.Entry<Integer, Integer> entry : info.params().entrySet()) {
			Integer key = entry.getKey();
			Integer val = entry.getValue();
			GL11.glTexParameteri(info.dimension(), key, val);
		}

		// Upload the texture data
		GL11.glTexImage2D(info.dimension(), 0, info.formatInternal(), width, height, 0, info.format(), info.type(),
				buffer);
		return textureId;
	}

	public void cleanup() {
		GL11.glDeleteTextures(textureId);
	}

	public static Texture singleColor(int w, int h, Color c) {
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
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL40.GL_RGBA32F, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
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

	public void bindLoc(int l) {
		GL40.glActiveTexture(GL40.GL_TEXTURE0 + l);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
	}

	public void bindImage() {
		GL43C.glBindImageTexture(0, textureId, 0, false, 0, GL43C.GL_READ_ONLY, GL40.GL_RGBA32F);
	}

	public static final TextureInfo BASE = new TextureInfo() {

		@Override
		public int type() {
			// TODO Auto-generated method stub
			return GL11.GL_UNSIGNED_BYTE;
		}

		@Override
		public int level() {
			// TODO Auto-generated method stub
			return GL40.GL_TEXTURE0;
		}

		@Override
		public int format() {
			// TODO Auto-generated method stub
			return GL11.GL_RGBA;
		}

		@Override
		public int dimension() {
			// TODO Auto-generated method stub
			return GL11.GL_TEXTURE_2D;
		}

		@Override
		public Map<Integer, Integer> params() {
			// TODO Auto-generated method stub
			return Map.of(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		}

		@Override
		public int formatInternal() {
			// TODO Auto-generated method stub
			return GL40.GL_RGBA8;
		}
	};
}
