package com.kronos.graphixs.internal;

import org.joml.Vector3f;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.geometry.meshing.DataBuffer;
import com.kronos.graphixs.geometry.meshing.QuadBuilder;

public class Cube {
	int x, y, z, size;
	Color c;

	public Cube(int x, int y, int z, int size, Color c) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		this.c = c;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the c
	 */
	public Color getC() {
		return c;
	}

	public Vector3f get() {
		return c.asVector3f();
	}

	public void applyVerts(BasicMeshBuilder buffer) {
		int i = buffer.getIndex();

		up(i += 1).add(buffer);

		i = buffer.getIndex();
		down(i += 1).add(buffer);

		i = buffer.getIndex();
		right(i += 1).add(buffer);

		i = buffer.getIndex();
		left(i += 1).add(buffer);

		i = buffer.getIndex();
		front(i += 1).add(buffer);

		i = buffer.getIndex();
		back(i += 1).add(buffer);

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cube [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append(", size=");
		builder.append(size);
		builder.append(", ");
		if (c != null) {
			builder.append("c=");
			builder.append(c);
		}
		builder.append("]");
		return builder.toString();
	}

	public DataBuffer down(int s) {
		DataBuffer buf = new DataBuffer();

		Vector3f v1 = new Vector3f(x + size, y, z);
		Vector3f v2 = new Vector3f(x + size, y, z + size);
		Vector3f v3 = new Vector3f(x, y, z + size);
		Vector3f v4 = new Vector3f(x, y, z);
		Vector3f norm = new Vector3f(0, 1, 0);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));

		return buf;
	}

	public DataBuffer up(int s) {
		DataBuffer buf = new DataBuffer();
		Vector3f v1 = new Vector3f(x + size, y + size, z);
		Vector3f v2 = new Vector3f(x + size, y + size, z + size);
		Vector3f v3 = new Vector3f(x, y + size, z + size);
		Vector3f v4 = new Vector3f(x, y + size, z);
		Vector3f norm = new Vector3f(0, -1, 0);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));
		return buf;
	}

	public DataBuffer left(int s) {
		DataBuffer buf = new DataBuffer();
		Vector3f v1 = new Vector3f(x, y + size, z + size);
		Vector3f v2 = new Vector3f(x, y, z + size);
		Vector3f v3 = new Vector3f(x, y, z);
		Vector3f v4 = new Vector3f(x, y + size, z);
		Vector3f norm = new Vector3f(1, 0, 0);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));
		return buf;
	}

	public DataBuffer right(int s) {
		DataBuffer buf = new DataBuffer();
		Vector3f v1 = new Vector3f(x + size, y + size, z + size);
		Vector3f v2 = new Vector3f(x + size, y, z + size);
		Vector3f v3 = new Vector3f(x + size, y, z);
		Vector3f v4 = new Vector3f(x + size, y + size, z);
		Vector3f norm = new Vector3f(-1, 0, 0);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));
		return buf;
	}

	public DataBuffer front(int s) {
		DataBuffer buf = new DataBuffer();
		Vector3f v1 = new Vector3f(x + size, y + size, z + size);
		Vector3f v2 = new Vector3f(x + size, y, z + size);
		Vector3f v3 = new Vector3f(x, y, z + size);
		Vector3f v4 = new Vector3f(x, y + size, z + size);
		Vector3f norm = new Vector3f(0, 0, -1);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));
		return buf;
	}

	public DataBuffer back(int s) {
		DataBuffer buf = new DataBuffer();
		Vector3f v1 = new Vector3f(x + size, y + size, z);
		Vector3f v2 = new Vector3f(x + size, y, z);
		Vector3f v3 = new Vector3f(x, y, z);
		Vector3f v4 = new Vector3f(x, y + size, z);
		Vector3f norm = new Vector3f(0, 0, 1);
		buf.addVertex(v1, get(), norm);
		buf.addVertex(v2, get(), norm);
		buf.addVertex(v3, get(), norm);
		buf.addVertex(v4, get(), norm);
		buf.addAll(QuadBuilder.outToList(s));
		return buf;
	}
}
