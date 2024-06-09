/**
 * 
 */
package com.kronos.graphixs.simplex;

import java.util.Map;

import com.kronos.Kronos;
import com.kronos.graphixs.LoopAccessor;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;

/**
 * 
 */
abstract class SimplexApp {

	public abstract void setup();

	public abstract void draw(LoopAccessor l);

	public ScreenConfig getConfig() {
		return new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 500;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return -1;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "SimplexApp-1.0";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 500;
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
	}

	public static void startApp(SimplexApp app) {
		Kronos.start(app.getConfig());
		app.setup();
		Simplex.init();
		Kronos.startDrawing((la) -> {
			app.draw(la);
		});
	}

}
