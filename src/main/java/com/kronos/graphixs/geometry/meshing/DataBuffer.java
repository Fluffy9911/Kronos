package com.kronos.graphixs.geometry.meshing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joml.Vector3f;

public class DataBuffer {
	List<Integer> inds;
	VertexBuffer buff;

	public DataBuffer() {
		buff = new VertexBuffer();
		inds = new ArrayList<Integer>();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Integer e) {
		return inds.add(e);
	}

	/**
	 * @param vert
	 * @param col
	 * @param normal
	 * @return
	 * @see com.kronos.graphixs.geometry.meshing.VertexBuffer#addVertex(org.joml.Vector3f,
	 *      org.joml.Vector3f, org.joml.Vector3f)
	 */
	public VertexBuffer addVertex(Vector3f vert, Vector3f col, Vector3f normal) {
		return buff.addVertex(vert, col, normal);
	}

	public void add(BasicMeshBuilder bmb) {
		bmb.addAllInds(inds);
		bmb.vertBuffer.addAll(buff.getVerts(), buff.getColor(), buff.getNormal());
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Integer> c) {
		return inds.addAll(c);
	}

}
