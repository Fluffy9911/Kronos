package com.kronos.graphixs.g2d.ui;

import com.kronos.graphixs.g2d.ScreenProvider;

/**
 * Transforms Cordinates so they can be positioned acordingly
 */
public interface Transformer {
	/**
	 * 
	 * @param provider
	 * @param position
	 */
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints);

}
