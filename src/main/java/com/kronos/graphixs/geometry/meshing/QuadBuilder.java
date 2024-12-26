package com.kronos.graphixs.geometry.meshing;

import java.util.ArrayList;
import java.util.List;

public class QuadBuilder {

	public static MeshBuilder createQuadTexture() {
		MeshBuilder mb = new MeshBuilder();

		// Create position and UV attributes
		mb.createPosition().createUV();

		// Add data for a quad (two triangles forming a rectangle)
		// Vertex positions (x, y) and UV coordinates (u, v)
		mb.addData(-1.0f, -1.0f, 0.0f, 0.0f); // Bottom-left corner
		mb.addData(1.0f, -1.0f, 1.0f, 0.0f); // Bottom-right corner
		mb.addData(1.0f, 1.0f, 1.0f, 1.0f); // Top-right corner
		mb.addData(-1.0f, 1.0f, 0.0f, 1.0f); // Top-left corner

		return mb;
	}

	public static MeshBuilder createQuadNoTexture() {
		MeshBuilder mb = new MeshBuilder();

		// Create position and UV attributes
		mb.createPosition();

		// Add data for a quad (two triangles forming a rectangle)
		// Vertex positions (x, y) and UV coordinates (u, v)
		mb.addData(-1.0f, -1.0f); // Bottom-left corner
		mb.addData(1.0f, -1.0f); // Bottom-right corner
		mb.addData(1.0f, 1.0f); // Top-right corner
		mb.addData(-1.0f, 1.0f); // Top-left corner

		return mb;
	}

	public static int[] out() {
		int[] i = new int[8];
		i[0] = 0;
		i[1] = 1;
		i[2] = 2;
		// i[3] = 0;

		i[4] = 2;
		i[5] = 3;
		i[6] = 0;
		// i[7] = 2;

		return i;
	}

	public static int[] out(int off) {
		int[] i = new int[8];
		i[0] = 0 + off;
		i[1] = 1 + off;
		i[2] = 2 + off;
		i[3] = 0 + off;

		i[4] = 2 + off;
		i[5] = 3 + off;
		i[6] = 0 + off;
		i[7] = 2 + off;

		return i;
	}

	public static List<Integer> outToList() {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(0);
		list.add(2);
		list.add(3);
		list.add(0);
		list.add(2);
		return list;
	}

	public static List<Integer> outToList(int off) {
		List<Integer> list = new ArrayList<>();
		list.add(0 + off);
		list.add(1 + off);
		list.add(2 + off);
		// list.add(0 + off);
		list.add(2 + off);
		list.add(3 + off);
		list.add(0 + off);
		// list.add(2 + off);
		return list;
	}
}
