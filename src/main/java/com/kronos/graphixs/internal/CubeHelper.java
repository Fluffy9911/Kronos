package com.kronos.graphixs.internal;

import java.util.Random;

import org.joml.Vector3i;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;

public class CubeHelper {
	public static Cube[][][] generateCubeArray(int width, int height, int depth, int cubeSize) {
		Cube[][][] cubes = new Cube[width][height][depth];

		// Loop through the 3D array to create and populate cubes
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					// Calculate the position of the cube based on its size
					int cubeX = x * cubeSize;
					int cubeY = y * cubeSize;
					int cubeZ = z * cubeSize;
					Random r = new Random();
					// Create a Color object for the cube's color (you can customize this)
					Color cubeColor = new Color(r.nextFloat(1), r.nextFloat(1), r.nextFloat(1)); // Example: Red color

					// Create a new Cube and assign it to the array
					cubes[x][y][z] = new Cube(cubeX, cubeY, cubeZ, cubeSize, cubeColor);
					System.out.println(cubes[x][y][z].toString());
				}
			}
		}

		return cubes;
	}

	public boolean shouldBeMeshed(Cube[][][] cubes, Vector3i vec) {
		// Get the dimensions of the cubes array
		int xSize = cubes.length;
		int ySize = cubes[0].length;
		int zSize = cubes[0][0].length;

		// Check adjacent cubes in all six directions
		boolean hasTop = vec.y + 1 < ySize && cubes[vec.x][vec.y + 1][vec.z] != null;
		boolean hasBottom = vec.y - 1 >= 0 && cubes[vec.x][vec.y - 1][vec.z] != null;
		boolean hasLeft = vec.x - 1 >= 0 && cubes[vec.x - 1][vec.y][vec.z] != null;
		boolean hasRight = vec.x + 1 < xSize && cubes[vec.x + 1][vec.y][vec.z] != null;
		boolean hasFront = vec.z - 1 >= 0 && cubes[vec.x][vec.y][vec.z - 1] != null;
		boolean hasBack = vec.z + 1 < zSize && cubes[vec.x][vec.y][vec.z + 1] != null;

		// If any adjacent cube is missing, return true (should be meshed)
		return !(hasTop && hasBottom && hasLeft && hasRight && hasFront && hasBack);
	}

	public static boolean shouldMeshTopFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (y + 1 >= cubes[0].length) || cubes[x][y + 1][z] == null;
	}

	/**
	 * Check if the bottom face of the cube should be meshed.
	 */
	public static boolean shouldMeshBottomFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (y - 1 < 0) || cubes[x][y - 1][z] == null;
	}

	/**
	 * Check if the left face of the cube should be meshed.
	 */
	public static boolean shouldMeshLeftFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (x - 1 < 0) || cubes[x - 1][y][z] == null;
	}

	/**
	 * Check if the right face of the cube should be meshed.
	 */
	public static boolean shouldMeshRightFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (x + 1 >= cubes.length) || cubes[x + 1][y][z] == null;
	}

	/**
	 * Check if the front face of the cube should be meshed.
	 */
	public static boolean shouldMeshFrontFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (z - 1 < 0) || cubes[x][y][z - 1] == null;
	}

	/**
	 * Check if the back face of the cube should be meshed.
	 */
	public static boolean shouldMeshBackFace(Cube[][][] cubes, Vector3i vec) {
		int x = vec.x;
		int y = vec.y;
		int z = vec.z;
		if (x < 0 || y < 0 || z < 0) {
			return false;
		}
		if (x >= cubes.length || y >= cubes[x].length || z >= cubes[x][y].length) {
			return false;
		}
		return (z + 1 >= cubes[0][0].length) || cubes[x][y][z + 1] == null;
	}

	public static Mesh createCube(Cube cube) {
		BasicMeshBuilder bmb = new BasicMeshBuilder();

		cube.applyVerts(bmb);
		bmb.addAll(BasicMeshBuilder.getAttribs());
		return bmb.build();
	}
}
