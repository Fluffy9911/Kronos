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

import com.kronos.Kronos;
import com.kronos.Testing;
import com.kronos.graphixs.Light;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.display.camera.PerspectiveCamera;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.internal.Cube;
import com.kronos.graphixs.rendering.RenderTarget;
import com.kronos.graphixs.rendering.RenderTarget.TargetConfig;
import com.kronos.graphixs.rendering.buffers.MeshBuffer;
import com.kronos.graphixs.shaders.Shader3D;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

/**
 * 
 */
public class Scene3D {
	List<Light> lights;
	List<Mesh> meshes;
	Shader3D draw;
	PerspectiveCamera pc;
	Vector2i li;
	String[] displays;
	String[] op;
	int val = 0;
	String display = "Hit N";
	String opr = "Hit M";
	int mmax = 20;
	int lmax = 5;
	int v = 0;
	RenderTarget tt;
	int uq = 1;

	public Scene3D(ShaderProgram draw, PerspectiveCamera pc) {
		super();
		this.draw = (Shader3D) draw;
		this.pc = pc;
		lights = new ArrayList<>();
		meshes = new ArrayList<>();

		Random r = new Random();
		redoLights(r);
		((Shader3D) draw).setCamera(pc);
		tt = new RenderTarget((Shader3D) draw, "test_buffer", new TargetConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 1920 * uq;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 1080 * uq;
			}

			@Override
			public Color clear() {
				// TODO Auto-generated method stub
				return Colors.Black;
			}

		});
		tt.setMeshes(new MeshBuffer<Mesh>((ArrayList<Mesh>) meshes));
	}

	public void handleInputs() {
		genDisplays();
		op = new String[] { "increase meshes by 10", "decrease meshes by 10", "increase lights by 1",
				"decrease lights by 1", "increase far clip", "increase camera velocity by 1",
				"decrease camera velocity by 1", "spawn light at position", "upscale quality",
				"upscale and render scene to texture" };
		opr = op[val];
		if (InputHandler.isKeyReleased(Keys.M)) {
			val++;
			if (val >= op.length) {
				val = 0;
			}
		}

		if (InputHandler.isKeyReleased(Keys.N)) {
			v++;
			if (v >= displays.length) {
				v = 0;
			}
			display = displays[v];
		}
		if (InputHandler.isKeyReleased(Keys.K)) {
			v--;
			if (v < 0) {
				v = 0;
			}
			display = displays[v];
		}
		if (InputHandler.isKeyReleased(Keys.B)) {
			if (val == 0) {
				mmax += 10;
				Testing.addRand(meshes, mmax);
				tt.setMeshes(new MeshBuffer<Mesh>((ArrayList<Mesh>) meshes));
			}
			if (val == 1) {
				mmax -= 10;
				Testing.addRand(meshes, mmax);
				tt.setMeshes(new MeshBuffer<Mesh>((ArrayList<Mesh>) meshes));
			}
			if (val == 2) {
				lmax++;
				redoLights(new Random());
				tt.setMeshes(new MeshBuffer<Mesh>((ArrayList<Mesh>) meshes));
			}
			if (val == 3) {
				lmax--;
				redoLights(new Random());
				tt.setMeshes(new MeshBuffer<Mesh>((ArrayList<Mesh>) meshes));
			}
			if (val == 4) {
				pc.setFar(pc.getFar() * 2);
				pc.update();

			}
			if (val == 5) {
				pc.setVelocity(pc.getVelocity() + 1);
				pc.update();

			}
			if (val == 6) {
				pc.setVelocity(pc.getVelocity() - 1);
				pc.update();

			}
			if (val == 7) {
				randomLight(pc.getPosition());
			}
			if (val == 8) {
				uq++;
				tt.setConfig(new TargetConfig() {

					@Override
					public int width() {
						// TODO Auto-generated method stub
						return 1920 * uq;
					}

					@Override
					public int height() {
						// TODO Auto-generated method stub
						return 1080 * uq;
					}

					@Override
					public Color clear() {
						// TODO Auto-generated method stub
						return Colors.Black;
					}
				});
			}
			if (val == 9) {
				renderToTex();
			}
			genDisplays();
		}
	}

	public void genDisplays() {
		displays = new String[] { "Meshes: " + meshes.size(), "Lights: " + lmax, "Verticies: " + meshes.size() * 8,
				"Cam Pos: " + pc.getPosition(), "Cam Looking At: " + pc.getLookat(), "Cam Yaw: " + pc.getYaw(),
				"Cam Pitch: " + pc.getPitch(), "Cam Velocity: " + pc.getVelocity(), "Cam Far Plane:" + pc.getFar() };
		display = displays[v];
	}

	public void redoLights(Random r) {
		lights.clear();
		int i = lmax;
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
		Light l = new Light(pos, Colors.randColor().asVector3f(), Colors.White.asVector3f(),
				Colors.randColor().asVector3f(), r.nextFloat(0, 1), r.nextFloat(0, 1), r.nextFloat(0, 1));
		l.setDis(r.nextFloat(16, 256));
		lights.add(l);

	}

	public void randomLight(Vector3f pos) {
		Random r = new Random();

		Cube cube = new Cube((int) pos.x, (int) pos.y, (int) pos.z, 5, Colors.White);
		BasicMeshBuilder b = new BasicMeshBuilder();
		cube.applyVerts(b);
		b.addAll(BasicMeshBuilder.getAttribs());
		Mesh cmesh = b.build();
		meshes.add(cmesh);

		lights.add(new Light(pos, Colors.White.asVector3f(), Colors.White.asVector3f(), Colors.randColor().asVector3f(),
				r.nextFloat(0, 1), r.nextFloat(0, 1), r.nextFloat(0, 1)));

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
		draw.setAttribs();

		li = draw.bindLightsSSBO(lights);

	}

	/**
	 * 
	 */
	public void render() {
//		draw.setAttribs();
//
//		li = draw.bindLightsSSBO(lights);
		draw.bindSSBO(li);
		draw.addUniform("material.strength", 1f);
		tt.setTargetCamera(pc);
		tt.getMeshes().setDraw(draw);
		Texture r = tt.render((s, m) -> {
			m.setDraw((ShaderProgram) s);
			m.drawBuffer();
		});
		Kronos.graphixs.clearScreen(Colors.Black);
		for (Iterator iterator = meshes.iterator(); iterator.hasNext();) {
			Mesh mesh = (Mesh) iterator.next();

			mesh.render(draw);
		}
//		TextureBatch tb = Kronos.graphixs.g2d.createBatch();
//		tb.drawTexture(0, 70, 400, 400, r);
//		tb.render();
//		tb.end();

	}

	public void renderToTex() {
		int cw = this.pc.getWidth();
		int ch = this.pc.getHeight();
		tt.setConfig(new TargetConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 1920 * uq;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 1080 * uq;
			}

			@Override
			public Color clear() {
				// TODO Auto-generated method stub
				return Colors.Black;
			}
		});
		tt.rebuildBuffer();
		this.pc.setWidth(1920 * uq);
		this.pc.setHeight(1080 * uq);
		this.pc.calculatePositioning(1920 * uq, 1080 * uq);
		prepare();
		System.out.println(pc.toString());
		draw.bindSSBO(li);
		draw.addUniform("material.strength", 1f);
		tt.setTargetCamera(pc);
		tt.getMeshes().setDraw(draw);
		Texture r = tt.render((s, m) -> {
			m.setDraw((ShaderProgram) s);
			m.drawBuffer();
		});
		r.bind();
		System.out.println(r.getWidth() + ":" + r.getHeight());
		Kronos.graphixs.writeTextureOut("testShot", 1920 * uq, 1080 * uq);
		this.pc.setHeight(cw);
		this.pc.setWidth(ch);
		this.pc.setMoved(true);
		this.pc.calculatePositioning(cw, ch);
		this.pc.update();
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

	public String getDisplay() {
		return display;
	}

	public String getOpr() {
		return opr;
	}

	public void scenePTest() {
		Testing.addRand(meshes, 10000);
		lmax = 5;
		redoLights(new Random());
	}

}
