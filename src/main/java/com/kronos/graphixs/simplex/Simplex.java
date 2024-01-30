/**
 * 
 */
package com.kronos.graphixs.simplex;

import org.joml.Matrix4f;

import com.kronos.graphixs.color.Color;

/**
 * 
 */
public class Simplex {
	private static Matrix4f transform = new Matrix4f(), last = new Matrix4f();
	private static float s = 0, tx = 0, ty = 0;
	private static Color clear;
	private static int fps = 0;

	public static void scale(float scale) {
		s += scale;
	}

	public static void translate(float x, float y) {
		tx += x;
		ty += y;
	}

	public static void push() {
		last = transform;
		transform.translate(tx, ty, 0);
		transform.scale(s);
	}

	public static void pop() {
		transform = last;
	}

	public static Color getClear() {
		return clear;
	}

	public static void setClear(Color clear) {
		Simplex.clear = clear;
	}

	public static int getFps() {
		return fps;
	}

	public static void setFps(int fps) {
		Simplex.fps = fps;
	}

}
