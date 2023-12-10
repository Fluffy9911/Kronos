package com.kronos.graphixs.resources;

public class IMath {

	public static int getMax(int... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		int max = i[0];
		for (int value : i) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public static float getMax(float... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		float max = i[0];
		for (float value : i) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public static long getMax(long... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		long max = i[0];
		for (long value : i) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public static int getMin(int... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		int min = i[0];
		for (int value : i) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

	public static float getMin(float... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		float min = i[0];
		for (float value : i) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

	public static long getMin(long... i) {
		if (i.length == 0) {
			throw new IllegalArgumentException("Input array is empty.");
		}
		long min = i[0];
		for (long value : i) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

	public static int between(int l, int h, int v) {
		return Math.max(l, Math.min(h, v));
	}

	public static float between(float l, float h, float v) {
		return Math.max(l, Math.min(h, v));
	}

	public static long between(long l, long h, long v) {
		return Math.max(l, Math.min(h, v));
	}
}
