package com.kronos.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class InputHandler {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

	// Create a GLFWKeyCallback to handle key events
	private static GLFWKeyCallback keyCallback;

	static int released = 0;
	static double lastx = 0, lasty = 0;
	static boolean mouseDownRight = false, mouseDownLeft = false;

	// Initialize the key callback
	public static void init(long window) {
		keyCallback = GLFWKeyCallback.create((windowHandle, keyCode, scancode, action, mods) -> {
			if (action == GLFW.GLFW_PRESS) {
				keys[keyCode] = true;
			} else if (action == GLFW.GLFW_RELEASE) {
				keys[keyCode] = false;
				released = keyCode;
			}
		});

		// Set the key callback for the specified window
		GLFW.glfwSetKeyCallback(window, keyCallback);
		GLFW.glfwSetCursorPosCallback(window, GLFWCursorPosCallback.create(new GLFWCursorPosCallbackI() {

			@Override
			public void invoke(long window, double xpos, double ypos) {
				lastx = xpos;
				lasty = ypos;
			}
		}));
		GLFW.glfwSetMouseButtonCallback(window, GLFWMouseButtonCallback.create(new GLFWMouseButtonCallbackI() {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				if (action == GLFW.GLFW_PRESS) {
					if (GLFW.GLFW_MOUSE_BUTTON_RIGHT == button) {
						mouseDownRight = true;
					}
					if (GLFW.GLFW_MOUSE_BUTTON_LEFT == button) {
						mouseDownLeft = true;
					}
				} else if (action == GLFW.GLFW_RELEASE) {
					if (GLFW.GLFW_MOUSE_BUTTON_RIGHT == button) {
						mouseDownRight = false;
					}
					if (GLFW.GLFW_MOUSE_BUTTON_LEFT == button) {
						mouseDownLeft = false;
					}
				}
			}
		}));
	}

	public static void nextFrame() {
		released = 0;

	}

	// Static method to check if a key is pressed
	public static boolean isKeyPressed(int keyCode) {
		if (keyCode >= 0 && keyCode < keys.length) {
			return keys[keyCode];
		}
		return false;
	}

	public static boolean isKeyReleased(int keyCode) {
		return keyCode == released;
	}

	/**
	 * @return the lastx
	 */
	public static double getLastMouseX() {
		return lastx;
	}

	/**
	 * @return the lasty
	 */
	public static double getLastMouseY() {
		return lasty;
	}

	public static boolean mouseDownRight() {
		// TODO Auto-generated method stub
		return mouseDownRight;
	}

	public static boolean isMouseDownLeft() {
		return mouseDownLeft;
	}

}
