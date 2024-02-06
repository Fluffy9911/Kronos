/**
 * 
 */
package com.kronos.graphixs.g3d;

import org.joml.Vector3f;

import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.material.Material;

/**
 * 
 */
public class PointLight extends Object3D {
	Vector3f color, position, direction;
	float strength;

	public PointLight(Material mat, Mesh mesh, Vector3f color, Vector3f position, Vector3f direction, float strength) {
		super(mat, mesh);
		this.color = color;
		this.position = position;
		this.direction = direction;
		this.strength = strength;
	}

	public Vector3f getColor() {
		return color;
	}

	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public Vector3f getDirection() {
		return direction;
	}

	public float getStrength() {
		return strength;
	}

}
