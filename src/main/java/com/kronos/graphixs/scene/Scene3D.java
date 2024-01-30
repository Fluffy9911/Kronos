/**
 * 
 */
package com.kronos.graphixs.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.joml.Vector2i;
import org.joml.Vector3f;

import com.kronos.graphixs.Light;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.camera.PerspectiveCamera;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.internal.Cube;
import com.kronos.graphixs.shaders.ShaderProgram;

/**
 * 
 */
public class Scene3D {
	List<Light> lights;
	List<Mesh> meshes;
	ShaderProgram draw;
	PerspectiveCamera pc;
	Vector2i li;
	String[] displays;
	String[] op;

	public Scene3D(ShaderProgram draw, PerspectiveCamera pc) {
		super();
		this.draw = draw;
		this.pc = pc;
		lights = new ArrayList<>();
		meshes = new ArrayList<>();

		Random r = new Random();
		redoLights(r);

	}

	public void redoLights(Random r) {
		lights.clear();
		int i = r.nextInt(1, 20);
		for (int j = 0; j < i; j++)
			randomLight();

	}

	public void randomLight() {
		Random r = new Random();
		Vector3f pos = rvec(new Random(), 0, 0, 0);
		Cube cube = new Cube((int) pos.x, (int) pos.y, (int) pos.z, 5, Colors.White);
		BasicMeshBuilder b = new BasicMeshBuilder();
		cube.applyVerts(b);
		b.addAll(BasicMeshBuilder.getAttribs());
		Mesh cmesh = b.build();
		meshes.add(cmesh);
		lights.add(new Light(pos, Colors.randColor().asVector3f(), Colors.White.asVector3f(),
				Colors.randColor().asVector3f(), r.nextFloat(0, 1), r.nextFloat(0, 1), r.nextFloat(0, 1)));

	}

	/**
	 * @param r
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	private Vector3f rvec(Random r, int i, int j, int k) {
		// TODO Auto-generated method stub
		return new Vector3f(r.nextFloat(-1000, 1000), r.nextFloat(-1000, 1000), r.nextFloat(-1000, 1000));
	}

	public void prepare() {
//cam uniforms
		draw.use();
		// lights.get(0).setPosition(pc.getPosition());

		draw.addUniform("proj", pc.getProjection());
		draw.addUniform("view", pc.getView());
		draw.addUniform("model", pc.getModel());
		draw.addUniform("omodel", pc.getModel());

		draw.addUniform("viewPos", pc.getLookat());

		li = draw.bindLightsSSBO(lights);
		// lights
//		for (int i = 0; i < lights.size(); i++) {
//			Light l = lights.get(i);
//			draw.addUniform("lights[" + i + "].position", l.getPosition());
//
//			draw.addUniform("lights[" + i + "].constant", 1);
//			draw.addUniform("lights[" + i + "].linear", l.getLinear());
//			draw.addUniform("lights[" + i + "].quadratic", l.getQuad());
//			draw.addUniform("lights[" + i + "].ambient", l.getAmbient());
//			draw.addUniform("lights[" + i + "].diffuse", l.getDiffuse());
//			draw.addUniform("lights[" + i + "].specular", l.getSpecular());
////			draw.addUniform("lights[" + i + "].cutOff", 0.15f);
////			draw.addUniform("lights[" + i + "].outerCutOff", 0.15f);
////			draw.addUniform("lights[" + i + "].direction", rvec(new Random(), 2, 2, 2));
//		}
		// material

//		draw.addUniform("material.ambient", 0.75f);
	}

	/**
	 * 
	 */
	public void render() {
		draw.bindSSBO(li);
		draw.addUniform("material.strength", 0.5f);
		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			Mesh mesh = (Mesh) iterator.next();

			mesh.render(draw);
		}

	}

	public List<Light> getLights() {
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}

	public List<Mesh> getMeshes() {
		return meshes;
	}

	public void setMeshes(List<Mesh> meshes) {
		this.meshes = meshes;
	}

	public int lights() {
		return lights.size();
	}
}
