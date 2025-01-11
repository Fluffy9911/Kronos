/**
 * 
 */
package com.kronos.graphixs.g2d.render2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.kronos.graphixs.shaders.bufferobjects.AdvancedBufferObject;
import com.kronos.graphixs.texture.Texture;

/**
 * 
 * vec3 id, data,data vec4 id,type,data,data if small can use data.data
 */
public class AtlasBuilder {

	static int maxs = 2048;

	public static float toFloat(short s1, short s2) {
		return Float.parseFloat(s1 + "." + s2 + "0f");
	}

	public static Atlas buildAtlas(HashMap<String, BufferedImage> rectangles) {
		int currentX = 0;
		int currentY = 0;
		int currentRowHeight = 0;
		BufferedImage image = new BufferedImage(maxs, maxs, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, maxs, maxs);
		int num = 0;
		AdvancedBufferObject ad = new AdvancedBufferObject(rectangles.size() * 6);
		HashMap<String, Integer> ids = new HashMap<>();
		for (Map.Entry<String, BufferedImage> entry : rectangles.entrySet()) {
			String key = entry.getKey();
			BufferedImage rect = entry.getValue();

			int rx = 0;
			int ry = 0;
			int id = num;
			int width = rect.getWidth();
			int height = rect.getHeight();
			ids.put(key, id);
			if (currentX + rect.getWidth() > maxs) {
				// Move to the next row
				currentX = 0;
				currentY += currentRowHeight;
				currentRowHeight = 0;
			}
			if (currentY + rect.getHeight() > maxs) {
				throw new RuntimeException("Cannot fit all images within the atlas");
			}
			rx = currentX;
			ry = currentY;
			currentX += rect.getWidth();
			num++;
			currentRowHeight = Math.max(currentRowHeight, rect.getHeight());
			g.drawImage(rect, rx, ry, rect.getWidth(), rect.getHeight(), null);
			ad.putVector(new Vector2f(num, 1));
			ad.putVector(new Vector4f(rx, ry, width, height));
		}
		return new Atlas(maxs, maxs, rectangles.size(), new Texture(image), ad, ids);
	}
}
