package com.kronos.graphixs.g2d;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Cam2D {
	Matrix4f projection, view, model;

	Vector3f position;

	float sw, sh;

	float near, far;

	public Cam2D(Vector3f position, float sw, float sh, float near, float far) {
		super();
		this.position = position;
		this.sw = sw;
		this.sh = sh;
		this.near = near;
		this.far = far;

		projection = new Matrix4f();

		view = new Matrix4f();

		model = new Matrix4f();

	}

	public void update() {

		projection.identity();
		view.identity();

		projection.ortho2DLH(0, sw, sh, 0);

		view.lookAtLH(position, new Vector3f(sw / 2, sh / 2, 0), new Vector3f(0, 1, 0));

	}

	public Matrix4f collectTransform() {
		return projection.mul(view);
	}

}
