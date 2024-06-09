/**
 * 
 */
package com.kronos.io.config;

import java.util.Arrays;

public class ConfigKey implements IConfigKey {
	String name, type;
	String[] values = new String[] {};
	String min, max;
	boolean string = false, comment;
	boolean header = false;
	boolean line = false;

	public ConfigKey() {
	}

	public ConfigKey(String name, String type, String[] values, String min, String max, boolean string,
			boolean comment) {
		this.name = name;
		this.type = type;
		this.values = values;
		this.min = min;
		this.max = max;
		this.string = string;
		this.comment = comment;
	}

	public ConfigKey(String name, String type, String[] values, String min, String max, boolean string, boolean comment,
			boolean header) {
		this.name = name;
		this.type = type;
		this.values = values;
		this.min = min;
		this.max = max;
		this.string = string;
		this.comment = comment;
		this.header = header;
	}

	public ConfigKey(String name, String type, String[] values, String min, String max, boolean string, boolean comment,
			boolean header, boolean line) {
		this.name = name;
		this.type = type;
		this.values = values;
		this.min = min;
		this.max = max;
		this.string = string;
		this.comment = comment;
		this.header = header;
		this.line = line;
	}

	@Override
	public String to() {
		if (line) {
			return "\n";
		}
		if (header) {
			return "## Section: " + name;
		}
		if (comment)
			return "#" + values[0];
		if (!string) {
			if (!(values == null) && values.length == 1)
				return "$" + name + "=" + values[0] + ";" + "type=" + type + ";" + min + "~" + max + ";";
			else if (values != null && values.length > 1)
				return "$" + name + "= %[" + String.join(",", values) + "];type=" + type + ";" + min + "~" + max + ";";
		}
		return "$" + name + "= %[" + String.join(",", values) + "];type=" + type + ";";
	}

	@Override
	public IConfigKey from(String s) {
		if (s.startsWith("#")) {
			parseComment(s);
		} else if (s.startsWith("$")) {
			parseConfig(s);
		}
		return this;
	}

	/**
	 * Parses a comment line.
	 * 
	 * @param s The input string representing the comment.
	 */
	private void parseComment(String s) {
		comment = true;
		// Remove '#' symbol and trim the string
		values = new String[] { s.substring(1).trim() };
	}

	/**
	 * Parses a configuration line.
	 * 
	 * @param s The input string representing the configuration.
	 */
	private void parseConfig(String s) {
		comment = false;
		// Split the configuration line into parts using ';' as delimiter
		String[] parts = s.substring(1).split(";");
		parseNameAndType(parts[0]);
		parseValues(parts[0]);
		// If it's not a string type, parse min-max values
		if (!string) {
			parseMinMax(parts[2]);
		}
	}

	/**
	 * Parses the name and type of the configuration.
	 * 
	 * @param nameType The part of the configuration line containing name and type.
	 */
	private void parseNameAndType(String nameType) {
		// Split the name and type using '=' as delimiter
		String[] nameValue = nameType.split("=");
		// Extract and trim the name
		name = nameValue[0].trim();

		// Extract and trim the type
		String[] typeValue = nameValue[1].split("=");
		type = typeValue[1].trim();
	}

	/**
	 * Parses the values of the configuration.
	 * 
	 * @param nameType The part of the configuration line containing name and type.
	 */
	private void parseValues(String nameType) {
		String valuesString = nameType.split("=")[1].trim();
		if (valuesString.startsWith("%")) {
			// For string type, values are enclosed in brackets and separated by comma
			string = true;
			// Remove brackets and trim the string
			valuesString = valuesString.substring(2, valuesString.length() - 2);
			// Split values using comma as delimiter
			values = valuesString.split(",");
		} else {
			// For non-string type, there is only one value
			string = false;
			values = new String[] { valuesString };
		}
	}

	/**
	 * Parses the minimum and maximum values.
	 * 
	 * @param minMax The part of the configuration line containing min-max values.
	 */
	private void parseMinMax(String minMax) {
		// Split min and max using '~' as delimiter
		String[] minMaxValues = minMax.split("~");
		// Extract and trim min value
		min = minMaxValues[0].trim();
		// Extract and trim max value
		max = minMaxValues[1].trim();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigKey [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (values != null) {
			builder.append("values=");
			builder.append(Arrays.toString(values));
			builder.append(", ");
		}
		if (min != null) {
			builder.append("min=");
			builder.append(min);
			builder.append(", ");
		}
		if (max != null) {
			builder.append("max=");
			builder.append(max);
			builder.append(", ");
		}
		builder.append("string=");
		builder.append(string);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the values
	 */
	public String[] getValues() {
		return values;
	}

	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @return the string
	 */
	public boolean isString() {
		return string;
	}

	/**
	 * @return the comment
	 */
	public boolean isComment() {
		return comment;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

}
