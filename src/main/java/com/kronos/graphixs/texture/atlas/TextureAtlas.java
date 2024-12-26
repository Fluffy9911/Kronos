package com.kronos.graphixs.texture.atlas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;

import com.kronos.graphixs.texture.Texture;

public class TextureAtlas {
	int scale = 5;
	private HashMap<String, BufferedImage> img; // Stores images with names as keys
	private HashMap<String, Vector2i> textureCoords; // Stores texture coordinates in the atlas
	private BufferedImage builtAtlas; // The final built atlas

	public TextureAtlas(HashMap<String, BufferedImage> img) {
		this.img = img;
		this.textureCoords = new HashMap<>();
	}

	public TextureAtlas(int scale, HashMap<String, BufferedImage> img) {
		this.scale = scale;
		this.img = img;
		this.textureCoords = new HashMap<>();
	}

	public BufferedImage getBuiltAtlas() {
		if (builtAtlas == null) {
			buildAtlas();
		}
		return builtAtlas;
	}

	public HashMap<String, Vector2i> getTextureCords() {
		if (textureCoords.isEmpty() && builtAtlas == null) {
			buildAtlas();
		}
		return textureCoords;
	}

	private void buildAtlas() {
		// Sort textures by size (largest area first)
		ArrayList<Map.Entry<String, BufferedImage>> sortedTextures = new ArrayList<>(img.entrySet());
		sortedTextures
				.sort(Comparator.comparingInt((Map.Entry<String, BufferedImage> entry) -> -entry.getValue().getWidth()
						* entry.getValue().getHeight()));

		// Calculate the initial atlas size (estimate)
		int estimatedWidth = 1024; // You can adjust this based on your use case
		int estimatedHeight = 1024; // Default square atlas

		// Place textures in the atlas
		int currentX = 0, currentY = 0, rowHeight = 0;
		for (Map.Entry<String, BufferedImage> entry : sortedTextures) {
			BufferedImage image = entry.getValue();
			String name = entry.getKey();

			// If this image doesn't fit in the current row, move to the next row
			if (currentX + image.getWidth() > estimatedWidth) {
				currentX = 0;
				currentY += rowHeight;
				rowHeight = 0;
			}

			// Update row height
			rowHeight = Math.max(rowHeight, image.getHeight());

			// Save the position in the textureCoords map
			textureCoords.put(name, new Vector2i(currentX, currentY));

			// Advance the x position for the next texture
			currentX += image.getWidth();
		}

		// Calculate the actual atlas size based on the placement
		estimatedHeight = currentY + rowHeight;

		// Create the final atlas image
		builtAtlas = new BufferedImage(estimatedWidth, estimatedHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = builtAtlas.createGraphics();

		// Draw each texture at its assigned position
		for (Map.Entry<String, Vector2i> entry : textureCoords.entrySet()) {
			String name = entry.getKey();
			Vector2i position = entry.getValue();
			BufferedImage image = img.get(name);

			g.drawImage(image, position.x, position.y, null);
		}

		g.dispose();
	}

	public Texture toGl() {
		Texture t = new Texture(getBuiltAtlas());
		return t;
	}
}
