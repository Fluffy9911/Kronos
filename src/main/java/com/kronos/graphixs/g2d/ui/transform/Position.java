package com.kronos.graphixs.g2d.ui.transform;

import com.kronos.graphixs.g2d.ScreenCord;

public class Position {

	public static ScreenCord topLeft(ScreenCord an, ScreenCord size) {
		float tx = an.getX();
		float ty = an.getY();

		if (size.getW() < an.getW()) {
			size.setX(tx);

		} else if (size.getH() < an.getH()) {
			size.setY(ty);
		}
		if (size.getW() > an.getW()) {
			size.setW(an.getW());
			size.setX(tx);
		} else if (size.getH() > an.getH()) {
			size.setH(an.getH());
			size.setY(ty);
		}
		return size;
	}

	public static ScreenCord bottomLeft(ScreenCord an, ScreenCord size) {
		float tx = an.getX();
		float ty = an.getY();
		float tty = (an.getH() - size.getH());
		if (tty < 0) {
			tty = ty;
		}
		size.setX(tx);
		size.setY(tty);
		if (size.getW() > an.getW()) {
			size.setW(an.getW());
			size.setX(tx);
		}
		return size;
	}

}
