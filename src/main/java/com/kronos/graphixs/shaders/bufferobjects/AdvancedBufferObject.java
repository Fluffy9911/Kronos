/**
 * 
 */
package com.kronos.graphixs.shaders.bufferobjects;

import java.nio.FloatBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

/**
 * 
 */
public class AdvancedBufferObject extends BufferObject {
	FloatBuffer buffer;

	public AdvancedBufferObject(int size) {
		this.buffer = BufferUtils.createFloatBuffer(size);
	}

	public void putInt(int val) {
		buffer.put(val);
	}

	public void putFloat(float f) {
		buffer.put(f);
	}

	public void putVector(Vector2f v) {
		buffer.put(v.x).put(v.y);
	}

	public void putVector(Vector3f v) {
		buffer.put(v.x).put(v.y).put(v.z);
	}

	public void putVector(Vector4f v) {
		buffer.put(v.x).put(v.y).put(v.z).put(v.w);
	}

	@Override
	public void append(FloatBuffer fb) {
		fb.put(buffer);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return buffer.capacity();
	}

}
