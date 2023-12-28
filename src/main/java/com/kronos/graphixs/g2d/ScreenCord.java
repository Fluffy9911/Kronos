package com.kronos.graphixs.g2d;

import java.awt.Rectangle;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public class ScreenCord {

	float x, y, w, h;

	public ScreenCord(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		w = 0;
		h = 0;
	}

	public ScreenCord(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	public NormCord create(Matrix4f m) {
		Vector4f pos = new Vector4f(x, y, 0, 0);
		Vector4f s = new Vector4f(w, h, 0, 0);

		pos = m.transform(pos);
		s = m.transform(s);

		return new NormCord(pos.x, pos.y, s.x, s.y);

	}

	public boolean contains(int x, int y) {
		Rectangle rect = new Rectangle((int) this.x, (int) this.y, (int) w, (int) h);
		return rect.contains(x, y);

	}

}
