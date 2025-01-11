/**
 * 
 */
package com.kronos.graphixs.geometry.adapter;

import org.joml.Matrix4f;

/**
 * 
 */
public class MatrixAdpater {
	private static final String MATRIX_DELIMITER = ",";

	public static String toMatrix4f(Matrix4f matrix) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sb.append(matrix.get(i, j));
				if (i != 3 || j != 3) {
					sb.append(MATRIX_DELIMITER);
				}
			}
		}
		return sb.toString();
	}

	public static Matrix4f fromMatrix4f(String matrixString) {
		String[] values = matrixString.split(MATRIX_DELIMITER);
		if (values.length != 16) {
			throw new IllegalArgumentException("Invalid number of values for Matrix4f");
		}

		Matrix4f matrix = new Matrix4f();
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrix.set(i, j, Float.parseFloat(values[index++]));
			}
		}
		return matrix;
	}
}
