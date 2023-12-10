package com.kronos.graphixs;

import org.joml.Vector3f;

public class Light {
	private Vector3f position;
	private Vector3f target;
	private Vector3f color;

	public Light(Vector3f position, Vector3f target, Vector3f color) {
		this.position = position;
		this.target = target;
		this.color = color;
	}

	// Getter and Setter methods for the position
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	// Getter and Setter methods for the target
	public Vector3f getTarget() {
		return target;
	}

	public void setTarget(Vector3f target) {
		this.target = target;
	}

	// Getter and Setter methods for the color
	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Light [position=" + position + ", target=" + target + ", color=" + color + "]";
	}
}
