package com.kronos;

import java.util.Map;

import com.kronos.core.event.EngineListener;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.transform.AreaConform;
import com.kronos.graphixs.g2d.ui.transform.ConformType;
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

		BasePosition bp = new BasePosition(new ScreenCord(60, 60, 600, 600), g2d.getProvider());
		BasePosition b1 = BasePosition.single(70, 70, 40, 40, g2d.getProvider());
		BasePosition b2 = BasePosition.single(85, 85, 40, 40, g2d.getProvider());
		BasePosition b3 = BasePosition.single(95, 95, 85, 40, g2d.getProvider());
		AreaConform ac = new AreaConform(ConformType.TOP, 5);

		TextureBatch b = g2d.createBatch();

		Kronos.registerListener(new EngineListener() {

			@Override
			public void engineStart() {
				// TODO Auto-generated method stub

			}

			@Override
			public void engineEnd() {

			}

			@Override
			public void configChange(Config c) {
				// TODO Auto-generated method stub

			}
		});
		Kronos.startDrawing((a) -> {

			g.clearScreen(Colors.White);
			bp.drawDebug(b);
			b1.drawDebug(b);
			b2.drawDebug(b);
			b3.drawDebug(b);
			b.render();
			g2d.renderQuad();
			// ac.reposition(g2d.getProvider(), bp, b1, b2, b3);
			// g.glErrors();
			InputHandler.nextFrame();
		});

	}

}
