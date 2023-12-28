package com.kronos.graphixs.g2d.ui.transform;

import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.Transformer;

public class KeepInBox implements Transformer {
	BaseComponent bc;

	public KeepInBox() {
		super();
	}

	public KeepInBox(BaseComponent bc) {
		super();
		this.bc = bc;
	}

	@Override
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		int x = (int) position.pos().getX();
		int y = (int) position.pos().getY();
		int w = (int) position.pos().getW();
		int h = (int) position.pos().getH();

		int ax = (int) position.anchoredPos().getX();
		int ay = (int) position.anchoredPos().getY();
		int aw = (int) position.anchoredPos().getW();
		int ah = (int) position.anchoredPos().getH();

		ScreenCord prev = position.pos();

		if (x < ax) {
			x = ax;
		}
		if (y < ay) {
			y = ay;
		}
		if (x > (ax + aw)) {
			x = ((ax + aw) - w);
		}
		if (y > (ay + ah)) {
			y = ((ay + ah) - h);
		}
		if ((x + w) > (ax + aw)) {
			x = ((ax + aw) - w);
		}
		if ((y + h) > (ay + ah)) {
			y = ((ay + ah) - h);
		}
		if (w > aw) {
			w -= (aw - position.xpad());
		}
		if (h > ah) {
			h -= (ah - position.ypad());
		}
		if (bc != null) {
			bc.move(x - prev.getX(), y - prev.getY(), w - prev.getW(), h - prev.getH());
		}
		position.setPos(new ScreenCord(x, y, w, h));
	}

}
