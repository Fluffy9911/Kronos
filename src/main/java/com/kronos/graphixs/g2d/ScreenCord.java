package com.kronos.graphixs.g2d;

import java.awt.Rectangle;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.kronos.graphixs.g2d.ui.transform.PositionX;
import com.kronos.graphixs.g2d.ui.transform.PositionY;
import com.kronos.io.Config;

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

	public void translate(float f, float g) {
		this.x += f;
		this.y += g;
	}

	public void translateSize(float h2, float i) {
		this.w += h2;
		this.h += i;
	}

	public void put(Config c, String name) {
		c.appendFloat(name + "_X", x);
		c.appendFloat(name + "_Y", y);
		c.appendFloat(name + "_W", w);
		c.appendFloat(name + "_H", h);
	}

	public void read(Config c, String name) {
		x = c.readOrWriteFloat(name + "_X", x);
		y = c.readOrWriteFloat(name + "_Y", y);
		w = c.readOrWriteFloat(name + "_W", w);
		h = c.readOrWriteFloat(name + "_H", h);

	}

	public void scale(float factor) {
		this.w *= factor;
		this.h *= factor;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setW(float w) {
		this.w = w;
	}

	public void setH(float h) {
		this.h = h;
	}

	public void set(ScreenCord sc) {
		this.x = sc.x;
		this.y = sc.y;
		this.w = sc.w;
		this.h = sc.h;
	}

	public void setToCenterOf(ScreenCord other) {
		// Calculate the center coordinates of the other ScreenCord
		float otherCenterX = other.getX() + other.getW() / 2;
		float otherCenterY = other.getY() + other.getH() / 2;

		// Calculate the center coordinates of this ScreenCord
		float centerX = this.x + this.w / 2;
		float centerY = this.y + this.h / 2;

		// Calculate the difference between the centers
		float dx = otherCenterX - centerX;
		float dy = otherCenterY - centerY;

		// Translate this ScreenCord by the calculated differences
		this.translate(dx, dy);
	}

	public void setToCenterOf(ScreenCord other, PositionX positionX, PositionY positionY) {

		// Adjust based on X position enum
		switch (positionX) {
		case LEFT:
			this.setX(other.getX());
			break;
		case RIGHT:
			this.setX((other.getW() - this.getW()));
			break;
		case CENTER:
		default:
			// No adjustment needed for center alignment
			break;
		}

		// Adjust based on Y position enum
		switch (positionY) {
		case UP:
			this.setY(other.getY());
			break;
		case DOWN:
			this.setY((other.getH() - this.getH()));
			break;
		case CENTER:
		default:
			// No adjustment needed for center alignment
			break;
		}

		// Translate this ScreenCord by the calculated differences

	}

}
