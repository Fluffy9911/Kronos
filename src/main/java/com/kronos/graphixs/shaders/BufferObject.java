/**
 * 
 */
package com.kronos.graphixs.shaders;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joml.Vector4f;

/**
 * 
 */
public abstract class BufferObject {
	List<Vector4f> data;

	public BufferObject() {
		super();
		data = new ArrayList<>();
	}

	public void append(FloatBuffer fb) {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			Vector4f v = (Vector4f) iterator.next();
			fb.put(v.x);
			fb.put(v.y);
			fb.put(v.z);
			fb.put(v.w);
		}
	}

	public int getSize() {
		return data.size() * 4;
	}
}
