package com.kronos.io.config.configbuilder;

public class MaxLengthBlock extends Block {

	private int maxLength;

	public MaxLengthBlock(String name, int maxLength) {
		super(false, name, new String[] { String.valueOf(maxLength) });
		this.maxLength = maxLength;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Assuming the input string format is prefix+name+"="+max_length;
		// Extracting the max length value after the '=' sign
		int startIndex = val.indexOf("=") + 1;
		String maxLengthString = val.substring(startIndex).trim();
		this.maxLength = Integer.parseInt(maxLengthString);
		// Update the data array
		data = new String[] { maxLengthString };
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		// Outputting the max length value
		if (l == 0)
			return pref + name + "=" + maxLength + ";";
		else
			return name + "=" + maxLength + ";";
	}
}
