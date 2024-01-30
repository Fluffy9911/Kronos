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
	public static Material def = new Material(0.5f, 0.75f);

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

}
