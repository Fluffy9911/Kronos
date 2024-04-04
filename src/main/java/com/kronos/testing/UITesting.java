/**
 * 
 */
package com.kronos.testing;

import java.util.List;
import java.util.Map;

import com.kronos.Kronos;
import com.kronos.gameFramework.game.apploading.AppLoader;
import com.kronos.gameFramework.game.apploading.Game;
import com.kronos.graphixs.LoopAccessor;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.ui.ComponentHandler;
import com.kronos.io.file.EasyConfig;

/**
 * 
 */
public class UITesting extends Game {
	ComponentHandler ch;

	@Override
	public List<String> requestedTextures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String programLocation() {
		// TODO Auto-generated method stub
		return "test";
	}

	@Override
	public void init() {
		ch = Kronos.graphixs.g2d.createHandler(Kronos.graphixs.g2d);

		ch.createComps();
		ch.load();

	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnd() {

		ch.saveData();
		System.out.println(Kronos.hello());
	}

	@Override
	public ScreenConfig getScreen() {
		// TODO Auto-generated method stub
		return new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 900;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return -1;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "ui-test";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 900;
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

	@Override
	public void loop(LoopAccessor la) {
		ch.update();
	}

	public static void main(String[] args) {
		Kronos.args = args;
		EasyConfig c = new EasyConfig("kronos\\conftest.txt");

		c.appendComment("This is a comment");

		c.appendInt("testint", 5, 0, 10);

		c.appendFloat("test_float", 1, 0.5f, 3.5f);

		c.appendComment("Comments?");

		c.appendIntArray("testiarr", new int[] { 4, 78, 8, 65, 3, 8 }, 0, 110);
		c.appendSection("Test Section");
		c.appendIntAdv("testiadv", "a test value for description stuff", 5, 0, 100);

		c.generateConfigFile(c);

		AppLoader.begin(new UITesting());

	}
}
