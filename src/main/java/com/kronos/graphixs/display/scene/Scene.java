package com.kronos.graphixs.display.scene;

import java.util.Iterator;

import com.kronos.graphixs.display.camera.Camera;
import com.kronos.graphixs.geometry.Mesh;

public class Scene extends Scene3D {

	public Scene(Camera camera) {
		super(camera);

	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			Mesh mesh = (Mesh) iterator.next();

		}

	}

}
