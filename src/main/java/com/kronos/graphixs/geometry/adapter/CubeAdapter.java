/**
 * 
 */
package com.kronos.graphixs.geometry.adapter;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.internal.Cube;

/**
 * 
 */
public class CubeAdapter {
	private static final String CUBE_DELIMITER = ";";
	private static final String VECTOR_DELIMITER = ",";

	public static String cubeToString(Cube cube) {
		StringBuilder sb = new StringBuilder();
		sb.append(cube.getX()).append(VECTOR_DELIMITER).append(cube.getY()).append(VECTOR_DELIMITER).append(cube.getZ())
				.append(VECTOR_DELIMITER).append(cube.getSize()).append(VECTOR_DELIMITER).append(cube.getC().getR())
				.append(VECTOR_DELIMITER).append(cube.getC().getG()).append(VECTOR_DELIMITER).append(cube.getC().getB())
				.append(CUBE_DELIMITER);

		return sb.toString();
	}

	public static Cube cubeFromString(String cubeString) {
		String[] values = cubeString.split(VECTOR_DELIMITER);
		if (values.length != 6) {
			throw new IllegalArgumentException("Invalid number of values for Cube");
		}

		int x = Integer.parseInt(values[0]);
		int y = Integer.parseInt(values[1]);
		int z = Integer.parseInt(values[2]);
		int size = Integer.parseInt(values[3]);
		float red = Float.parseFloat(values[4]);
		float green = Float.parseFloat(values[5]);
		float blue = Float.parseFloat(values[6]);
		Color c = new Color(red, green, blue);

		return new Cube(x, y, z, size, c);
	}

}
