package com.kronos.dynamo.simple;

import java.awt.Rectangle;

import org.joml.Vector2f;

public class Bounds2D {
	float x, y, width, height;
	float factor = 0.5f;
	Vector2f velocity = new Vector2f(0, 0);
	float friction = 0.25f;
	protected Vector2f target = new Vector2f();
	float previousX = 0, previousY = 0;
	Rectangle r;

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
		this.translateBox2D(velocity.x, velocity.y);
		this.velocity = new Vector2f();
	}

//	public void resolveForce() {
//		velocity.mul(friction);
//		MathLerp.lerpSmooth((velocity.x + x), x, factor);
//		MathLerp.lerpSmooth((velocity.y + y), y, factor);
//	}

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
		if (r == null) {
			r = new Rectangle((int) x, (int) y, (int) width, (int) height);
		} else {
			r.setBounds((int) x, (int) y, (int) width, (int) height);
		}

		return r;
	}

	public void resolveCollisionSolid(Bounds2D b) {

		Rectangle ot = b.toRect();

		// Calculate motion vector
		float dx = x - previousX;
		float dy = y - previousY;

		// Create swept AABB representing object's path
		Rectangle sweptRect = new Rectangle((int) Math.min(x, previousX), (int) Math.min(y, previousY),
				(int) (Math.abs(dx) + width), (int) (Math.abs(dy) + height));

		// Check for collisions along the path
		if (sweptRect.intersects(ot)) {
			float overlapX = Math.min(sweptRect.x + sweptRect.width, ot.x + ot.width) - Math.max(sweptRect.x, ot.x);
			float overlapY = Math.min(sweptRect.y + sweptRect.height, ot.y + ot.height) - Math.max(sweptRect.y, ot.y);

			if (overlapX < overlapY) { // Resolve horizontally first
				if (x < ot.x) { // Move to the left
					x -= overlapX;
				} else { // Move to the right
					x += overlapX;
				}
			} else { // Resolve vertically first
				if (y < ot.y) { // Move upwards
					y -= overlapY;
				} else { // Move downwards
					y += overlapY;
				}
			}
		}

		// Update previous position for next frame
		previousX = x;
		previousY = y;
	}

	public void resolveCollisionsNonSolid(Bounds2D b) {
		Rectangle th = this.toRect();
		Rectangle ot = b.toRect();

		if (th.intersects(ot)) {
			float overlapX = Math.min(x + width, b.x + b.width) - Math.max(x, b.x);
			float overlapY = Math.min(y + height, b.y + b.height) - Math.max(y, b.y);

			if (overlapX < overlapY) {
				if (x < b.x) {
					x -= overlapX / 2;
					b.x += overlapX / 2;
				} else {
					x += overlapX / 2;
					b.x -= overlapX / 2;
				}
			} else {
				if (y < b.y) {
					y -= overlapY / 2;
					b.y += overlapY / 2;
				} else {
					y += overlapY / 2;
					b.y -= overlapY / 2;
				}
			}
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

	public boolean canMove(Bounds2D b) {
		return !this.projectedRectangle(target).intersects(b.toRect());
	}
}
