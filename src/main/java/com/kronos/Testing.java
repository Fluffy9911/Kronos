package com.kronos;

import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.display.camera.Camera;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.internal.Cube;
import com.kronos.graphixs.shaders.PixelatedShaderProgram;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.graphixs.shaders.builtin.EdgeDetection;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;

public class Testing {
	public static int i = 0;

	public static void main(String[] args) {

		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 770;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 770;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "KronosTesting";
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return Colors.White;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return 60;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return defaultHints();
			}

		});
		Graphixs g = Kronos.graphixs;
//		g.createShader("pixelated", new EdgeDetection());
//		g.createShader("default", new ColoredShader());

//		g.manager_render.createRender("test1", "default");
//
//		g.manager_render.createRender("test2", "default");

		Camera cam = new Camera(new Vector3f(5, 5, 5), new Vector2f(1, 1), -1, 1, 500, 500);
		BasicMeshBuilder bmb = new BasicMeshBuilder();
		Cube cube = new Cube(1, 1, 1, 7, Colors.Green);
		Cube cub2 = new Cube(8, 1, 1, 7, Colors.Lavender);
		cube.applyVerts(bmb);
		bmb.addAll(BasicMeshBuilder.getAttribs());
		Mesh m = bmb.build();

		BasicMeshBuilder builder = new BasicMeshBuilder();
		builder.addAll(BasicMeshBuilder.getAttribs());
		cub2.applyVerts(builder);

		ShaderProgram sp = new ShaderProgram(Kronos.loader.tryLoad("shaders/vertex.vs"),
				Kronos.loader.tryLoad("shaders/fragment.fs"));
		sp.compileShader();
		PixelatedShaderProgram pixel = new PixelatedShaderProgram();
		EdgeDetection edge_detection = new EdgeDetection();

		pixel.compileShader();
		edge_detection.compileShader();
		edge_detection.setView(new Vector2f(500, 500));
		pixel.update(8, 0, 0, new Vector2f(500, 500));

		Mesh test = builder.build();

		cam.lookAt(new Vector3f(1, 1, 0));

		System.out.println(cam.toString());
		Kronos.startDrawing((a) -> {
			GL40.glEnable(GL40.GL_DEPTH_TEST);
			if (InputHandler.isKeyReleased(Keys.P)) {
				pixel.config();
			}
			if (InputHandler.isKeyReleased(Keys.K)) {
				if (i == 0) {
					Kronos.graphixs.setFrame();
					i = 1;
				} else {
					Kronos.graphixs.setFilled();
					i = 0;
				}
			}
			cam.update();
			sp.use();
			sp.addUniform("proj", cam.getProjection(500, 500));
			sp.addUniform("view", cam.getView());

			g.enablePPBuffer();
			g.clearScreen(Colors.White);

			test.render(sp);

			g.disablePPBuffer();

			edge_detection.use();
			g.enableEdgeBuffer();
			g.clearScreen(Colors.White);
			g.drawPPQuad(edge_detection);

			g.disableEdgeBuffer();
			g.clearScreen(Colors.White);
			pixel.use();

			g.drawToScreen(pixel);
			InputHandler.nextFrame();
		});

	}

}
