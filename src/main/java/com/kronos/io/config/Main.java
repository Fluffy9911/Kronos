package com.kronos.io.config;

public class Main {
	public static void main(String[] args) {
		// Create ConfigKey objects
		IConfigKey key1 = new ConfigKey("intKey", "int", new String[] { "50" }, "1", "100", false, false);
		IConfigKey key2 = new ConfigKey("floatKey", "float", new String[] { "3.14" }, "0", "10", false, false);
		IConfigKey key3 = new ConfigKey("stringKey", "string", new String[] { "\"Hello World\"" }, "", "", true, false);
		IConfigKey key4 = new ConfigKey("intArrayKey", "int_arr", new String[] { "1", "2", "3", "4" }, "1", "5", false,
				false);
		IConfigKey key5 = new ConfigKey("floatArrayKey", "float_arr", new String[] { "1.1", "2.2", "3.3" }, "0", "4",
				false, false);
		IConfigKey key6 = new ConfigKey("stringArrayKey", "string_arr",
				new String[] { "\"apple\"", "\"banana\"", "\"cherry\"" }, "", "", true, false);
		IConfigKey key7 = new ConfigKey("", "", new String[] { "This is a comment" }, "", "", false, true);

		// Print ConfigKey objects
		System.out.println("Original ConfigKey Objects:");
		System.out.println(key1.to());
		System.out.println(key2.to());
		System.out.println(key3.to());
		System.out.println(key4.to());
		System.out.println(key5.to());
		System.out.println(key6.to());
		System.out.println(key7.to());

		// Convert ConfigKey objects to strings and store in variables
		String s1 = key1.to();
		String s2 = key2.to();
		String s3 = key3.to();
		String s4 = key4.to();
		String s5 = key5.to();
		String s6 = key6.to();
		String s7 = key7.to();

		// Create new ConfigKey objects by parsing the strings
		IConfigKey newKey1 = new ConfigKey();
		newKey1.from(s1);
		IConfigKey newKey2 = new ConfigKey();
		newKey2.from(s2);
		IConfigKey newKey3 = new ConfigKey();
		newKey3.from(s3);
		IConfigKey newKey4 = new ConfigKey();
		newKey4.from(s4);
		IConfigKey newKey5 = new ConfigKey();
		newKey5.from(s5);
		IConfigKey newKey6 = new ConfigKey();
		newKey6.from(s6);
		IConfigKey newKey7 = new ConfigKey();
		newKey7.from(s7);

		// Print the new ConfigKey objects
		System.out.println("\nNew ConfigKey Objects after parsing:");
		System.out.println(newKey1.to());
		System.out.println(newKey2.to());
		System.out.println(newKey3.to());
		System.out.println(newKey4.to());
		System.out.println(newKey5.to());
		System.out.println(newKey6.to());
		System.out.println(newKey7.to());
	}
}
