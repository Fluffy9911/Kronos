/**
 * 
 */
package com.kronos.graphixs.texture;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import com.kronos.io.assets.TextureBuffer;

/**
 * 
 */
public class TextureTask implements Runnable {
	TextureBuffer buffer;
	BufferedImage image;
	String id;

	public TextureTask(TextureBuffer buffer, BufferedImage image, String id) {
		super();
		this.buffer = buffer;
		this.image = image;
		this.id = id;
	}

	public ByteBuffer create(BufferedImage img) {
		return BufferUtils.createByteBuffer(img.getWidth() * img.getHeight() * 4);
	}

	@Override
	public void run() {
		int width = image.getWidth();
		int height = image.getHeight();

		ByteBuffer buffer = create(image);

		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);

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
		this.buffer.addData(id, new TexData(width, height, buffer));
	}
}
