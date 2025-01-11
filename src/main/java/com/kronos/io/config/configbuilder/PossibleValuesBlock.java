package com.kronos.io.config.configbuilder;

public class PossibleValuesBlock extends Block {

	private String possibleValues;
	private String[] listOfValues;

	public PossibleValuesBlock(String name, String possibleValues, String[] listOfValues) {
		super(true, name, listOfValues);
		this.possibleValues = possibleValues;
		this.listOfValues = listOfValues;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Assuming the input string format is
		// prefix+name+possible_values+app+[somelistofvaluesno"];
		// Extracting the list of values inside the brackets
		int startIndex = val.indexOf("[") + 1;
		int endIndex = val.lastIndexOf("]");
		String valuesString = val.substring(startIndex, endIndex);
		// Splitting the values string into an array
		this.listOfValues = valuesString.split(",");
		// Extracting the possible values between name and app
		int possibleValuesIndex = val.indexOf(name) + name.length();
		int appIndex = val.indexOf(app);
		this.possibleValues = val.substring(possibleValuesIndex, appIndex).trim();
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		// Outputting the values
		if (l == 0)
			return pref + name + possibleValues + ";";
		else
			return name + possibleValues + ";";
	}
}
