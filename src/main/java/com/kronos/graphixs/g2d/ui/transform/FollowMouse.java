package com.kronos.graphixs.g2d.ui.transform;

import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.Transformer;
import com.kronos.io.InputHandler;

public class FollowMouse implements Transformer {

	@Override
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		int mx = (int) InputHandler.getLastMouseX();
		int my = (int) InputHandler.getLastMouseY();

		ScreenCord sc = new ScreenCord(mx, my, position.pos().getW(), position.pos().getH());
		position.setPos(sc);

	}

}
