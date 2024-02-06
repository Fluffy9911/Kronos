/**
 * 
 */
package com.kronos.graphixs.g3d;

import org.joml.Vector3f;

import com.kronos.graphixs.geometry.material.Material;

/**
 * 
 */
public class Sphere {
	Material mat;
	Vector3f center;
	float radius;

	public Sphere(Material mat, Vector3f center, float radius) {
		super();
		this.mat = mat;
		this.center = center;
		this.radius = radius;
	}

	public Material getMat() {
		return mat;
	}

	public Vector3f getCenter() {
		return center;
	}

	public float getRadius() {
		return radius;
	}

}
