package com.kronos.graphixs.g2d;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.kronos.Kronos;
import com.kronos.core.util.SListener;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.InputHandler;

public class Cam2D implements SListener {
	Matrix4f projection, view, model;

	Vector3f position;

	float sw, sh;

	float near, far;
	float zoom = 1;

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

		position = new Vector3f(sw / 2, sh / 2, 0);

		registerEvent();
	}

	public void update() {
		if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_UP)) {
			zoom += 0.25f;
		}
		if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_DOWN)) {
			zoom -= 0.25f;
		}
		projection.identity();
		view.identity();

		projection.ortho2DLH(0, sw, sh, 0);
		projection.scale(zoom);
		view.setTranslation(position);

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

	/**
	 * @return the projection
	 */
	public Matrix4f getProjection() {
		return projection;
	}

	/**
	 * @param projection the projection to set
	 */
	public void setProjection(Matrix4f projection) {
		this.projection = projection;
	}

	/**
	 * @return the view
	 */
	public Matrix4f getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Matrix4f view) {
		this.view = view;
	}

	/**
	 * @return the model
	 */
	public Matrix4f getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Matrix4f model) {
		this.model = model;
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @return the sw
	 */
	public float getSw() {
		return sw;
	}

	/**
	 * @param sw the sw to set
	 */
	public void setSw(float sw) {
		this.sw = sw;
	}

	/**
	 * @return the sh
	 */
	public float getSh() {
		return sh;
	}

	/**
	 * @param sh the sh to set
	 */
	public void setSh(float sh) {
		this.sh = sh;
	}

	/**
	 * @return the near
	 */
	public float getNear() {
		return near;
	}

	/**
	 * @param near the near to set
	 */
	public void setNear(float near) {
		this.near = near;
	}

	/**
	 * @return the far
	 */
	public float getFar() {
		return far;
	}

	/**
	 * @param far the far to set
	 */
	public void setFar(float far) {
		this.far = far;
	}

	/**
	 * @return the zoom
	 */
	public float getZoom() {
		return zoom;
	}

	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

}
