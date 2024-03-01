package com.kronos.graphixs.g2d.fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.texture.Texture;
import com.kronos.io.Config;

/**
 * this class is a MESS
 * 
 * @TODO need to render without awt. but later
 */
public class FontRenderer {
	Map<String, Texture> cached;
	Font default_font;
	Config config;
	boolean cd;
	Logger l = Kronos.debug.getLogger();

	public FontRenderer(Font default_font) {
		super();
		this.default_font = default_font;
		cached = new HashMap<>();
		config = Kronos.k_config;
		cd = config.readOrWriteBoolean("text_caching_extensive_debug", false);
	}

	public Texture get(Object key) {

		return cached.get(key);
	}

	public Texture put(String key, Texture value) {
		if (cd) {
			l.debug("Caching Textureid: {} with data: [W:{}H:{}]", key, value.getWidth(), value.getHeight());
		}
		return cached.put(key, value);
	}

	public static FontRenderer createDefault() {
		return new FontRenderer(getFont());
	}

	private BufferedImage prepareText(Font f, Color c, String text) {
		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TRANSLUCENT);
		Graphics g = img.createGraphics();
		g.setFont(f);
		int width = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		int height = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		img = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);

		g = img.createGraphics();
		g.setColor(c);
		g.setFont(f);
		g.drawString(text, 0, height);
		g.dispose();
		return img;
	}

	public static BufferedImage drawText(Rectangle constraint, Font font, String text, Color color, boolean cen) {
		// Create a BufferedImage with TRANSLUCENT type
		BufferedImage img = new BufferedImage(constraint.width, constraint.height, BufferedImage.TRANSLUCENT);
		Graphics2D g = img.createGraphics();

		// Set rendering hints for better text quality
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Set font and color
		g.setFont(font);
		g.setColor(color);

		// Get FontMetrics to measure text dimensions
		FontMetrics fontMetrics = g.getFontMetrics();

		// Split the text into lines to fit within the given width
		String[] lines = getWrappedTextLines(text, constraint.width, fontMetrics);
		// scale down by .25 until we fit or until we are a size of 0
		while (getOverflowX(text, font, constraint.width) != 0 && getOverflowY(text, font, constraint.height) != 0) {
			if (font.getSize() <= 0) {
				font = font.deriveFont(1);
				break;
			}
			font = font.deriveFont(font.getSize() - .25f);
		}
		// Calculate total height required for all lines
		int totalHeight = lines.length * fontMetrics.getHeight();
		int startY = 0;
		// Calculate the starting y-coordinate to center the text vertically in the box
		if (cen)
			startY = (constraint.height - totalHeight) / 2 + fontMetrics.getAscent();

		// Draw each line of text
		for (String line : lines) {
			int width = fontMetrics.stringWidth(line);
			int x = (constraint.width - width) / 2;
			g.drawString(line, x, startY);
			startY += fontMetrics.getHeight();
		}

		// Dispose of the graphics object
		g.dispose();

		return img;
	}

	private static String[] getWrappedTextLines(String text, int maxWidth, FontMetrics fontMetrics) {
		// Split the input text into words
		String[] words = text.split("\\s+");

		// List to hold the lines of wrapped text
		StringBuilder currentLine = new StringBuilder();
		java.util.List<String> lines = new java.util.ArrayList<>();

		// Iterate through each word
		for (String word : words) {
			int currentLineWidth = fontMetrics.stringWidth(currentLine.toString() + " " + word);

			// If adding the current word exceeds the maxWidth, start a new line
			if (currentLineWidth <= maxWidth) {
				currentLine.append(" ").append(word);
			} else {
				// Start a new line with the current word
				lines.add(currentLine.toString().trim());
				currentLine = new StringBuilder(word);
			}
		}

		// Add the last line
		lines.add(currentLine.toString().trim());

		return lines.toArray(new String[0]);
	}

	private Texture createDrawTexture(Font f, Color c, String text) {
		Texture t = new Texture(prepareText(f, c, text));
		Config co = createConfig(c, text);
		cached.put(co.writeOut(), t);
		return t;
	}

	private Texture createDrawTexture(Font f, Color c, String text, BufferedImage img) {
		Texture t = new Texture(img);
		Config co = createConfig(c, text);
		cached.put(co.writeOut(), t);
		return t;
	}

	private Texture getTexture(String text, Color col) {
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

	public void renderText(String text, int x, int y, TextureBatch batch) {
		Texture t = getTexture(text, Color.WHITE);
		if (t == null)
			t = createDrawTexture(default_font, Color.WHITE, text);

		batch.drawTexture(x, y, t.getWidth(), t.getHeight(), t);
	}

	public void renderText(String text, int x, int y, Font f, Color c, TextureBatch batch) {
		Texture t = getTexture(text, c);
		if (t == null)
			t = createDrawTexture(f, c, text);

		batch.drawTexture(x, y, t.getWidth(), t.getHeight(), t);
	}

	public void renderText(String text, int x, int y, int w, int h, TextureBatch batch) {
		Texture t = getTexture(text, Color.WHITE);
		if (t == null)
			t = createDrawTexture(default_font, Color.WHITE, text);

		batch.drawTexture(x, y, w, h, t);
	}

	public void renderTextConstraints(String text, int x, int y, int w, int h, TextureBatch batch) {
		Texture t = createDrawTexture(default_font, Color.WHITE, text,
				drawText(new Rectangle(0, 0, w, h), default_font, text, Color.WHITE, false));
		batch.drawTexture(x, y, t.getWidth(), t.getHeight(), t);
	}

	public void renderTextConstraints(String text, int x, int y, int w, int h, Font f, Color cc, TextureBatch batch) {
		Texture t = createDrawTexture(f, cc, text, drawText(new Rectangle(0, 0, w, h), f, text, cc, true));
		batch.drawTexture(x, y, t.getWidth(), t.getHeight(), t);
	}

	public void renderTextConstraintsCentered(String text, int x, int y, int w, int h, boolean center,
			TextureBatch batch) {
		Texture t = createDrawTexture(default_font, Color.WHITE, text,
				drawText(new Rectangle(0, 0, w, h), default_font, text, Color.WHITE, center));
		batch.drawTexture(x, y, t.getWidth(), t.getHeight(), t);
	}

	public void renderText(String text, int x, int y, int w, int h, Font f, Color c, TextureBatch batch) {
		Texture t = getTexture(text, c);
		if (t == null)
			t = createDrawTexture(f, c, text);

		batch.drawTexture(x, y, w, h, t);
	}

	private static final Font getFont() {
		Graphics g = new BufferedImage(1, 2, BufferedImage.TYPE_INT_RGB).getGraphics();
		Font font = new Font(g.getFont().toString(), 0, 12);
		g.dispose();

		return font;
	}

	public void createTextCache(String text, Font f, Color c) {
		createDrawTexture(f, c, text);
	}

	public static Rectangle2D getSize(String text, Font f) {
		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TRANSLUCENT);
		Graphics g = img.createGraphics();
		g.setFont(f);
		return g.getFontMetrics().getStringBounds(text, g);
	}

	public static int getOverflowX(String text, Font f, int max) {
		Rectangle2D rd = getSize(text, f);
		return (int) Math.max(0, max - rd.getWidth());

	}

	public static int getOverflowY(String text, Font f, int max) {
		Rectangle2D rd = getSize(text, f);
		return (int) Math.max(0, max - rd.getHeight());

	}

}
