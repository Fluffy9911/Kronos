package com.kronos.graphixs.g2d;

public class NormCord {
	float x, y, w, h;

	public NormCord(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		w = 0;
		h = 0;
	}

	public NormCord(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

}
