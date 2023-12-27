package com.kronos.graphixs.g2d.ui;

import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;

public class BasePosition implements ComponentPosition {
	ScreenCord pos, ap;
	int xpad, ypad;
	ScreenProvider provider;

	public BasePosition(ScreenCord pos, ScreenCord ap, int xpad, int ypad, ScreenProvider provider) {
		super();
		this.pos = pos;
		this.ap = ap;
		this.xpad = xpad;
		this.ypad = ypad;
		this.provider = provider;
	}

	public BasePosition(ScreenCord pos, ScreenCord ap, int xpad, ScreenProvider provider) {

		this(pos, ap, xpad, 0, provider);
	}

	public BasePosition(ScreenCord pos, ScreenCord ap, ScreenProvider provider) {
		this(pos, ap, 0, 0, provider);
	}

	public BasePosition(ScreenCord pos, ScreenProvider provider) {
		this(pos, new ScreenCord(0, 0, provider.getConfig().width(), provider.getConfig().height()), 0, 0, provider);
	}

	@Override
	public ScreenCord pos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public int xpad() {
		// TODO Auto-generated method stub
		return xpad;
	}

	@Override
	public int ypad() {
		// TODO Auto-generated method stub
		return ypad;
	}

	@Override
	public ScreenCord anchoredPos() {
		// TODO Auto-generated method stub
		return ap;
	}

}
