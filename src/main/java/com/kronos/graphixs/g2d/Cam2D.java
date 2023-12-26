package com.kronos.graphixs.g2d;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.kronos.Kronos;
import com.kronos.core.util.SListener;
import com.kronos.graphixs.display.ScreenConfig;

public class Cam2D implements SListener {
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
		registerEvent();
	}

	public void update() {

		projection.identity();
		view.identity();

		projection.ortho2DLH(0, sw, sh, 0);

	}

	public Matrix4f collectTransform() {
		return projection;
	}

	public Matrix4f view() {
		return view;
	}

	@Override
	public void updateSC(ScreenConfig sc) {
//		sw = sc.width();
//		sh = sc.height();
//		update();
		Kronos.debug.getLogger().debug("Camera Updated To: {} {}", sw, sh);

	}

}
