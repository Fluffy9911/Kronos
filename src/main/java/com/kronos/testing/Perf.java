/**
 * 
 */
package com.kronos.testing;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.joml.Vector2f;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.render2d.Atlas;
import com.kronos.graphixs.g2d.render2d.AtlasBuilder;
import com.kronos.graphixs.g2d.render2d.Render2D;
import com.kronos.graphixs.shaders.render.RenderShader;
import com.kronos.graphixs.texture.AssetLoader;
import com.kronos.graphixs.texture.TextureBuilder;
import com.kronos.io.InputHandler;

/**
 * 
 */
public class Perf {
	public static boolean at = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ScreenConfig sc = new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 1920;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "PTest";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 1080;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return this.defaultHints();
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return Colors.White;
			}
		};
		AssetLoader asl = new AssetLoader();
		asl.addBasePath("main/resources");
		asl.add("test", "texture");
		asl.add("up", "texture");
		asl.add("top", "texture");
		asl.searchForAssets();
		asl.loadAsset();

		asl.getLoaded().put("button", Kronos.loader.tryLoadImage("texture/button.png"));
		asl.getLoaded().put("test", Kronos.loader.tryLoadImage("texture/test.png"));
		asl.getLoaded().put("top", Kronos.loader.tryLoadImage("texture/top.png"));
		asl.getLoaded().put("up", Kronos.loader.tryLoadImage("texture/up.png"));

		Kronos.startInDev(sc);

		RenderShader rs = new RenderShader(asl.readAll("src/main/resources/shaders/REVS.vs"),
				asl.readAll("src/main/resources/shaders/RE.fs")) {

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}

			@Override
			public void close() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setUniforms() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setAttribs() {
				// TODO Auto-generated method stub

			}
		};
		RenderShader pir = new RenderShader(asl.readAll("src/main/resources/shaders/REVS.vs"),
				asl.readAll("src/main/resources/shaders/pir.fs")) {

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}

			@Override
			public void close() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setUniforms() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setAttribs() {
				// TODO Auto-generated method stub

			}
		};

		Kronos.graphixs.createShader("re2d", rs);
		Kronos.graphixs.createShader("pir", pir);
		// System.exit(0);
		Atlas a = AtlasBuilder.buildAtlas((HashMap<String, BufferedImage>) asl.getLoaded());
		Render2D r2d = new Render2D(a, rs);

		TextureBatch tb = Kronos.graphixs.g2d.createBatch(Kronos.graphixs.g2d);
		// System.exit(0);
		Kronos.startDrawing((la) -> {
			if (InputHandler.isRightReleased()) {
				at = !at;
			}
			if (at) {

				r2d.addToBuffer(0, -1, 1, 1, -1);

				r2d.prepare();
				r2d.sus();
				r2d.render();
				r2d.end();
			} else {

//				Mesh m = Builtin.screenQuad();
//
//				m.render(pir);

				tb.drawTexture(0, 0, 100, 100,
						TextureBuilder.buildTextureBordered(10, 10, 1, Colors.Black, Colors.Red));

				tb.render();
				tb.end();
			}
			InputHandler.nextFrame();
		});
	}

	public static void testSplitFloat() {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			int v1 = random.nextInt(1, 100);
			int v2 = random.nextInt(1, 100);
			float value = AtlasBuilder.toFloat((short) v1, (short) v2);
			Vector2f result = Render2D.splitFloat(value);

			if (result.x != v1 || result.y != v2) {
				System.out.println("Test failed for value: " + value);
				System.out.println("Expected: " + v1 + ":" + v2);
				System.out.println("Got: " + result.x() + ":" + result.y());
			}
		}
		System.out.println("All tests completed.");
	}
}
