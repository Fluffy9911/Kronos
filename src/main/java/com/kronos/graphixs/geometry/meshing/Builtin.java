package com.kronos.graphixs.geometry.meshing;

import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.geometry.Mesh;

public class Builtin {
	public static Mesh textured_quad(int x, int y, float w, float h) {
		float[] verts = new float[] { x, y, 1f, 1f, x, y + h, 1f, 0f, x + w, y + h, 0f, 0f, x + w, y, 0f, 1f

		};
		int[] in = new int[] { 0, 1, 3, 1, 2, 3 };
		Mesh m = new Mesh(verts, in, 4, 6);
		m.addAttribute("in_pos", 0, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 0);
		m.addAttribute("uv", 1, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 2 * Float.BYTES);
		return m.build();
	}

	public static Mesh screenQuad() {
		float[] verts = new float[] { -1.0f, 1.0f, 0.0f, 1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 1.0f

		};
		int[] in = new int[] { 0, 1, 3, 1, 2, 3 };
		Mesh m = new Mesh(verts, in, 4, 6);
		m.addAttribute("in_pos", 0, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 0);
		m.addAttribute("uv", 1, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 2 * Float.BYTES);
		return m.build();
	}

	public static Mesh textured_quad(float x, float y, float w, float h) {
		float[] verts = new float[] { x, y, 1f, 1f, x, y + h, 1f, 0f, x + w, y + h, 0f, 0f, x + w, y, 0f, 1f

		};
		int[] in = new int[] { 0, 1, 3, 1, 2, 3 };
		Mesh m = new Mesh(verts, in, 4, 6);
		m.addAttribute("in_pos", 0, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 0);
		m.addAttribute("uv", 1, 2, GL40.GL_FLOAT, 4 * Float.BYTES, 2 * Float.BYTES);
		return m.build();
	}
}
