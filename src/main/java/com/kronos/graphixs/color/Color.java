package com.kronos.graphixs.color;

import org.joml.Vector3f;
import org.joml.Vector4f;

import com.kronos.graphixs.resources.IMath;

public class Color {
	float r, g, b, a;

	public Color(float r, float g, float b, float a) {
		setRGBA(r, g, b, a);
	}

	public Color(float r, float g, float b) {
		this(r, g, b, 1);
	}

	/**
	 * Set the color components (RGBA) while ensuring they are normalized.
	 */
	public void setRGBA(float r, float g, float b, float a) {
		this.r = IMath.between(0, 1, r);
		this.g = IMath.between(0, 1, g);
		this.b = IMath.between(0, 1, b);
		this.a = IMath.between(0, 1, a);
	}

	/**
	 * @return the r
	 */
	public float getR() {
		return r;
	}

	/**
	 * @return the g
	 */
	public float getG() {
		return g;
	}

	/**
	 * @return the b
	 */
	public float getB() {
		return b;
	}

	/**
	 * @return the a
	 */
	public float getA() {
		return a;
	}

	public Color add(Color other) {
		return new Color(r + other.r, g + other.g, b + other.b, a + other.a);
	}

	public Color subtract(Color other) {
		return new Color(r - other.r, g - other.g, b - other.b, a - other.a);
	}

	public Color multiply(Color other) {
		return new Color(r * other.r, g * other.g, b * other.b, a * other.a);
	}

	public Color divide(Color other) {
		// Check for division by zero
		if (other.r == 0 || other.g == 0 || other.b == 0 || other.a == 0) {
			throw new IllegalArgumentException("Division by zero is not allowed.");
		}
		return new Color(r / other.r, g / other.g, b / other.b, a / other.a);
	}

	public Vector3f asVector3f() {
		return new Vector3f(r, g, b);
	}

	public Vector4f asVector4f() {
		return new Vector4f(r, g, b, a);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Color [r=");
		builder.append(r);
		builder.append(", g=");
		builder.append(g);
		builder.append(", b=");
		builder.append(b);
		builder.append(", a=");
		builder.append(a);
		builder.append("]");
		return builder.toString();
	}

	public int rgb() {
		java.awt.Color co = new java.awt.Color(r, g, b);
		return co.getRGB();
	}

}
