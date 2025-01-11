package com.kronos.io;

import java.awt.event.KeyEvent;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import com.kronos.Kronos;
import com.kronos.core.res.DataField;
import com.kronos.core.res.ResourceKey;

public class InputHandler {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

	// Create a GLFWKeyCallback to handle key events
	private static GLFWKeyCallback keyCallback;
	static HashMap<String, CharBuffer> caps = new HashMap<>();
	static int released = 0;
	static double lastx = 0, lasty = 0;
	static boolean mouseDownRight = false, mouseDownLeft = false;
	static boolean rightReleased = false, leftReleased = false;

	public static Map<String, Number> key_mapping = new HashMap<>();

	public static Map<String, KeyMapping> maps = new HashMap<>();

	// Initialize the key callback
	public static void init(long window) {

		keyCallback = GLFWKeyCallback.create((windowHandle, keyCode, scancode, action, mods) -> {
			for (Map.Entry<String, CharBuffer> entry : caps.entrySet()) {
				String key = entry.getKey();
				CharBuffer val = entry.getValue();
				if (action == GLFW.GLFW_RELEASE) {

					val.append(convertKeyCodeToChar(keyCode, false));
				}
			}
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
						rightReleased = true;
					}
					if (GLFW.GLFW_MOUSE_BUTTON_LEFT == button) {
						mouseDownLeft = false;
						leftReleased = true;
					}
				}
			}
		}));
	}

	public static void handleKeyMappings() {
		DataField<Map<String, Number>> df = new DataField<>(key_mapping,
				ResourceKey.kronos_base.childPath("key_mappings", "Keys"));
		key_mapping = df.get().getObj();
		Kronos.debug.getLogger().debug("Registered Keys: {}", key_mapping.toString());

		key_mapping.put("A", GLFW.GLFW_KEY_A);
		key_mapping.put("B", GLFW.GLFW_KEY_B);
		key_mapping.put("C", GLFW.GLFW_KEY_C);
		key_mapping.put("D", GLFW.GLFW_KEY_D);
		key_mapping.put("E", GLFW.GLFW_KEY_E);
		key_mapping.put("F", GLFW.GLFW_KEY_F);
		key_mapping.put("G", GLFW.GLFW_KEY_G);
		key_mapping.put("H", GLFW.GLFW_KEY_H);
		key_mapping.put("I", GLFW.GLFW_KEY_I);
		key_mapping.put("J", GLFW.GLFW_KEY_J);
		key_mapping.put("K", GLFW.GLFW_KEY_K);
		key_mapping.put("L", GLFW.GLFW_KEY_L);
		key_mapping.put("M", GLFW.GLFW_KEY_M);
		key_mapping.put("N", GLFW.GLFW_KEY_N);
		key_mapping.put("O", GLFW.GLFW_KEY_O);
		key_mapping.put("P", GLFW.GLFW_KEY_P);
		key_mapping.put("Q", GLFW.GLFW_KEY_Q);
		key_mapping.put("R", GLFW.GLFW_KEY_R);
		key_mapping.put("S", GLFW.GLFW_KEY_S);
		key_mapping.put("T", GLFW.GLFW_KEY_T);
		key_mapping.put("U", GLFW.GLFW_KEY_U);
		key_mapping.put("V", GLFW.GLFW_KEY_V);
		key_mapping.put("W", GLFW.GLFW_KEY_W);
		key_mapping.put("X", GLFW.GLFW_KEY_X);
		key_mapping.put("Y", GLFW.GLFW_KEY_Y);
		key_mapping.put("Z", GLFW.GLFW_KEY_Z);
		key_mapping.put("SPACE", GLFW.GLFW_KEY_SPACE);
		key_mapping.put("ENTER", GLFW.GLFW_KEY_ENTER);
		key_mapping.put("ESCAPE", GLFW.GLFW_KEY_ESCAPE);
		key_mapping.put("UP", GLFW.GLFW_KEY_UP);
		key_mapping.put("DOWN", GLFW.GLFW_KEY_DOWN);
		key_mapping.put("LEFT", GLFW.GLFW_KEY_LEFT);
		key_mapping.put("RIGHT", GLFW.GLFW_KEY_RIGHT);

		df.free().get();

		Kronos.debug.getLogger().debug("Registered Keys: {}", key_mapping.toString());

	}

	public static void nextFrame() {
		released = 0;
		rightReleased = false;
		leftReleased = false;
	}

	public static void registerKeyMapping(String id, KeyMapping km) {
		maps.put(id, km);
		Kronos.debug.getLogger().debug("Registered Key Mapping for name: {} with values?: {}, codes?: {} ", id,
				km.getMappings(), km.toCodes());
	}

	public static void createMapping(String id, String... m) {
		KeyMapping km = DataField.serializableField(id, new KeyMapping(m));
		maps.put(id, km);
		Kronos.debug.getLogger().debug("Registered Key Mapping for name: {} with values?: {}, codes?: {} ", id,
				km.getMappings(), km.toCodes());

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

	public static boolean isKeyPressed(String str) {

		if (!maps.containsKey(str)) {
			System.err.println("Error: Key mapping for \"" + str + "\" does not exist.");
			return false;
		}

		int[] arr = maps.get(str).toCodes();
		if (arr == null || arr.length == 0) {
			System.err.println("Error: No key codes found for \"" + str + "\".");
			return false;
		}

		for (int i = 0; i < arr.length; i++) {

			if (isKeyPressed(arr[i])) {

				return true;
			}
		}

		return false;
	}

	public static boolean isKeyReleased(String str) {

		if (!maps.containsKey(str)) {
			System.err.println("Error: Key mapping for \"" + str + "\" does not exist.");
			return false;
		}

		int[] arr = maps.get(str).toCodes();
		if (arr == null || arr.length == 0) {
			System.err.println("Error: No key codes found for \"" + str + "\".");
			return false;
		}

		for (int i = 0; i < arr.length; i++) {

			if (isKeyReleased(arr[i])) {

				return true;
			}
		}

		return false;
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

	/**
	 * @return the rightReleased
	 */
	public static boolean isRightReleased() {
		return rightReleased;
	}

	/**
	 * @return the leftReleased
	 */
	public static boolean isLeftReleased() {
		return leftReleased;
	}

	public void startKeyCapture(CharBuffer buffer) {

	}

	public static char convertKeyCodeToChar(int keyCode, boolean shiftPressed) {
		char[] chars = new char[2];
		KeyEvent keyEvent = new KeyEvent(null, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, keyCode,
				(char) keyCode);

		if (shiftPressed) {
			// If shift is pressed, convert to uppercase
			chars[0] = Character.toUpperCase(keyEvent.getKeyChar());
		} else {
			chars[0] = keyEvent.getKeyChar();
		}

		// Handle special characters

		// Create a CharBuffer with the converted character
		CharBuffer charBuffer = CharBuffer.wrap(chars);
		return charBuffer.get();
	}
}
