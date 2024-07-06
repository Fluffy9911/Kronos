/**
 * 
 */
package com.kronos.testing;

import static com.kronos.core.res.ResourceField.serializableField;

import java.util.List;

/**
 * 
 */
public class RKeyTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> testr = serializableField("test_static_rfield", List.of("hi", "hello", "sup"));
		System.out.println(testr.toString());
	}

}
