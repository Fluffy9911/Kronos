package com.kronos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

import com.kronos.core.event.EngineListener;
import com.kronos.debug.DebugInputFields;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.display.camera.PerspectiveCamera;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.builder.ShapeRenderer;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.pixelcanvas.Canvas2D;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.graphixs.internal.Cube;
import com.kronos.graphixs.scene.Scene3D;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.Config;
import com.kronos.io.InputHandler;

public class Testing {
	public static int i = 200;

	public static void main(String[] args) {

		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 1000;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 1000;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "KronosTesting";
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return Colors.Black;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return -1;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return defaultHints();
			}

		});

		Graphixs g = Kronos.graphixs;
		DebugInputFields dbgif = new DebugInputFields();

		Graphixs2D g2d = g.g2d;

//		ComponentHandler ch = new ComponentHandler(g2d);
//		Panel p = new Panel(BasePosition.single(40, 40, 300, 400, g2d.getProvider()), false, true, "test_panel");
//		ch.put("test_panel", p);
//		IncrementNumber ic = new IncrementNumber(BasePosition.single(95, 95, 120, 40, g2d.getProvider()), false, false,
//				false, "test_increment");
//		ch.put("test_increment", ic);
//
//		ch.createComps();
//		ch.load();

		Canvas2D c = new Canvas2D(400, 400);
		c.clear(Colors.White.rgb());
		c.noiseShort();
		TextureBatch tb = g2d.createBatch();
		ShapeRenderer sr = g.shapeRenderer;
		sr.loadIn("test_shape", Kronos.loader.tryLoadImage("texture/test_shape.png"));

		Cube cube = new Cube(0, 0, 0, 10, Colors.Salmon);
//		BasicMeshBuilder builder = new BasicMeshBuilder();
//		addRand(builder, 10);
//		builder.addAll(BasicMeshBuilder.getAttribs());
		List<Mesh> mms = new ArrayList<Mesh>();
		addRand(mms, 20);
		ShaderProgram draw = g.getShader("3d");
		PerspectiveCamera pc = new PerspectiveCamera(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0),
				new Vector3f(1, 1, 1));
		pc.calculatePositioning(900, 900);

		Kronos.registerListener(new EngineListener() {

			@Override
			public void engineStart() {
				// ch.load();

			}

			@Override
			public void engineEnd() {
				// ch.write();
			}

			@Override
			public void configChange(Config c) {
				// TODO Auto-generated method stub

			}
		});
		Mesh quad = Builtin.screenQuad();
		FontRenderer fr = FontRenderer.createDefault();

		Scene3D scene = new Scene3D(draw, pc);
		scene.setMeshes(mms);
		Kronos.startDrawing((a) -> {
			GL40.glEnable(GL40.GL_DEPTH_TEST);

			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_Z)) {
				pc.setZoom(pc.getZoom() + 0.1f);
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_X)) {
				Kronos.config.setCurrent(g.getConfig());
				pc.update();
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_C)) {
				pc.setFar(pc.getFar() + 100f);
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_V)) {
				pc.setFar(pc.getFar() - 100f);
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_B)) {
				i += 10;

			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_N)) {
				i = (int) ((i * .5) + i);
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_M)) {
				mms.clear();
				addRand(mms, i);
				scene.setMeshes(mms);
				scene.redoLights(new Random());
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_F)) {
				a.setFps((int) (a.getFps() + 5));
			}
			if (InputHandler.isKeyReleased(GLFW.GLFW_KEY_G)) {
				a.setFps((int) (a.getFps() - 5));
			}
			pc.update();

			scene.prepare();
			scene.render();

			//
			fr.renderText("Cam Position: " + pc.getPosition().toString() + " Camera Look: " + pc.getLookat().toString(),
					0, 0, fr.useDefaultFont().deriveFont(20.5f), java.awt.Color.WHITE, tb);
			fr.renderText(
					"Meshes: " + i + "Scene Lights: " + scene.lights() + " FPS: " + a.getFps() + "/" + a.target()
							+ " Delta: " + a.getDeltaTime(),
					0, 15, fr.useDefaultFont().deriveFont(20.5f), java.awt.Color.WHITE, tb);
			// tb.drawTexture(0, 0, 200, 200, c.toTexture());
			tb.render();
			tb.end();

			g.glErrors();
			InputHandler.nextFrame();
		});

	}

	public static void addRand(List<Mesh> mesh, int a) {
		for (int i = 0; i < a; i++) {
			Random r = new Random();

			Cube cube = new Cube(r.nextInt(-2000, 2000), r.nextInt(-2000, 2000), r.nextInt(-2000, 2000),
					r.nextInt(10, 200), Colors.randColor());
			BasicMeshBuilder b = new BasicMeshBuilder();
			cube.applyVerts(b);
			b.addAll(BasicMeshBuilder.getAttribs());
			Mesh cmesh = b.build();
			mesh.add(cmesh);
		}
	}

	public static void addRand(BasicMeshBuilder builder, int a) {
		for (int i = 0; i < a; i++) {
			Random r = new Random();

			Cube cube = new Cube(r.nextInt(-1000, 1000), r.nextInt(-1000, 1000), r.nextInt(-1000, 1000),
					r.nextInt(2, 100), Colors.randColor());

			cube.applyVerts(builder);

		}
	}
}
