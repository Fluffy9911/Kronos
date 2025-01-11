/**
 * 
 */
package com.kronos.graphixs.shaders.utils;

import java.util.HashMap;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * represents a shader glsl struct object capable of writing floats ints and
 * vectors to shaders
 */
public class Struct {
	HashMap<String, String> values;
	String name;

	public Struct(String name) {
		this.name = name;
		values = new HashMap<String, String>();
	}

	public void putInt(String n, int val) {
		values.put(n, Integer.toString(val));
	}

	public void putFloat(String n, float f) {
		values.put(n, Float.toString(f));
	}

	public void putVector(String n, Vector2f v) {
		putFloat("vec2:" + n + "<x>", v.x);
		putFloat("vec2:" + n + "<y>", v.y);
	}

	public void putVector(String n, Vector3f v) {
		putFloat("vec3:" + n + "<x>", v.x);
		putFloat("vec3:" + n + "<y>", v.y);
		putFloat("vec3:" + n + "<z>", v.z);
	}

	public void putVector(String n, Vector4f v) {
		putFloat("vec4:" + n + "<x>", v.x);
		putFloat("vec4:" + n + "<y>", v.y);
		putFloat("vec4:" + n + "<z>", v.z);
		putFloat("vec4:" + n + "<w>", v.w);

	}

}
