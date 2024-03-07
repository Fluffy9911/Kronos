/**
 * 
 */
package com.kronos.graphixs.geometry.shape;

import org.joml.Vector2i;

/**
 * 
 */
public class ShapePosition {
	IShape shape;

	int trx, blx, brx;

	int topry, bly, bry;

	public ShapePosition(IShape shape) {
		super();
		this.shape = shape;
	}

	public Vector2i getTopRight() {
		return new Vector2i(trx, topry);
	}

	public Vector2i getBottomLeft() {
		return new Vector2i(blx, bly);
	}

	public Vector2i getBottomRight() {
		return new Vector2i(brx, bry);
	}

	public void setTopRight(int x, int y) {
		trx = x;
		topry = y;
		shape.setX(trx - shape.getWidth());
		shape.setY(y);
	}

	public void setBottomLeft(int x, int y) {
		blx = x;
		bly = y;
		shape.setY(topry - shape.getHeight());
		shape.setX(x);
	}

	public void setBottomRight(int x, int y) {
		brx = x;
		bry = y;
		shape.setX(brx - shape.getWidth());
		shape.setY(bry - shape.getHeight());
	}

}
