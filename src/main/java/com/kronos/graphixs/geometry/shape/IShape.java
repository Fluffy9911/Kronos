/**
 * 
 */
package com.kronos.graphixs.geometry.shape;

import org.joml.Vector2i;

/**
 * 
 */
public interface IShape {
	public int getX();

	public int getY();

	public int getWidth();

	public int getHeight();

	public int setX(int x);

	public int setY(int y);

	public int setWidth(int w);

	public int setHeight(int h);

	public Vector2i getCenter();
}
