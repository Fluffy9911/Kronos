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
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
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

		Font fo = Kronos.loader.loadFont(f).deriveFont(50f);

		Graphixs g = Kronos.graphixs;
		Graphixs2D g2d = g.g2d;
		Texture t = Texture.singleColor(500, 500, Colors.Salmon);
		TextureBatch tb = g2d.createBatch();

		FontRenderer fr = new FontRenderer(fo);
		BufferedImage img = Kronos.loader.tryLoadImage("texture/test.png");
		Kronos.startDrawing((a) -> {
			g.clearScreen(Colors.White);
			tb.drawTexture(0, 0, 500, 500, img);
			tb.drawTexture(50, 50, 100, 100, t);
			fr.renderText("this is a massive sentence that just goes on and on till i run out of things to say...", 50,
					700, fo, java.awt.Color.BLACK, tb);
			tb.render();
			tb.end();

			g2d.renderQuad();
			g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
