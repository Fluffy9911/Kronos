package com.kronos.graphixs;

import org.joml.Vector3f;

public class Light {
	private Vector3f position;
	private Vector3f ambient, specular, diffuse;

	float linear, quad, constant;
	float dis = 16;

	public Light(Vector3f position, Vector3f ambient, Vector3f specular, Vector3f diffuse, float linear, float quad,
			float constant) {
		super();
		this.position = position;
		this.ambient = ambient;
		this.specular = specular;
		this.diffuse = diffuse;
		this.linear = linear;
		this.quad = quad;
		this.constant = constant;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getAmbient() {
		return ambient;
	}

	public Vector3f getSpecular() {
		return specular;
	}

	public Vector3f getDiffuse() {
		return diffuse;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setAmbient(Vector3f ambient) {
		this.ambient = ambient;
	}

	public void setSpecular(Vector3f specular) {
		this.specular = specular;
	}

	public void setDiffuse(Vector3f diffuse) {
		this.diffuse = diffuse;
	}

	public void setLinear(float linear) {
		this.linear = linear;
	}

	public void setQuad(float quad) {
		this.quad = quad;
	}

	public void setConstant(float constant) {
		this.constant = constant;
	}

	public float getLinear() {
		return linear;
	}

	public float getQuad() {
		return quad;
	}

	public float getConstant() {
		return constant;
	}

	public float getDis() {
		return dis;
	}

	public void setDis(float dis) {
		this.dis = dis;
	}

}
