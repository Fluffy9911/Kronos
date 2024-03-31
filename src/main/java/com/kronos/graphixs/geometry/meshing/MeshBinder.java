/**
 * 
 */
package com.kronos.graphixs.geometry.meshing;

import java.util.ArrayList;
import java.util.List;

import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.Mesh.AttributeInfo;

/**
 * 
 */
public class MeshBinder {
	Mesh[] m;
	float[] v;
	int[] i;
	int fs, is;

	public MeshBinder(Mesh[] m) {
		this.m = m;
		for (int i = 0; i < m.length; i++) {
			fs += m[i].getVertCount();
			is += m[i].getIndexCount();
		}
		v = new float[fs];
		i = new int[is];
	}

	public Mesh build(List<AttributeInfo> af) {
		int off = 0;
		ArrayList<Float> fb = new ArrayList<Float>();
		ArrayList<Integer> ib = new ArrayList<Integer>();
		for (int i = 0; i < m.length; i++) {
			for (float f : m[i].getV()) {
				fb.add(f);
			}
			for (int ii : m[i].getI()) {
				int v = ii + off;
				ib.add(v);
			}
			off += (m[i].getIndexCount());
		}
		for (Float f : fb) {
			v[fb.indexOf(f)] = f.floatValue();
		}
		for (Integer f : ib) {
			i[ib.indexOf(f)] = f.intValue();
		}

		System.out.println(fb.toString());
		System.out.println(ib.toString());
		Mesh m = new Mesh(v, i, fs, is);
		m.addAll(af);
		// m.build();
		return m.build();
	}

}
