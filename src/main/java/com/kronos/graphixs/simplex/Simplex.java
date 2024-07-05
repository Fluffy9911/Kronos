/**
 * 
 */
package com.kronos.graphixs.simplex;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;

/**
 * 
 */
public class Simplex {
	private static Matrix4f transform = new Matrix4f(), last = new Matrix4f();
	private static float s = 0, tx = 0, ty = 0;
	private static Color clear, col = new Color(1, 1, 1, 1);
	private static int fps = 0;
	private static TextureBatch batch;

	public static void init() {
		batch = Kronos.graphixs.g2d.createBatch(Kronos.graphixs.g2d);
		clear = new Color(0, 0, 0, 1);
	}

	public static void scale(float scale) {
		s += scale;
	}

	public static void translate(float x, float y) {
		tx += x;
		ty += y;
	}

	public static void push() {
		last = transform;
		transform.translate(tx, ty, 0);
		transform.scale(s);
	}

	public static void pop() {
		transform = last;
	}

	public static Color getClear() {
		return clear;
	}

	public static void setClear(Color clear) {
		Simplex.clear = clear;
	}

	public static int getFps() {
		return fps;
	}

	public static void setFps(int fps) {
		Simplex.fps = fps;
	}

	public static void drawLine(int x1, int y1, int x2, int y2) {
		GL11.glColor3f(col.getR(), col.getG(), col.getB());
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glEnd();
	}

	public static void drawLine(int x1, int y1, int x2, int y2, Color c) {
		GL11.glColor3f(c.getR(), c.getG(), c.getB());
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glEnd();
	}

	public static void drawLine(int x1, int y1, int x2, int y2, Color c1, Color c2) {
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(c1.getR(), c1.getG(), c1.getB());
		GL11.glVertex2i(x1, y1);
		GL11.glColor3f(c2.getR(), c2.getG(), c2.getB());
		GL11.glVertex2i(x2, y2);
		GL11.glEnd();
	}

	public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		GL11.glColor3f(col.getR(), col.getG(), col.getB());
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glVertex2i(x3, y3);
		GL11.glEnd();
	}

	public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color c) {
		GL11.glColor3f(c.getR(), c.getG(), c.getB());
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glVertex2i(x3, y3);
		GL11.glEnd();
	}

	public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color c1, Color c2, Color c3) {
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glColor3f(c1.getR(), c1.getG(), c1.getB());
		GL11.glVertex2i(x1, y1);
		GL11.glColor3f(c2.getR(), c2.getG(), c2.getB());
		GL11.glVertex2i(x2, y2);
		GL11.glColor3f(c3.getR(), c3.getG(), c3.getB());
		GL11.glVertex2i(x3, y3);
		GL11.glEnd();
	}

	public static void drawSquare(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		GL11.glColor3f(col.getR(), col.getG(), col.getB());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glVertex2i(x3, y3);
		GL11.glVertex2i(x4, y4);
		GL11.glEnd();
	}

	public static void drawSquare(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color c) {
		GL11.glColor3f(c.getR(), c.getG(), c.getB());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(x1, y1);
		GL11.glVertex2i(x2, y2);
		GL11.glVertex2i(x3, y3);
		GL11.glVertex2i(x4, y4);
		GL11.glEnd();
	}

	public static void drawSquare(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color c1, Color c2,
			Color c3, Color c4) {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(c1.getR(), c1.getG(), c1.getB());
		GL11.glVertex2i(x1, y1);
		GL11.glColor3f(c2.getR(), c2.getG(), c2.getB());
		GL11.glVertex2i(x2, y2);
		GL11.glColor3f(c3.getR(), c3.getG(), c3.getB());
		GL11.glVertex2i(x3, y3);
		GL11.glColor3f(c4.getR(), c4.getG(), c4.getB());
		GL11.glVertex2i(x4, y4);
		GL11.glEnd();
	}

	public static void drawRect(int x, int y, int w, int h, Color c) {
		drawSquare(x, y, x + w, y, x + w, y + h, x, y + h, c);
	}

	public static void drawText(int x, int y, String text, Color col) {
		FontRenderer fr = FontRenderer.createDefault();
		fr.renderText(text, x, y, fr.useDefaultFont(), new java.awt.Color(col.getR(), col.getG(), col.getB(), 1),
				batch);

	}

	public static TextureBatch getBatch() {
		return batch;
	}

}
