package com.kronos.graphixs.g2d;

import java.awt.Rectangle;

import org.joml.Matrix4f;
import org.joml.Vector2f;
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

	public Vector2f getPercentWH(float xper, float yper) {
		return new Vector2f(w * xper, h * yper);
	}

	public Vector2f getPercentPosition(float xper, float yper) {
		return getPercentWH(xper, yper).add(x, y);
	}

	/**
	 * @return
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, (int) w, (int) h);
	}

	/**
	 * @param pos
	 * @param px
	 * @param py
	 */
	public void setToCenterOf(ScreenCord pos, PositionX px, PositionY py) {
		if (px == PositionX.LEFT) {
			this.x = pos.x - (w / 2);
		} else if (px == PositionX.RIGHT) {
			this.x = pos.x + (pos.w / 2);
		}
		if (py == PositionY.UP) {
			this.y = pos.y;
		} else if (py == PositionY.DOWN) {
			this.y = pos.h - (this.h / 2);
		}

	}

	/**
	 * @param pos
	 */
	public void set(ScreenCord pos) {
		this.x = pos.x;
		this.y = pos.y;
		this.w = pos.w;
		this.h = pos.h;

	}

}
