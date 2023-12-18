package com.kronos.graphixs.g2d;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.kronos.graphixs.display.ScreenConfig;

/**
 * Converts cordinates to opengl cords and does all the math
 */
public class ScreenProvider {

	Cam2D cam;

	ScreenConfig config;

	public ScreenProvider(ScreenConfig config) {
		super();
		this.config = config;
		cam = new Cam2D(new Vector3f(0, 0, 1), config.width(), config.height(), 0, 0);
		cam.update();
	}

	public Matrix4f collectTransform() {
		return cam.collectTransform();
	}

	public void update() {
		cam.update();
	}

}
