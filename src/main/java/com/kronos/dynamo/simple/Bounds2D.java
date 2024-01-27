package com.kronos.dynamo.simple;

import java.awt.Rectangle;

import org.joml.Vector2f;

public class Bounds2D {
	float x, y, width, height;
	float factor = 0.5f;
	Vector2f velocity = new Vector2f(0, 0);
	float friction = 0.25f;

	public Bounds2D(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Vector2f getCenter() {
		return new Vector2f((width / 2) + x, (height / 2) + y);

	}

	public void moveCenterInstant(float mx, float my) {
		x += (mx / 2);
		y += (my / 2);
	}

	public void applyForce(Vector2f v) {
		velocity.add(v);
	}

	public void resolveForce() {
		velocity.mul(friction);
		MathLerp.lerpSmooth((velocity.x + x), x, factor);
		MathLerp.lerpSmooth((velocity.y + y), y, factor);
	}

	public float futureX(Vector2f v) {
		float fx = x;

		v.mul(friction);
		MathLerp.lerpSmooth((v.x + fx), fx, factor);
		return fx;
	}

	public float futureY(Vector2f v) {
		float fy = y;

		v.mul(friction);
		MathLerp.lerpSmooth((v.y + fy), fy, factor);
		return fy;
	}

	public boolean intersects(Bounds2D b2d) {
		return this.toRect().intersects(b2d.toRect());
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @return the factor
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * @return the velocity
	 */
	public Vector2f getVelocity() {
		return velocity;
	}

	/**
	 * @return the friction
	 */
	public float getFriction() {
		return friction;
	}

	public Rectangle toRect() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public void resolveCollision(Bounds2D b) {
		Rectangle th = this.toRect();
		Rectangle ot = b.toRect();
		Rectangle projected_rect = projectedRectangle(velocity);

		Vector2f direction = velocity;
		float xo = 0;
		float yo = 0;
		// adds/subs for pixel perfect
		if (projected_rect.intersects(ot)) {
			if (projected_rect.intersects(ot)) {
				if (direction.x > 0) {
					// right
					float x = ot.x;
					xo = ((th.x + th.width) - x);
				} else if (direction.x < 0) {
					// left
					float x = (ot.x + ot.width);
					xo = (th.x - x);
				}

				if (direction.y > 0) {
					// down
					float y = ot.y;
					yo = (y - (th.y + th.height));
				} else if (direction.y < 0) {
					// up
					yo = ((ot.y + ot.height) - th.y);
				}

				translateBox2D(xo, yo);
			}

		} else {
			return;
		}

	}

	public void translateBox2D(float xo, float yo) {
		x += xo;
		y += yo;
	}

	public Rectangle projectedRectangle(Vector2f target) {

		Rectangle projected_rect = new Rectangle((int) futureX(target), (int) futureY(target), (int) width,
				(int) height);

		return projected_rect;
	}
}
