package com.kronos;

import java.util.Map;

import com.kronos.core.event.EngineListener;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.ComponentHandler;
import com.kronos.graphixs.g2d.ui.components.batched.IncrementNumber;
import com.kronos.graphixs.g2d.ui.components.panel.Panel;
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
				return -1;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return defaultHints();
			}

		});

		Graphixs g = Kronos.graphixs;

		Graphixs2D g2d = g.g2d;

		ComponentHandler ch = new ComponentHandler(g2d);
		Panel p = new Panel(BasePosition.single(40, 40, 300, 400, g2d.getProvider()), false, true, "test_panel");
		ch.put("test_panel", p);
		IncrementNumber ic = new IncrementNumber(BasePosition.single(95, 95, 120, 40, g2d.getProvider()), false, false,
				false, "test_increment");
		ch.put("test_increment", ic);

		ch.createComps();
		ch.load();

		Kronos.registerListener(new EngineListener() {

			@Override
			public void engineStart() {
				ch.load();

			}

			@Override
			public void engineEnd() {
				ch.write();
			}

			@Override
			public void configChange(Config c) {
				// TODO Auto-generated method stub

			}
		});
		Kronos.startDrawing((a) -> {
			g2d.getProvider().update();
			g.clearScreen(Colors.White);
			ch.update();
			g2d.renderQuad();
			// ac.reposition(g2d.getProvider(), bp, b1, b2, b3);
			g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
