/**
 * 
 */
package com.kronos.gameFramework.f2d;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.kronos.dynamo.simple.Bounds2D;

/**
 * 
 */
public class Position2D extends Bounds2D {
	public Matrix4f trans;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Position2D(int x, int y, int width, int height) {
		super(x, y, width, height);
		trans = new Matrix4f();
	}

	public int x() {
		// TODO Auto-generated method stub
		return (int) super.getX();
	}

	public int y() {
		// TODO Auto-generated method stub
		return (int) super.getY();
	}

	public int width() {
		// TODO Auto-generated method stub
		return (int) super.getWidth();
	}

	public int height() {
		// TODO Auto-generated method stub
		return (int) super.getHeight();
	}

	public void moveToTarget() {

		this.translateBox2D(target.x, target.y);
		this.target = new Vector2f();
	}

	public void applyFutureForce(float x, float y) {
		this.target.add(x, y);
	}

}
