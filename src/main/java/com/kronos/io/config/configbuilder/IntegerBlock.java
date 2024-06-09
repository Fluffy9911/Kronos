package com.kronos.io.config.configbuilder;

public class IntegerBlock extends Block {

	private int value1;
	private int value2;

	public IntegerBlock(String name, int value1, int value2) {
		super(false, name, new String[] { String.valueOf(value1), String.valueOf(value2) });
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Assuming the input string format is pref+name+"="+value1~value2+;
		// We directly split the input string based on the '~' separator to extract the
		// integer values
		// Example parsing logic:
		String[] values = val.split("~");
		this.value1 = Integer.parseInt(values[0].trim());
		this.value2 = Integer.parseInt(values[1].trim());
		// Update the data array
		data = new String[] { String.valueOf(value1), String.valueOf(value2) };
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		// Outputting the values
		if (l == 0)
			return pref + value1 + "~" + value2 + ";";
		else
			return value1 + "~" + value2 + ";";
	}
}
