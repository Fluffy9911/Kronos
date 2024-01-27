package com.kronos.graphixs.g2d.ui;

import com.kronos.graphixs.g2d.ScreenCord;

public interface ComponentPosition {

	public ScreenCord pos();

	public int xpad();

	public int ypad();

	/**
	 * the bounds where this component CAN* be
	 * 
	 * @return
	 */
	public ScreenCord anchoredPos();

}
