/**
 * 
 */
package com.kronos.graphixs.display.camera;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.kronos.Kronos;
import com.kronos.gameFramework.templates.Transform;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

/**
 * 
 */
public class Camera extends Transform {

	protected float fov;
	protected float zoom;
	protected float near;
	protected float far;
	protected int screen_width = Kronos.config.getCurrent().width();
	protected int screen_height = Kronos.config.getCurrent().height();
	protected Matrix4f view;
	protected Matrix4f projection;
	protected Matrix4f model;
	protected boolean usemouspos = true;
	protected boolean mlock = false;
	protected boolean move = false;
	protected int lastX = 0;
	protected int lastY = 0;

	public static final float SENSITIVITY = 0.025f;
	protected float coeff = 0.5f;

	public Camera() {
		super(new Vector3f(0, 0, 0));
		this.position = new Vector3f(0, 0, 0);
		this.up = new Vector3f(0, 1, 0);
		this.looking = new Vector3f(0, -1, 0);
		fov = 90;
		yaw = 0;
		pitch = 0;
		zoom = 1;
		near = 1;
		far = 1000;
		this.velocity = 1.5f;
		resetMatricies();

	}

	public Camera(Vector3f position, Vector3f up, Vector3f lookat, float fov, float yaw, float pitch, float zoom,
			float near, float far) {
		super(position);
		this.position = position;
		this.up = up;
		this.looking = lookat;
		this.fov = fov;
		this.yaw = yaw;
		this.pitch = pitch;
		this.zoom = zoom;
		this.near = near;
		this.far = far;
		this.velocity = 1.5f;
	}

	public void calculatePositioning(int width, int height) {
		if (move) {
			resetMatricies();

//			System.out.println(width + ":" + height);
			projection.perspective(fov, (width / height), near, far);

			updateViewMatrix();

			model.scale(zoom);
		}
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
			move = true;
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
		looking = target;

		view.identity().lookAt(position, target, new Vector3f(0, 1, 0));
	}

	/**
	 * @return the lookat
	 */
	public Vector3f getLookat() {
		return looking;
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
	@Override
	public float getYaw() {
		return yaw;
	}

	/**
	 * @return the pitch
	 */
	@Override
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

	public boolean isMoved() {
		return move;
	}

	public void setMoved(boolean move) {
		this.move = move;
	}

}