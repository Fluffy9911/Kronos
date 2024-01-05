package com.kronos.graphixs.g2d.ui.listeners;

import org.lwjgl.glfw.GLFW;

import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.States;
import com.kronos.graphixs.g2d.ui.UIListener;
import com.kronos.graphixs.g2d.ui.listeners.adapter.InteractionType;
import com.kronos.graphixs.g2d.ui.listeners.adapter.MouseEvents;
import com.kronos.io.InputHandler;

public class MouseEventListener implements UIListener {
	boolean hovering = false;
	MouseEvents event;

	public MouseEventListener(MouseEvents event) {
		super();
		this.event = event;
	}

	@Override
	public void listen(BaseComponent bc, States states) {
		int mx = (int) InputHandler.getLastMouseX();
		int my = (int) InputHandler.getLastMouseY();

		if (bc.getPosition().pos().contains(mx, my) && InputHandler.isLeftReleased()) {
			event.clicked(InteractionType.LEFT_CLICK);
		}
		if (bc.getPosition().pos().contains(mx, my) && InputHandler.isRightReleased()) {
			event.clicked(InteractionType.RIGHT_CLICK);
		}
		if (bc.getPosition().pos().contains(mx, my) && InputHandler.isKeyReleased(GLFW.GLFW_KEY_SPACE)) {
			event.clicked(InteractionType.KEYBOARD);
		}
		if (bc.getPosition().pos().contains(mx, my)) {
			hovering = true;
			event.hover(mx, my);
		} else if (hovering && !bc.getPosition().pos().contains(mx, my)) {
			hovering = false;
			event.exited();
		}

	}

}
