package com.kronos.graphixs.display.camera;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.kronos.Kronos;
import com.kronos.dynamo.simple.MathLerp;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

public class PerspectiveCamera {

	protected Vector3f position, up, lookat;

	protected float fov, yaw, pitch;
	protected float zoom;
	protected float near, far;
	protected int screen_width = Kronos.config.getCurrent().width();
	protected int screen_height = Kronos.config.getCurrent().height();
	Matrix4f view, projection, model;
//flags
	protected boolean usemouspos = true, mlock = false, move = false;;

	protected int lastX = 0, lastY = 0;
	protected float velocity = 1.5f;

	public static final float SENSITIVITY = 0.025f;

	protected float coeff = 0.5f;

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
		resetMatricies();

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
		resetMatricies();
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
		resetMatricies();
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
		resetMatricies();
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
		resetMatricies();
	}

	public void calculatePositioning(int width, int height) {
		resetMatricies();

		projection.perspective(fov, (width / height), near, far);

		updateViewMatrix();

		model.scale(zoom);

	}

	public void resetMatricies() {
		view = new Matrix4f();
		projection = new Matrix4f();
		model = new Matrix4f();
	}

	public void move(Vector3f mov) {
		position.add(mov);
		calculatePositioning(screen_width, screen_height);
		move = true;
	}

	public void yaw(float ya, float easing) {
		yaw = MathLerp.lerpSmooth(yaw + ya, yaw, easing);
		move = true;
	}

	public void pitch(float pi, float easing) {
		pitch = MathLerp.lerpSmooth(pitch + pi, pitch, easing);
		move = true;
	}

	/**
	 * Handles the cameras movement
	 */
	public void updateMovement() {

		if (InputHandler.isKeyPressed(Keys.A)) {

			float movementValue = (velocity * coeff);
			moveLeft(movementValue);
			move = true;

		}
		if (InputHandler.isKeyPressed(Keys.D)) {
			float movementValue = (velocity * coeff);
			moveRight(movementValue);
			move = true;
		}

		if (InputHandler.isKeyPressed(Keys.S)) {

			float movementValue = (velocity * coeff);
			moveBackward(movementValue);
			move = true;
		}
		if (InputHandler.isKeyPressed(Keys.W)) {
			float movementValue = (velocity * coeff);
			moveForeward(movementValue);
			move = true;
		}
		if (InputHandler.isKeyPressed(Keys.SPACE)) {
			float movementValue = (velocity * coeff);
			position.add(0, movementValue, 0);
			move = true;
		}
		if (InputHandler.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			float movementValue = (velocity * coeff);
			position.add(0, -movementValue, 0);
			move = true;
		}

	}

	public void updateRotation() {
		float yadd = 0;
		float padd = 0;
		if (!usemouspos) {
			float rotateSpeed = 0.01f; // You can adjust this value

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

			pitch(padd, 0.5f);
			yaw(yadd, 0.5f);

		} else {
			double mouseX = InputHandler.getLastMouseX();
			double mouseY = InputHandler.getLastMouseY();

			// Calculate the change in mouse position
			float deltaX = (float) mouseX - lastX;
			float deltaY = (float) mouseY - lastY;

			deltaX *= SENSITIVITY;
			deltaY *= SENSITIVITY;

			Vector3f mc = new Vector3f(deltaX, deltaY, 0);

			lastX = (int) mouseX;
			lastY = (int) mouseY;
			updateCameraRotation(deltaX, deltaY);
			mc = new Vector3f();
			calculatePositioning(screen_width, screen_height);
			if (InputHandler.isKeyReleased(Keys.L)) {
				mlock = !mlock;
			}
			if (mlock) {
				try {
					Robot r = new Robot();
					float cx = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
					float cy = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
					Vector2f mp = new Vector2f(cx, cy);

					r.mouseMove((int) mp.x, (int) mp.y);
				} catch (AWTException e) {

					e.printStackTrace();
				}

			}
		}

	}

	private void updateCameraRotation(float deltaX, float deltaY) {
		yaw(deltaX, 0.5f);
		pitch(deltaY, 0.5f);
	}

	private void updateViewMatrix() {

		Vector3f direction = new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();

		Vector3f target = new Vector3f(position).add(direction);
		lookat = target;

		view.identity().lookAt(position, target, new Vector3f(0, 1, 0));
	}

	public void update() {
		screen_width = Kronos.config.getCurrent().width();
		screen_height = Kronos.config.getCurrent().height();

		updateMovement();
		updateRotation();

		if (move) {
			calculatePositioning(screen_width, screen_height);
			move = false;
		}
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
		Vector3f t = getDirection();
		t.normalize().mul(amnt);
		position.add(t);
	}

	public void moveBackward(float amnt) {
		Vector3f t = getDirection();
		t.normalize().mul(amnt);
		position.sub(t);
	}

	public Vector3f getDirection() {
		// Calculate the direction vector based on yaw and pitch
		return new Vector3f((float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch),
				(float) (Math.sin(yaw) * Math.cos(pitch))).normalize();
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
		builder.append(screen_width);
		builder.append(", height=");
		builder.append(screen_height);
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

	/**
	 * @return the width
	 */
	public int getWidth() {
		return screen_width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.screen_width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return screen_height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.screen_height = height;
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

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

}
