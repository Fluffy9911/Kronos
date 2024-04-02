/**
 * 
 */
package com.kronos.testing;

import java.io.File;
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
import com.kronos.io.ListTesting;
import com.kronos.io.streamedlists.StreamedDynamicList;

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
		StreamedDynamicList<String> sdl = new StreamedDynamicList<>(new File("kronos\\sdtestperf"));
		sdl.setChunkSize(12);
		ListTesting.<String>test(sdl, new String[] { "Hello" }, 900000, 10000);
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
		// TODO Auto-generated method stub
		ch.saveData();
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
		// AppLoader.addPluginNature(new File("kronos/plugins"));
		AppLoader.begin(new UITesting());
	}
}
