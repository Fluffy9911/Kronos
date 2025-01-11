/**
 * 
 */
package com.kronos.graphixs.geometry.adapter;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * 
 */
public class VectorAdapter {

	public static String[] fromVec3f(Vector3f v) {
		return new String[] { Float.toString(v.x), Float.toString(v.y), Float.toString(v.z) };
	}

	public static Vector3f toVec3f(String[] values) {
		if (values.length != 3) {
			throw new IllegalArgumentException("Invalid number of values for Vec3f");
		}
		float x = Float.parseFloat(values[0]);
		float y = Float.parseFloat(values[1]);
		float z = Float.parseFloat(values[2]);
		return new Vector3f(x, y, z);
	}

	// Vec4f
	public static String[] fromVec4f(Vector4f v) {
		return new String[] { Float.toString(v.x), Float.toString(v.y), Float.toString(v.z), Float.toString(v.w) };
	}

	public static Vector4f toVec4f(String[] values) {
		if (values.length != 4) {
			throw new IllegalArgumentException("Invalid number of values for Vec4f");
		}
		float x = Float.parseFloat(values[0]);
		float y = Float.parseFloat(values[1]);
		float z = Float.parseFloat(values[2]);
		float w = Float.parseFloat(values[3]);
		return new Vector4f(x, y, z, w);
	}

	// Vec2f
	public static String[] fromVec2f(Vector2f v) {
		return new String[] { Float.toString(v.x), Float.toString(v.y) };
	}

	public static Vector2f toVec2f(String[] values) {
		if (values.length != 2) {
			throw new IllegalArgumentException("Invalid number of values for Vec2f");
		}
		float x = Float.parseFloat(values[0]);
		float y = Float.parseFloat(values[1]);
		return new Vector2f(x, y);
	}
}
