/**
 * 
 */
package com.kronos.gameFramework.f2d;

import com.kronos.dynamo.simple.Bounds2D;

/**
 * 
 */
public class Position2D extends Bounds2D {

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Position2D(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
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

}
