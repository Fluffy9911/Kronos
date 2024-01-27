package com.kronos;

import java.util.Map;

import com.kronos.core.event.EngineListener;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.builder.ShapeRenderer;
import com.kronos.graphixs.g2d.pixelcanvas.Canvas2D;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.io.Config;
import com.kronos.io.InputHandler;

public class Testing {
	public static int i = 0;

	public static void main(String[] args) {

		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 900;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 900;
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

		Kronos.startDrawing((a) -> {
			g2d.getProvider().update();
			g.clearScreen(Colors.White);
			Color col = Colors.randColor();
			sr.renderShape("test_shape", col, 0, 0, 400, 400, tb);
			tb.drawTexture(401, 0, 400, 400, sr.shapes.get("test_shape").toTexture());
			tb.render();
			tb.end();
			g2d.renderQuad();
			c.noise();
			g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
