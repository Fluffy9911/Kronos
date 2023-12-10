package com.kronos.graphixs.color;

import java.util.Random;

public class Colors {
	public static final Color Red = new Color(1.0f, 0.0f, 0.0f);
	public static final Color Green = new Color(0.0f, 1.0f, 0.0f);
	public static final Color Blue = new Color(0.0f, 0.0f, 1.0f);
	public static final Color Yellow = new Color(1.0f, 1.0f, 0.0f);
	public static final Color Magenta = new Color(1.0f, 0.0f, 1.0f);
	public static final Color Cyan = new Color(0.0f, 1.0f, 1.0f);
	public static final Color Gray = new Color(0.5f, 0.5f, 0.5f);
	public static final Color DarkGreen = new Color(0.0f, 0.5f, 0.0f);
	public static final Color Purple = new Color(0.5f, 0.0f, 0.5f);
	public static final Color Teal = new Color(0.0f, 0.5f, 0.5f);
	public static final Color Brown = new Color(0.8f, 0.6f, 0.4f);
	public static final Color Navy = new Color(0.2f, 0.4f, 0.8f);
	public static final Color Lime = new Color(0.4f, 0.8f, 0.2f);
	public static final Color Salmon = new Color(0.8f, 0.2f, 0.4f);
	public static final Color Mint = new Color(0.2f, 0.8f, 0.4f);
	public static final Color Lavender = new Color(0.4f, 0.2f, 0.8f);
	public static final Color White = new Color(1, 1, 1, 1);
	public static final Color Black = new Color(0, 0, 0, 1);

	public static Color randColor() {
		Random r = new Random();
		return new Color(r.nextFloat(1), r.nextFloat(1), r.nextFloat(1));
	}

	public static Color randColorAlpha() {
		Random r = new Random();
		return new Color(r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), r.nextFloat(1));
	}
}
