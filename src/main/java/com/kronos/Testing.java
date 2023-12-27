package com.kronos;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.transform.FollowMouse;
import com.kronos.graphixs.g2d.ui.transform.KeepInBox;
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
		BaseComponent bc = new BaseComponent(new BasePosition(new ScreenCord(10, 10, 50, 50), g2d.getProvider()), false,
				false, false);

		BasePosition test = new BasePosition(new ScreenCord(70, 70, 50, 50), new ScreenCord(50, 50, 500, 500),
				g2d.getProvider());
		KeepInBox kib = new KeepInBox();
		FollowMouse fm = new FollowMouse();
		Texture t = Texture.singleColor(500, 500, Colors.Salmon);
		TextureBatch tb = g2d.createBatch();

		FontRenderer fr = new FontRenderer(fo);
		BufferedImage img = Kronos.loader.tryLoadImage("texture/test.png");
		Kronos.startDrawing((a) -> {

			g.clearScreen(Colors.White);
			test.drawDebug(tb);
			tb.render();
			tb.end();
			fm.reposition(g2d.getProvider(), test, null);
			kib.reposition(g2d.getProvider(), test, null);
			g2d.renderQuad();
			g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
