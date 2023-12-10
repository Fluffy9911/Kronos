package com.kronos.graphixs.geometry.meshing;

import java.util.LinkedList;
import java.util.List;

import org.joml.Vector3f;

public class VertexBuffer {
	LinkedList<Vector3f> verts, color, normal;

	public VertexBuffer() {
		verts = new LinkedList<>();
		color = new LinkedList<>();
		normal = new LinkedList<>();
	}

	public VertexBuffer addVertex(Vector3f vert, Vector3f col, Vector3f normal) {
		verts.add(vert);
		color.add(col);
		this.normal.add(normal);
		return this;
	}

	public int addVertexID(Vector3f vert, Vector3f col, Vector3f normal) {
		verts.add(vert);
		color.add(col);
		this.normal.add(normal);
		return verts.indexOf(vert);
	}

	/**
	 * @return the verts
	 */
	public List<Vector3f> getVerts() {
		return verts;
	}

	/**
	 * @return the color
	 */
	public List<Vector3f> getColor() {
		return color;
	}

	/**
	 * @return the normal
	 */
	public List<Vector3f> getNormal() {
		return normal;
	}

	public float[] toVerts() {
		float[] verts = new float[this.verts.size() * 9];

		for (int i = 0; i < verts.length; i += 9) {
			Vector3f v = this.verts.pop();
			Vector3f c = color.pop();
			Vector3f n = normal.pop();

			verts[i] = v.x;
			verts[i + 1] = v.y;
			verts[i + 2] = v.z;
			verts[i + 3] = c.x;
			verts[i + 4] = c.y;
			verts[i + 5] = c.z;
			verts[i + 6] = n.x;
			verts[i + 7] = n.y;
			verts[i + 8] = n.z;
		}

		return verts;

	}

	public void addAll(List<Vector3f> vert, List<Vector3f> col, List<Vector3f> norm) {
		this.verts.addAll(vert);
		this.color.addAll(col);
		this.normal.addAll(norm);
	}

}
