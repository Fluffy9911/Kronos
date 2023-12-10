package com.kronos.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;

public class InputHandler {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

	// Create a GLFWKeyCallback to handle key events
	private static GLFWKeyCallback keyCallback;

	static int released = 0;
	static double lastx = 0, lasty = 0;

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

}
