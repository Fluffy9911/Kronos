package com.kronos;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kronos.core.SystemInfoUtil;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.SpriteBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.BasicMeshBuilder;
import com.kronos.graphixs.internal.Cube;
import com.kronos.graphixs.shaders.render.ShaderProgram;
import com.kronos.io.InputHandler;

public class Testing {
	public static int i = 200;

	public static void main(String[] args) throws MalformedURLException {
		Kronos.args = args;
		Kronos.enablePlugins(new File("plugins"));
		Kronos.startInDev(new ScreenConfig() {

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
				return Colors.White;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return defaultHints();
			}

		});
		// System.out.println(Kronos.hello());
		System.exit(0);
		Graphixs g = Kronos.graphixs;
		ShaderProgram sp = g.getShader("sbtext");
		Graphixs2D g2d = g.g2d;
		SpriteBatch sbat = new SpriteBatch(sp);
		sp.use();
		// sp.addUniform("spriteTexture", 0);
		FontRenderer fr = FontRenderer.createDefault();
		Random r = new Random();
		Kronos.startDrawing((a) -> {
			sbat.begin();
			sp.addUniform("projection", g2d.getProvider().collectTransform());

			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);
			sbat.draw(g.getTexture("slider"), 0, 0, 100, 100);

			sbat.end();
			System.out.println(SystemInfoUtil.getGPUVramInfo() + ": " + SystemInfoUtil.getCPUUsage());
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
