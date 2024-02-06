/**
 * 
 */
package com.kronos.graphixs.geometry.material;

/**
 * 
 */
public class Material {
	float strength;
	float ambient;
	public static Material def = new Material(0.5f, 0.75f, 0.5f, 0.5f);
	public static Material shiny = new Material(1, 1, 1, 1);
	float reflectiveness = 1f;
	float shininess = 1f;

	public Material(float strength, float ambient) {
		super();
		this.strength = strength;
		this.ambient = ambient;
	}

	public float getStrength() {
		return strength;
	}

	public float getAmbient() {
		return ambient;
	}

	public Material(float strength, float ambient, float reflectiveness, float shininess) {
		super();
		this.strength = strength;
		this.ambient = ambient;
		this.reflectiveness = reflectiveness;
		this.shininess = shininess;
	}

	public float getReflectiveness() {
		return reflectiveness;
	}

	public float getShininess() {
		return shininess;
	}

}
