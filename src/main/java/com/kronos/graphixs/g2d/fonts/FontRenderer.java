package com.kronos.graphixs.g2d.fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.kronos.graphixs.display.Texture;
import com.kronos.io.Config;

public class FontRenderer {
	Map<String, Texture> cached;
	Font default_font;

	public FontRenderer(Font default_font) {
		super();
		this.default_font = default_font;
	}

	public BufferedImage prepareText(Font f, Color c, String text) {
		BufferedImage img = new BufferedImage(0, 0, 0);
		Graphics g = img.createGraphics();
		int width = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		int height = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		img = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);

		g = img.createGraphics();
		g.setColor(c);
		g.drawString(text, 0, 0);
		return img;
	}

	public Texture createDrawTexture(Font f, Color c, String text) {
		Texture t = new Texture(prepareText(f, c, text));
		Config co = createConfig(c, text);
		cached.put(co.writeOut(), t);
		return t;
	}

	public Texture getTexture(String text, Color col) {
		return cached.get(createConfig(col, text));
	}

	public Texture getOrCreate(Font f, Color c, String text) {
		if (getTexture(text, c) == null) {
			return createDrawTexture(f, c, text);
		} else {
			return getTexture(text, c);
		}
	}

	public Config createConfig(Color c, String text) {
		Config co = new Config();
		co.appendString("name", text);
		co.appendInt("col_r", c.getRed());
		co.appendInt("col_g", c.getGreen());
		co.appendInt("col_b", c.getBlue());
		return co;
	}

	public String getName(Config c) {
		return c.readString("name");

	}

	public Color getColor(Config c) {
		return new Color(c.readOrWriteInt("col_r", 0), c.readOrWriteInt("col_g", 0), c.readOrWriteInt("col_b", 0));
	}

	public Font useDefaultFont() {
		return default_font;
	}

}
