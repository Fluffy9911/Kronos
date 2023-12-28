package com.kronos.graphixs.g2d.ui.transform;

import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.Transformer;
import com.kronos.io.InputHandler;

public class DragNDrop implements Transformer {
	boolean last = false;

	@Override
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		int mx = (int) InputHandler.getLastMouseX();
		int my = (int) InputHandler.getLastMouseY();

		if (InputHandler.mouseDownRight() && position.pos().contains(mx, my)) {

			ScreenCord sc = new ScreenCord(mx, my, position.pos().getW(), position.pos().getH());
			position.setPos(sc);
			last = true;
			return;
		}
		if (last && InputHandler.mouseDownRight()) {
			ScreenCord sc = new ScreenCord(mx, my, position.pos().getW(), position.pos().getH());
			position.setPos(sc);

			return;
		}
		last = false;
	}

}
