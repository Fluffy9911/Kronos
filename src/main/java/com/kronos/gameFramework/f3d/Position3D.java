/**
 * 
 */
package com.kronos.gameFramework.f3d;

import org.joml.Vector3f;

/**
 * 
 */
public class Position3D {
	Vector3f min, max;
	float sx, sy, sz;

	public Position3D(Vector3f min, Vector3f max) {
		super();
		this.min = min;
		this.max = max;
		setupScale();
	}

	public Position3D(Vector3f min, float sx, float sy, float sz) {
		super();
		this.min = min;
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
		max = new Vector3f(min.x + sx, min.y + sy, min.z + sz);
	}

	public void setupScale() {
		sx = max.x - min.x;
		sy = max.y - min.y;
		sz = max.z - min.z;
	}

	public Vector3f getMin() {
		return min;
	}

	public Vector3f getMax() {
		return max;
	}

	public float getSx() {
		return sx;
	}

	public float getSy() {
		return sy;
	}

	public float getSz() {
		return sz;
	}

}
