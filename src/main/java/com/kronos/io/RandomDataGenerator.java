package com.kronos.io;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomDataGenerator {
	public static String generateRandomString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static int[] generateRandomIntArray(int size, int min, int max) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = RandomUtils.nextInt(min, max + 1);
		}
		return array;
	}

	public static boolean[] generateRandomBooleanArray(int size) {
		boolean[] array = new boolean[size];
		for (int i = 0; i < size; i++) {
			array[i] = RandomUtils.nextBoolean();
		}
		return array;
	}

	public static float[] generateRandomFloatArray(int size, float min, float max) {
		float[] array = new float[size];
		for (int i = 0; i < size; i++) {
			array[i] = RandomUtils.nextFloat(min, max);
		}
		return array;
	}

	// You can add similar methods for generating random arrays of other types if
	// needed
}
