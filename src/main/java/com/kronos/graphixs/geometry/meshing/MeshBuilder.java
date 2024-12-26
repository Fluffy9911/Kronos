package com.kronos.graphixs.geometry.meshing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MeshBuilder {

	private final LinkedHashMap<String, List<Float>> data = new LinkedHashMap<>();
	private int attributeSize = -1;
	List<Integer> inds = new ArrayList<>();

	public MeshBuilder createPosition() {
		return this.createAttribute("position");
	}

	public MeshBuilder createUV() {
		return this.createAttribute("uv");
	}

	public MeshBuilder createTexture() {
		return this.createAttribute("texture");
	}

	public MeshBuilder createAll() {
		return this.createPosition().createUV().createTexture();
	}

	/**
	 * Creates a new attribute with the given name.
	 * 
	 * @param name the name of the attribute
	 * @return the current MeshBuilder instance
	 */
	public MeshBuilder createAttribute(String name) {
		if (data.containsKey(name)) {
			throw new IllegalArgumentException("Attribute already exists: " + name);
		}
		data.put(name, new ArrayList<>());
		return this;
	}

	/**
	 * Adds data for each attribute in the order they were created.
	 * 
	 * @param values the values to add
	 */
	public void addData(float... values) {
		if (values.length != data.size()) {
			throw new IllegalArgumentException("Number of values (" + values.length
					+ ") must match the number of attributes (" + data.size() + ").");
		}

		int index = 0;
		for (List<Float> list : data.values()) {
			list.add(values[index++]);
		}

		// Ensure all attributes have the same size
		if (attributeSize == -1) {
			attributeSize = data.values().iterator().next().size();
		} else if (attributeSize != data.values().iterator().next().size()) {
			throw new IllegalStateException("Attribute data is inconsistent. All attributes must have the same size.");
		}
	}

	/**
	 * Retrieves all data as a float array, with values ordered such that the first
	 * value of each attribute is first, then the second value, and so on.
	 * 
	 * @return an array of floats containing the interleaved attribute data
	 */
	public float[] build() {
		if (data.isEmpty() || attributeSize == 0) {
			return new float[0];
		}

		float[] result = new float[attributeSize * data.size()];
		int resultIndex = 0;

		for (int i = 0; i < attributeSize; i++) {
			for (List<Float> list : data.values()) {
				result[resultIndex++] = list.get(i);
			}
		}

		return result;
	}

	public void addInds(Integer... is) {
		inds.addAll(List.of(is));
	}

	public Integer[] indicies() {

		return inds.toArray(new Integer[0]);
	}
}
