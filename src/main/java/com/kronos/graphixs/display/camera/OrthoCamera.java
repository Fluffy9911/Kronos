package com.kronos.graphixs.display.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.kronos.Kronos;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

public class OrthoCamera {
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;

	private Vector3f position, eye, up;
	private float rotation;

	public OrthoCamera(Vector3f pos, Vector3f eye, Vector3f up) {
		projectionMatrix = new Matrix4f().setOrtho(0, Kronos.graphixs.getConfig().width(),
				Kronos.graphixs.getConfig().height(), 0, -5, 5);
		viewMatrix = new Matrix4f();
		this.eye = eye;
		this.up = up;
		this.position = pos;
		position = new Vector3f(0, 0, 0);
		rotation = 0.0f;
	}

	public void updateMovement() {
		if (InputHandler.isKeyPressed(Keys.A)) {
			eye.add(-1, 0, 0);
		}
		if (InputHandler.isKeyPressed(Keys.D)) {
			eye.add(1, 0, 0);
		}
		if (InputHandler.isKeyPressed(Keys.W)) {
			eye.add(0, 0, 1);
		}
		if (InputHandler.isKeyPressed(Keys.S)) {
			eye.add(0, 0, -1);
		}
		if (InputHandler.isKeyPressed(Keys.UP)) {
			eye.add(0, 1, 0);
		}
		if (InputHandler.isKeyPressed(Keys.DOWN)) {
			eye.add(0, -1, 0);
		}
		updateViewMatrix();
	}

	/**
	 * @return the projectionMatrix
	 */
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	/**
	 * @return the viewMatrix
	 */
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	private void updateViewMatrix() {

		viewMatrix.lookAt(eye, position.add(eye), up);
	}
}
