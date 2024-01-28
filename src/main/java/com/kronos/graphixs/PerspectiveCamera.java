package com.kronos.graphixs;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.kronos.Kronos;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

public class PerspectiveCamera {

	Vector3f position, up, lookat;

	float fov, yaw, pitch;
	float zoom;
	float near, far;
	int width = Kronos.config.getCurrent().width();
	int height = Kronos.config.getCurrent().height();
	Matrix4f view, projection, model;

	private float velocity = 1.5f;

	private float coeff = 0.5f;

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat) {
		this.position = position;
		this.up = up;
		this.lookat = lookat;
		fov = 90;
		yaw = 0;
		pitch = 0;
		zoom = 1;
		near = 1;
		far = 1000;
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();

	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov) {
		this.position = position;
		this.up = up;
		this.lookat = lookat;
		this.fov = fov;
		yaw = 0;
		pitch = 0;
		zoom = 1;
		near = 1;
		far = 1000;
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov, float yaw, float pitch) {
		this.position = position;
		this.up = up;
		this.lookat = lookat;
		this.fov = fov;
		this.yaw = yaw;
		this.pitch = pitch;
		zoom = 1;
		near = 1;
		far = 1000;
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov, float yaw, float pitch,
			float zoom) {
		this.position = position;
		this.up = up;
		this.lookat = lookat;
		this.fov = fov;
		this.yaw = yaw;
		this.pitch = pitch;
		this.zoom = zoom;
		near = 1;
		far = 1000;
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov, float yaw, float pitch,
			float zoom, float near, float far) {
		this.position = position;
		this.up = up;
		this.lookat = lookat;
		this.fov = fov;
		this.yaw = yaw;
		this.pitch = pitch;
		this.zoom = zoom;
		this.near = near;
		this.far = far;
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
	}

	public void calculatePositioning(int width, int height) {
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
		projection.perspective(fov, (width / height), near, far);
		updateViewMatrix();
		model.scale(zoom);

//		view.rotate(yaw, 1, 0, 0);
//		view.rotate(pitch, 0, 1, 0);

	}

	public void move(Vector3f mov) {
		position.add(mov);
		calculatePositioning(width, height);
	}

	public void yaw(float ya, float easing) {
		yaw += ya;

		// Math.floor(yaw);
	}

	public void pitch(float pi, float easing) {
		pitch += pi;
		// Math.floor(pitch);
	}

	/**
	 * Handles the cameras movement
	 */
	public void updateMovement() {

		if (InputHandler.isKeyPressed(Keys.A)) {

			float movementValue = (velocity * coeff);
			moveLeft(movementValue);

		}
		if (InputHandler.isKeyPressed(Keys.D)) {
			float movementValue = (velocity * coeff);
			moveRight(movementValue);
		}

		if (InputHandler.isKeyPressed(Keys.S)) {

			float movementValue = (velocity * coeff);
			moveBackward(movementValue);

		}
		if (InputHandler.isKeyPressed(Keys.W)) {
			float movementValue = (velocity * coeff);
			moveForeward(movementValue);
		}
		if (InputHandler.isKeyPressed(Keys.SPACE)) {
			float movementValue = (velocity * coeff);
			position.add(0, movementValue, 0);
		}
		if (InputHandler.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			float movementValue = (velocity * coeff);
			position.add(0, -movementValue, 0);
		}

	}

	public void updateRotation() {
		float rotateSpeed = 0.01f; // You can adjust this value
		float yadd = 0;
		float padd = 0;
		if (InputHandler.isKeyPressed(Keys.LEFT)) {
			yadd -= rotateSpeed; // Rotate left
		}
		if (InputHandler.isKeyPressed(Keys.RIGHT)) {
			yadd += rotateSpeed; // Rotate right
		}
		if (InputHandler.isKeyPressed(Keys.UP)) {
			padd += rotateSpeed; // Rotate left
		}
		if (InputHandler.isKeyPressed(Keys.DOWN)) {
			padd -= rotateSpeed; // Rotate right
		}
		// Clamp pitch to avoid flipping
		// padd = (float) Math.max(-Math.PI / 2.0f, Math.min(Math.PI / 2.0f, pitch));
		pitch(padd, 0.5f);
		yaw(yadd, 0.5f);
		calculatePositioning(width, height);
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @return the up
	 */
	public Vector3f getUp() {
		return up;
	}

	/**
	 * @return the lookat
	 */
	public Vector3f getLookat() {
		return lookat;
	}

	/**
	 * @return the fov
	 */
	public float getFov() {
		return fov;
	}

	/**
	 * @return the yaw
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * @return the zoom
	 */
	public float getZoom() {
		return zoom;
	}

	/**
	 * @return the near
	 */
	public float getNear() {
		return near;
	}

	/**
	 * @return the far
	 */
	public float getFar() {
		return far;
	}

	/**
	 * @return the view
	 */
	public Matrix4f getView() {
		return view;
	}

	/**
	 * @return the projection
	 */
	public Matrix4f getProjection() {
		return projection;
	}

	/**
	 * @return the model
	 */
	public Matrix4f getModel() {
		return model;
	}

	public void dd() {
		if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_Q)) {
			System.out.println(toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerspectiveCamera [");
		if (position != null) {
			builder.append("position=");
			builder.append(position);
			builder.append(", ");
		}
		if (up != null) {
			builder.append("up=");
			builder.append(up);
			builder.append(", ");
		}
		if (lookat != null) {
			builder.append("lookat=");
			builder.append(lookat);
			builder.append(", ");
		}
		builder.append("fov=");
		builder.append(fov);
		builder.append(", yaw=");
		builder.append(yaw);
		builder.append(", pitch=");
		builder.append(pitch);
		builder.append(", zoom=");
		builder.append(zoom);
		builder.append(", near=");
		builder.append(near);
		builder.append(", far=");
		builder.append(far);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append(", ");
		if (view != null) {
			builder.append("view=");
			builder.append(view);
			builder.append(", ");
		}
		if (projection != null) {
			builder.append("projection=");
			builder.append(projection);
			builder.append(", ");
		}
		if (model != null) {
			builder.append("model=");
			builder.append(model);
			builder.append(", ");
		}
		if (getPosition() != null) {
			builder.append("getPosition()=");
			builder.append(getPosition());
			builder.append(", ");
		}
		if (getUp() != null) {
			builder.append("getUp()=");
			builder.append(getUp());
			builder.append(", ");
		}
		if (getLookat() != null) {
			builder.append("getLookat()=");
			builder.append(getLookat());
			builder.append(", ");
		}
		builder.append("getFov()=");
		builder.append(getFov());
		builder.append(", getYaw()=");
		builder.append(getYaw());
		builder.append(", getPitch()=");
		builder.append(getPitch());
		builder.append(", getZoom()=");
		builder.append(getZoom());
		builder.append(", getNear()=");
		builder.append(getNear());
		builder.append(", getFar()=");
		builder.append(getFar());
		builder.append(", ");
		if (getView() != null) {
			builder.append("getView()=");
			builder.append(getView());
			builder.append(", ");
		}
		if (getProjection() != null) {
			builder.append("getProjection()=");
			builder.append(getProjection());
			builder.append(", ");
		}
		if (getModel() != null) {
			builder.append("getModel()=");
			builder.append(getModel());
		}
		builder.append("]");
		return builder.toString();
	}

	private void updateViewMatrix() {
		// Calculate the direction vector based on yaw and pitch
		Vector3f direction = new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();

		// Calculate the target point based on the current position and direction
		Vector3f target = new Vector3f(position).add(direction);
		lookat = target;
		// Update the view matrix
		view.identity().lookAt(position, target, new Vector3f(0, 1, 0));
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(Vector3f up) {
		this.up = up;
	}

	/**
	 * @param lookat the lookat to set
	 */
	public void setLookat(Vector3f lookat) {
		this.lookat = lookat;
	}

	/**
	 * @param fov the fov to set
	 */
	public void setFov(float fov) {
		this.fov = fov;
	}

	/**
	 * @param yaw the yaw to set
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	/**
	 * @param pitch the pitch to set
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	/**
	 * @param near the near to set
	 */
	public void setNear(float near) {
		this.near = near;
	}

	/**
	 * @param far the far to set
	 */
	public void setFar(float far) {
		this.far = far;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Matrix4f view) {
		this.view = view;
	}

	/**
	 * @param projection the projection to set
	 */
	public void setProjection(Matrix4f projection) {
		this.projection = projection;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Matrix4f model) {
		this.model = model;
	}

	public void update() {
		width = Kronos.config.getCurrent().width();
		height = Kronos.config.getCurrent().height();
	}

	public void updateLR(float vel) {
		Vector3f ld = lookat;
		ld.normalize();
		ld.rotateX(90);
		ld.mul(vel);
		position.add(ld);

	}

	public void moveRight(float distance) {

		Vector3f right = new Vector3f();
		right.set(getDirection()).cross(up).normalize().mul(distance);
		position.add(right);
	}

	public void moveLeft(float distance) {

		Vector3f right = new Vector3f();
		right.set(getDirection()).cross(up).normalize().mul(-distance);
		position.add(right);
	}

	public void moveForeward(float amnt) {
		Vector3f t = lookat;
		t.normalize().mul(amnt);
		position.add(t);
	}

	public void moveBackward(float amnt) {
		Vector3f t = lookat;
		t.normalize().mul(-amnt);
		position.add(t);
	}

	public Vector3f getDirection() {
		// Calculate the direction vector based on yaw and pitch
		return new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();
	}

}
