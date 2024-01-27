package com.kronos.dynamo.simple;

import org.joml.Vector3f;

public class MathLerp {

	public static float lerpSmooth(float target, float value, float iv) {
		value += (target - value) * iv;
		return value;
	}

	public static Vector3f lerpSmooth(Vector3f target, Vector3f value, float iv) {
		float x = lerpSmooth(target.x, value.x, iv);
		float y = lerpSmooth(target.y, value.y, iv);
		float z = lerpSmooth(target.z, value.z, iv);

		return value.add(x, y, z);
	}

	public static double lerpSmooth(double target, double value, double iv) {
		value += (target - value) * iv;
		return value;
	}

	public static int lerpSmooth(int target, int value, int iv) {
		value += (target - value) * iv;
		return value;
	}

	public static float lerpSpring(float speed, float target, float value) {
		speed = lerpSmooth(speed, (target - value) * .5f, 0.2f);
		target += speed;
		return target;
	}

	public static double lerpSpring(double speed, double target, double value) {
		speed = lerpSmooth(speed, (target - value) * 0.5, 0.2);
		target += speed;
		return target;
	}

	public static int lerpSpring(int speed, int target, int value) {
		speed = (int) lerpSmooth(speed, (target - value) * 0.5, 0.2);
		target += speed;
		return target;
	}

	public static double easeInOut(double t, double start, double end) {
		t = Math.max(0, Math.min(1, t));
		return start + (end - start) * (-Math.cos(t * Math.PI) / 2 + 0.5);
	}

	public static float easeInOut(float t, float start, float end) {
		t = Math.max(0, Math.min(1, t));
		return (float) (start + (end - start) * (-Math.cos(t * Math.PI) / 2 + 0.5));
	}

	public static double easeInQuad(double t, double start, double end) {
		t = Math.max(0, Math.min(1, t));
		return start + (end - start) * t * t;
	}

	public static float easeOutCubic(float t, float start, float end) {
		t = Math.max(0, Math.min(1, t));
		t--;
		return start + (end - start) * (t * t * t + 1);
	}

}
