/**
 * 
 */
package com.kronos.io.config.configbuilder;

/**
 * 
 */
public class EqualsBlock extends Block {
	boolean s = false;

	public EqualsBlock(String n, String[] dat) {
		this.name = n;
		this.data = dat;

	}

	public EqualsBlock(String n, String[] dat, boolean b) {
		this.name = n;
		this.data = dat;
		this.isarr = b;
	}

	public EqualsBlock(String n, String[] dat, boolean b, boolean bb) {
		this.name = n;
		this.data = dat;
		this.isarr = b;
		this.s = bb;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting the name from the input string
		int nameStartIndex;
		if (pref != null && !pref.isEmpty()) {
			nameStartIndex = val.indexOf(pref) + pref.length();
		} else {
			nameStartIndex = 0;
		}
		int nameEndIndex = val.indexOf("=");
		name = val.substring(nameStartIndex, nameEndIndex);

		// Extracting the values based on the value of isarr
		if (isarr) {
			// Assuming the input string format is pref+name+"="+app+[someval,otherval]+;
			// You need to parse the data inside the brackets
			// Example parsing logic:
			int startIndex = val.indexOf("[") + 1;
			int endIndex = val.indexOf("]");
			String dataString = val.substring(startIndex, endIndex);
			data = dataString.split(",");
		} else {
			// Assuming the input string format is pref+name+"="+someval+;
			// You need to parse the data after the '=' sign
			// Example parsing logic:
			int startIndex = val.indexOf("=") + 1;
			String dataString = val.substring(startIndex);
			dataString = dataString.replaceAll("\"", "");
			data = new String[] { dataString };
		}
	}

//reads in pref+name+"="+someval+; or if its an array  pref+name+"="+app+[someval,otherval]+;
	@Override
	public String blockOut(String pref, String app, int l) {
		if (l == 0 && !isarr) {
			return pref + name + "=" + data[0] + ";";
		} else if (l == 0) {
			return pref + name + "=" + app + "[" + String.join(",", data) + "];";
		}
		if (!isarr) {
			return name + "=" + data[0] + ";";
		} else {
			return name + "=" + app + "[" + String.join(",", data) + "];";
		}
	}

}
