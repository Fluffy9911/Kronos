/**
 * 
 */
package com.kronos.graphixs.g2d.ui;

import com.kronos.io.InputHandler;

/**
 * 
 */
public class HoverListener implements UIListener {

	boolean f = false;

	@Override
	public void listen(BaseComponent bc, States states) {
		int mx = (int) InputHandler.getLastMouseX();
		int my = (int) InputHandler.getLastMouseY();

		if (bc.getPosition().pos().contains(mx, my) && InputHandler.isLeftReleased()) {
			bc.hovering = true;
			if (f == false) {
				enterHover(mx, my, bc);
				f = true;
			} else {
				hover(mx, my, bc);
			}

		} else {
			bc.hovering = false;
			leaveHover(mx, my, bc);
			f = false;
		}

	}

	public void enterHover(int x, int y, BaseComponent bc) {
		bc.enterHover(x, y);
	}

	public void leaveHover(int x, int y, BaseComponent bc) {
		bc.leaveHover(x, y);
	}

	public void hover(int x, int y, BaseComponent bc) {
		bc.hover(x, y);
	}
}