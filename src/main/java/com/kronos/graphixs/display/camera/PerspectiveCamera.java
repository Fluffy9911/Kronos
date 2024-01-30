package com.kronos.graphixs.display.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class PerspectiveCamera extends Camera {

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat) {
		super();
		this.position = position;
		this.up = up;
		this.looking = lookat;
		fov = 90;
		yaw = 0;
		pitch = 0;
		zoom = 1;
		near = 1;
		far = 1000;
		resetMatricies();

	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov) {
		super();
		this.position = position;
		this.up = up;
		this.looking = lookat;
		this.fov = fov;
		yaw = 0;
		pitch = 0;
		zoom = 1;
		near = 1;
		far = 1000;
		resetMatricies();
	}

	public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f lookat, float fov, float yaw, float pitch) {
		super();
		this.position = position;
		this.up = up;
		this.looking = lookat;
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
		super();
		this.position = position;
		this.up = up;
		this.looking = lookat;
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
		super();
		this.position = position;
		this.up = up;
		this.looking = lookat;
		this.fov = fov;
		this.yaw = yaw;
		this.pitch = pitch;
		this.zoom = zoom;
		this.near = near;
		this.far = far;
		resetMatricies();
	}

	@Override
	public void update() {
//		screen_width = Kronos.config.getCurrent().width();
//		screen_height = Kronos.config.getCurrent().height();

		updateMovement();
		updateRotation();

		if (move) {
			calculatePositioning(getWidth(), getHeight());
			move = false;
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
		if (looking != null) {
			builder.append("lookat=");
			builder.append(looking);
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
		this.looking = lookat;
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

	@Override
	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

}
