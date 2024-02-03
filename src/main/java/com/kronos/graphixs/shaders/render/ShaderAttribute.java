/**
 * 
 */
package com.kronos.graphixs.shaders.render;

import org.lwjgl.opengl.GL20;

public class ShaderAttribute {
	String name;
	int loc;

	public ShaderAttribute(String name, int loc) {
		this.name = name;
		this.loc = loc;
	}

	public void apply(int pid) {
		GL20.glBindAttribLocation(pid, loc, name);
	}

}