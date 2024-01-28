package com.kronos.graphixs.geometry.meshing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.Mesh.AttributeInfo;

public class BasicMeshBuilder {
	VertexBuffer vertBuffer;
	List<AttributeInfo> attribs;
	List<Integer> ind;

	public Mesh toMesh(VertexBuffer buffer, List<Integer> ind) {
		this.vertBuffer = buffer;
		this.ind = ind;
		return this.build();
	}

	public BasicMeshBuilder() {
		attribs = new ArrayList<>();
		vertBuffer = new VertexBuffer();
		ind = new ArrayList<>();
	}

	/**
	 * @param vert
	 * @param col
	 * @param normal
	 * @return
	 * @see com.kronos.graphixs.geometry.meshing.VertexBuffer#addVertex(org.joml.Vector3f,
	 *      org.joml.Vector3f, org.joml.Vector3f)
	 */
	public BasicMeshBuilder addVertex(Vector3f vert, Vector3f col, Vector3f normal, int con) {
		int b = vertBuffer.addVertexID(vert, col, normal);
		ind.add(b);
		ind.add(con);
		return this;
	}

	/**
	 * @param vert
	 * @param col
	 * @param normal
	 * @return
	 * @see com.kronos.graphixs.geometry.meshing.VertexBuffer#addVertex(org.joml.Vector3f,
	 *      org.joml.Vector3f, org.joml.Vector3f)
	 */

	public BasicMeshBuilder addVertex(Vector3f vert, Vector3f col, Vector3f normal) {
		int b = vertBuffer.addVertexID(vert, col, normal);

		return this;
	}

	public void addInd(int i) {
		ind.add(i);
	}

	public void foreach(BuilderImpl impl) {
		for (Iterator iterator = vertBuffer.verts.iterator(); iterator.hasNext();) {
			Vector3f vert = (Vector3f) iterator.next();
			impl.foreach(vert, vertBuffer.color.get(vertBuffer.verts.indexOf(vert)),
					vertBuffer.normal.get(vertBuffer.verts.indexOf(vert)));
		}
	}

	public void translate(float x, float y, float z) {

		foreach((v, c, n) -> {
			v.add(x, y, z);
		});
	}

	public void scale(float scalar) {

		foreach((v, c, n) -> {
			v.mul(scalar);
		});
	}

	public void rot(float a, float x, float y, float z) {

		foreach((v, c, n) -> {
			v.rotateX(a);
			v.rotateY(a);
			v.rotateZ(a);
		});
	}

	/**
	 * @param vert
	 * @param col
	 * @param normal
	 * @return
	 * @see com.kronos.graphixs.geometry.meshing.VertexBuffer#addVertex(org.joml.Vector3f,
	 *      org.joml.Vector3f, org.joml.Vector3f)
	 */
	public BasicMeshBuilder addVertexToFirst(Vector3f vert, Vector3f col, Vector3f normal) {

		return addVertex(vert, col, normal, 0);
	}

	public void addAttribs(AttributeInfo info) {
		attribs.add(info);
	}

	public Mesh build() {
		float[] vs = vertBuffer.toVerts();

		int am = vs.length;
		int ia = ind.size();
		int[] idn = new int[ind.size()];
		for (int i = 0; i < idn.length; i++) {
			idn[i] = ind.get(i);

		}
		// Kronos.debug.getLogger().debug("Verts: {} indices: {}", vs, idn);
		Mesh m = new Mesh(vs, idn, am, ia);
		m.addAll(attribs);
		return m.build();
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends AttributeInfo> c) {
		return attribs.addAll(c);
	}

	public static List<AttributeInfo> getAttribs() {
		return List.of(new AttributeInfo("in_pos", 0, 3, GL40.GL_FLOAT, 9 * Float.BYTES, 0),
				new AttributeInfo("in_color", 1, 3, GL40.GL_FLOAT, 9 * Float.BYTES, 3 * Float.BYTES),
				new AttributeInfo("in_normal", 2, 3, GL40.GL_FLOAT, 9 * Float.BYTES, 6 * Float.BYTES));
	}

	public void addAllData(VertexBuffer vb) {
		this.vertBuffer.addAll(vb.getVerts(), vb.getNormal(), vb.getColor());
	}

	public static interface BuilderImpl {

		void foreach(Vector3f vertex, Vector3f color, Vector3f normal);

	}

	public void addInd(int[] out) {
		for (int i = 0; i < out.length; i++) {
			ind.add(out[i]);
		}

	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAllInds(Collection<? extends Integer> c) {
		return ind.addAll(c);
	}

	public int getIndex() {

		return vertBuffer.verts.size() - 1;
	}

}
