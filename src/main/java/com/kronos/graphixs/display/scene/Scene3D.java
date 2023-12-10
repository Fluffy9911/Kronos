package com.kronos.graphixs.display.scene;

import java.util.ArrayList;
import java.util.List;

import com.kronos.Kronos;
import com.kronos.graphixs.Light;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.camera.Camera;
import com.kronos.graphixs.geometry.Mesh;

public abstract class Scene3D {
	List<Mesh> meshes;
	Camera camera;
	List<Light> scene_lights;
	Graphixs graphixs = Kronos.graphixs;

	public Scene3D(Camera camera) {
		this.camera = camera;
		meshes = new ArrayList<>();
		scene_lights = new ArrayList<>();

	}

	public abstract void setup();

	public abstract void cleanup();

	public Scene3D addMesh(Mesh m) {
		meshes.add(m);
		return this;
	}

	public Scene3D addLight(Light light) {
		scene_lights.add(light);
		return this;
	}

	public void renderScene() {

		render();

	}

	public abstract void render();

}