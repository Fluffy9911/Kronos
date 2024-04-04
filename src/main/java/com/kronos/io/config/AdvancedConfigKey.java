/**
 * 
 */
package com.kronos.io.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

/**
 * 
 */
public class AdvancedConfigKey extends ConfigKey {
//$testbool=true;type=boolean;
//$tbarr=%[true,false,false];type=boolean_arr;

	// $vartype=val;type=type_arr_fixed;min~max;lengthmin~lengthmax;
	// strings use the same without min and max

	// $enumname=val;type=enum;values=%[val1,val2,val3];

	// $filenamevar=name;type=file_local;
	// $filenamevar=name;type=file_remote;
	// $color=%[255,255,255,255];type=color_rgb;

	public static List<String> parseBlocks(String input, String prefixToken) {
		List<String> blocks = new ArrayList<>();
		if (input != null && !input.isEmpty()) {
			int startIndex = input.indexOf(prefixToken);
			while (startIndex != -1) {
				int endIndex = input.indexOf(';', startIndex + 1);
				if (endIndex != -1) {
					String block = input.substring(startIndex, endIndex + 1);
					blocks.add(block);
					startIndex = input.indexOf(prefixToken, endIndex + 1);
				} else {
					break;
				}
			}
		}
		return blocks;
	}

	public static List<String> parseArrays(String block, String arrayPrefixToken) {
		List<String> arrays = new ArrayList<>();
		if (block.startsWith(arrayPrefixToken)) {
			String arrayString = block.substring(arrayPrefixToken.length());
			// Remove the leading and trailing brackets
			arrayString = arrayString.substring(1, arrayString.length() - 1);
			String[] elements = arrayString.split(",");
			for (String element : elements) {
				arrays.add(element.trim());
			}
		}
		return arrays;
	}

	public static String[] parsePrefix1(String block, String prefix) {
		if (block.startsWith(prefix)) {
			String peice = block.replaceFirst(prefix, "");
			String[] chunks = peice.split("=");
			String name = chunks[0];

			String value = chunks[1];
			// string
			if (value.startsWith("\"")) {
				value = value.replaceAll("\"", "");
				return new String[] { name, value };

			} else {
				// other value
				return new String[] { name, value };
			}

		}
		return null;

	}

	public static String[] parseEqual(String block) {

		String peice = block;
		String[] chunks = peice.split("=");
		String name = chunks[0];

		String value = chunks[1];
		// string
		if (value.startsWith("\"")) {
			value = value.replaceAll("\"", "");
			return new String[] { name, value };

		} else {
			// other value
			return new String[] { name, value };
		}

	}

	public static String[] parseVdV(String block, String sep) {

		String peice = block;
		String[] chunks = peice.split(sep);
		String name = chunks[0];

		String value = chunks[1];
		// string
		if (value.startsWith("\"")) {
			value = value.replaceAll("\"", "");
			return new String[] { name, value };

		} else {
			// other value
			return new String[] { name, value };
		}

	}

	public static String[] parseArray(String block, String ap) {
		if (!isArr(block, ap))
			return null;

		String peice = block;
		String[] chunks = peice.split("=");
		String name = chunks[0];

		String value = chunks[1];

		String[] values = Strings.splitList(value.replaceAll("[", "").replaceAll("]", ""));

		String[] full = new String[values.length + 1];
		full[0] = name;
		for (int i = 0; i < values.length; i++) {
			full[i + 1] = values[i];
		}
		return full;

	}

	public static boolean isArr(String block, String arrp) {
		return block.contains(arrp);
	}

	public static String[] parsePrefix2(String block, String pref, String ap) {
		if (!isArr(block, ap))
			return null;
		if (block.startsWith(pref)) {
			String peice = block.replaceFirst(pref, "");
			String[] chunks = peice.split("=");
			String name = chunks[0];

			String value = chunks[1];

			String[] values = Strings.splitList(value.replaceAll("[", "").replaceAll("]", ""));

			String[] full = new String[values.length + 1];
			full[0] = name;
			for (int i = 0; i < values.length; i++) {
				full[i + 1] = values[i];
			}
			return full;
		}
		return null;

	}

	public static boolean stringArray(String s) {
		return s.contains("\"");
	}

	// two types of blocks with a prefix {prefix}name=singlevalue; strings need "",
	// second type {prefix}name={arrayprefix}[values seperated by ,]; again strings
	// in ""

}
