/**
 * 
 */
package com.kronos.testing;

import com.kronos.core.res.ResourceKey;
import com.kronos.core.res.VariableSerializer;
import com.kronos.graphixs.shaders.utils.Struct;

/**
 * 
 */
public class RKeyTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Struct s = new Struct("Test");
		VariableSerializer.<Struct>saveAll(s, ResourceKey.kronos_base.child("hello_test1"));
	}

}
