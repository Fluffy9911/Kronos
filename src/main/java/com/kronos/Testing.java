package com.kronos;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import com.kronos.core.event.EngineListener;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.ComponentHandler;
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
		File f = new File(Kronos.loader.createFilePath("fonts", "Blox2.ttf"));
		Graphixs g = Kronos.graphixs;
		Font fo = Kronos.loader.loadFont(f).deriveFont(50f);
		Graphixs2D g2d = g.g2d;

		ComponentHandler ch = new ComponentHandler(g2d);
		BaseComponent bc = new BaseComponent(new BasePosition(new ScreenCord(10, 10, 50, 50), g2d.getProvider()), false,
				false, false, "test_component");
		ch.put("test_comp", bc);
		BufferedImage img = Kronos.loader.tryLoadImage("texture/test.png");
		ch.createComps();
		ch.load();
		Kronos.registerListener(new EngineListener() {

			@Override
			public void engineStart() {
				// TODO Auto-generated method stub

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

			g.clearScreen(Colors.White);
			ch.update();
			g2d.renderQuad();
			// g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
