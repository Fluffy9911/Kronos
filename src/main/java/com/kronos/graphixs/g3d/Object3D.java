/**
 * 
 */
package com.kronos.graphixs.g3d;

import org.joml.Matrix4f;

import com.kronos.gameFramework.templates.Transform;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.material.Material;
import com.kronos.graphixs.shaders.render.ShaderProgram;

/**
 * 
 */
public class Object3D extends Transform {
	Material mat;
	Matrix4f transform;
	Mesh mesh;

	public Object3D(Material mat, Mesh mesh) {
		super(Transform.no_pos);
		this.mat = mat;
		this.mesh = mesh;

	}

	public void render(ShaderProgram draw) {
		draw.addUniform("omodel", transform);
		draw.addUniform("material.strength", mat.getStrength());
		mesh.render(draw);
	}

}
