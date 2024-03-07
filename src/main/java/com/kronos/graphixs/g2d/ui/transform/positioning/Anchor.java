/**
 * 
 */
package com.kronos.graphixs.g2d.ui.transform.positioning;

import org.joml.Vector2i;

import com.kronos.graphixs.g2d.ui.BasePosition;

/**
 * 
 */
public class Anchor {

	private Vector2i point;

	boolean ignoreX = false, ignoreY = false;

	public Anchor(Vector2i point) {
		super();
		this.point = point;
	}

	public Anchor(Vector2i point, boolean ignoreX, boolean ignoreY) {
		super();
		this.point = point;
		this.ignoreX = ignoreX;
		this.ignoreY = ignoreY;
	}

	public void transform(Anchor a) {
		this.getPoint().add(a.getPoint());
	}

	public void transX(int x) {
		this.point.add(x, 0);
	}

	public void transY(int y) {
		this.point.add(0, y);
	}

	public Anchor negate() {
		return new Anchor(point.negate(new Vector2i()), ignoreX, ignoreY);
	}

	public Vector2i getPoint() {
		return point;
	}

	public boolean isIgnoreX() {
		return ignoreX;
	}

	public boolean isIgnoreY() {
		return ignoreY;
	}

	public static Anchor posOfComp(BasePosition bp) {
		return new Anchor(new Vector2i((int) bp.pos().getX(), (int) bp.pos().getY()), false, false);
	}

	public static Anchor posOfAnchor(BasePosition bp) {
		return new Anchor(new Vector2i((int) bp.anchoredPos().getX(), (int) bp.anchoredPos().getY()), false, false);
	}

}
