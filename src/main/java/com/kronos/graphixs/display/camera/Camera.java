package com.kronos.graphixs.display.camera;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

public class Camera {
	Vector3f position;
	Vector3f look;
	Matrix4f view, projection;
	float near, far;
	int width, height;
	private static final float FOV = (float) Math.toRadians(60.0f);
	private static final float Z_FAR = 1000.f;
	private static final float Z_NEAR = 0.01f;
	private float yaw = 0.0f; // Yaw rotation around the Y-axis
	private float pitch = 0.0f; // Pitch rotation around the X-axis

	public Camera(Vector3f position, Vector2f look, float near, float far, int width, int height) {
		this.position = position;
		this.look = new Vector3f(look.x, look.y, 0);
		this.near = near;
		this.far = far;
		this.width = width;
		this.height = height;
		view = new Matrix4f();
		projection = new Matrix4f();
	}

	public Matrix4f perspective(float fov) {
		Matrix4f m = new Matrix4f();
		m.perspective(fov, width / height, near, far);
		m.lookAt(position, new Vector3f(look.x, look.y, 0), new Vector3f(0, 1, 0));
		view = m;
		projection = new Matrix4f();
		return m;
	}

	public Matrix4f lookAt(Vector3f pos) {
		if (view != null) {
			look = pos;
			view.identity();
			view.lookAt(position, pos, new Vector3f(0, 1, 0));
			return view;
		}
		return new Matrix4f();
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @return the look
	 */
	public Vector3f getLook() {
		return look;
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
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the fov
	 */
	public static float getFov() {
		return FOV;
	}

	/**
	 * @return the zFar
	 */
	public static float getzFar() {
		return Z_FAR;
	}

	/**
	 * @return the zNear
	 */
	public static float getzNear() {
		return Z_NEAR;
	}

	public Matrix4f getProjection(int width, int height) {
		return projection.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
	}

	public void move(float x, float y, float z) {
		look.add(x, y, z);
		lookAt(new Vector3f(look.x, look.y, z));
	}

	public void movePosition(float x, float y, float z) {
		position.add(x, y, z);
		lookAt(new Vector3f(look.x, look.y, 0));

	}

	public void translate(float deltaX, float deltaY, float deltaZ) {
		look.add(deltaX, deltaY, deltaZ);
		lookAt(look);
	}

	// New method for rotating the camera view matrix around the camera's position
	public void rotate(float angle, float axisX, float axisY, float axisZ) {
		view.rotate(angle, axisX, axisY, axisZ);
	}

	// New method for updating camera movements and rotations
	public void update() {
		updateMovement();
		updateRotation();
	}

	// New method for handling camera rotation based on input
	public void updateRotation() {
		float rotateSpeed = 0.01f; // You can adjust this value

		if (InputHandler.isKeyPressed(Keys.LEFT)) {
			yaw -= rotateSpeed; // Rotate left
		}
		if (InputHandler.isKeyPressed(Keys.RIGHT)) {
			yaw += rotateSpeed; // Rotate right
		}
		if (InputHandler.isKeyPressed(Keys.UP)) {
			pitch += rotateSpeed; // Rotate left
		}
		if (InputHandler.isKeyPressed(Keys.DOWN)) {
			pitch -= rotateSpeed; // Rotate right
		}
		// Clamp pitch to avoid flipping
		pitch = (float) Math.max(-Math.PI / 2.0f, Math.min(Math.PI / 2.0f, pitch));

		// Update the view matrix based on the new orientation
		updateViewMatrix();
	}

	private void updateViewMatrix() {
		// Calculate the direction vector based on yaw and pitch
		Vector3f direction = new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();

		// Calculate the target point based on the current position and direction
		Vector3f target = new Vector3f(position).add(direction);

		// Update the view matrix
		view.identity().lookAt(position, target, new Vector3f(0, 1, 0));
	}

	public void updateMovement() {
		float moveSpeed = 0.1f; // You can adjust this value

		Vector3f moveVector = new Vector3f();

		if (InputHandler.isKeyPressed(Keys.A)) {
			moveVector.add(-moveSpeed, 0, 0);
		}
		if (InputHandler.isKeyPressed(Keys.D)) {
			moveVector.add(moveSpeed, 0, 0);
		}
		if (InputHandler.isKeyPressed(Keys.W)) {
			moveVector.add(0, 0, -moveSpeed);
		}
		if (InputHandler.isKeyPressed(Keys.S)) {
			moveVector.add(0, 0, moveSpeed);
		}
		if (InputHandler.isKeyPressed(Keys.SPACE)) {
			moveVector.add(0, moveSpeed, 0);
		}
		if (InputHandler.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			moveVector.add(0, -moveSpeed, 0);
		}
		// Apply the translation to the camera's position
		position.add(moveVector);
		look.add(moveVector.x, moveVector.y, 0);

		// Update the view matrix
		lookAt(look);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Camera [");
		if (position != null) {
			builder.append("position=");
			builder.append(position);
			builder.append(", ");
		}
		if (look != null) {
			builder.append("look=");
			builder.append(look);
			builder.append(", ");
		}
		if (view != null) {
			builder.append("view=");
			builder.append(view.toString());
			builder.append(", ");
		}
		if (projection != null) {
			builder.append("projection=");
			builder.append(projection.toString());
			builder.append(", ");
		}
		builder.append("near=");
		builder.append(near);
		builder.append(", far=");
		builder.append(far);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}

}
