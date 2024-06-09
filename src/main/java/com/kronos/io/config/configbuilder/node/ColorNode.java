package com.kronos.io.config.configbuilder.node;

import java.awt.Color;

import com.kronos.io.config.configbuilder.Block;

public class ColorNode extends Block {
	Color colorValue;

	public ColorNode(String name, Color colorValue) {
		super(false, name,
				new String[] { colorValue.getRed() + "," + colorValue.getGreen() + "," + colorValue.getBlue() });
		this.colorValue = colorValue;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting color value
		int colorStartIndex = val.indexOf("\"") + 1;
		int colorEndIndex = val.lastIndexOf("\"");
		String colorStr = val.substring(colorStartIndex, colorEndIndex);
		String[] components = colorStr.split(",");
		if (components.length == 3) {
			int red = Integer.parseInt(components[0]);
			int green = Integer.parseInt(components[1]);
			int blue = Integer.parseInt(components[2]);
			this.colorValue = new Color(red, green, blue);
		}
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + colorValue.getRed() + "," + colorValue.getGreen() + "," + colorValue.getBlue()
				+ "\";";
	}

	public Color getColorValue() {
		return colorValue;
	}

	public void setColorValue(Color colorValue) {
		this.colorValue = colorValue;
	}
}
