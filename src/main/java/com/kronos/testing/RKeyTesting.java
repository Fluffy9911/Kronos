/**
 * 
 */
package com.kronos.testing;

import com.kronos.core.res.ResourceData;
import com.kronos.core.res.ResourceField;
import com.kronos.core.res.ResourceKey;

/**
 * 
 */
public class RKeyTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceData rd = ResourceData.fromKey(ResourceKey.kronos_base.child("test_key"));
		rd.get();
		rd.free();
		ResourceField<Integer> testval = new ResourceField<Integer>(ResourceKey.kronos_base.child("testi"), 1531);
		testval.get().free();
		ResourceField<ResourceKey> te = new ResourceField<ResourceKey>(ResourceKey.kronos_base.child("testrk"),
				ResourceKey.kronos_base);
		te.get().free();
	}

}
