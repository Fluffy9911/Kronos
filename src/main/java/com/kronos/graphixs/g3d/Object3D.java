/**
 * 
 */
package com.kronos.graphixs.g3d;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.kronos.gameFramework.templates.Transform;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.material.Material;
import com.kronos.graphixs.shaders.ShaderProgram;

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

//for opengl rendering - ignore
	public void render(ShaderProgram draw) {
		draw.addUniform("omodel", transform);
		draw.addUniform("material.strength", mat.getStrength());
		mesh.render(draw);
	}

	private Vector3f transformPoint(Vector3f point, Matrix4f matrix) {
		// Transform a point from local space to world space
		Vector4f transformedPoint = new Vector4f(point, 1.0f);
		matrix.transform(transformedPoint);

		return new Vector3f(transformedPoint.x, transformedPoint.y, transformedPoint.z);
	}

	private Vector3f transformDirection(Vector3f direction, Matrix4f matrix) {
		// Transform a direction from local space to world space
		Vector4f transformedDirection = new Vector4f(direction, 0.0f);
		matrix.transform(transformedDirection);

		return new Vector3f(transformedDirection.x, transformedDirection.y, transformedDirection.z);
	}
}
