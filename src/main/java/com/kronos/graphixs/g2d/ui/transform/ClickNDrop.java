package com.kronos.graphixs.g2d.ui.transform;

import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.Transformer;
import com.kronos.io.InputHandler;

public class ClickNDrop implements Transformer {
	boolean clicked = false;

	@Override
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		int mx = (int) InputHandler.getLastMouseX();
		int my = (int) InputHandler.getLastMouseY();
		if (!InputHandler.mouseDownRight()) {
			return;
		}
		if (constraints != null && clicked) {
			System.out.println(constraints.length);
			for (int i = 0; i < constraints.length; i++) {
				System.out.println(constraints[i].anchoredPos().contains(mx, my));
				if (constraints[i].anchoredPos().contains(mx, my) && InputHandler.mouseDownRight()) {
					KeepInBox k = new KeepInBox();
					k.reposition(provider, new BasePosition(position.pos(), constraints[i].anchoredPos(), provider),
							constraints);
					position.setAp(constraints[i].anchoredPos());
					System.out.println("set");
					return;
				}
			}
			return;
		}
		if (position.pos().contains(mx, my) && InputHandler.mouseDownRight()) {
			clicked = true;
			System.out.println(clicked);
			return;
		} else {
			clicked = false;
			return;
		}

	}

}
