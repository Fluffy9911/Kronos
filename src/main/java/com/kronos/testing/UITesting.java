/**
 * 
 */
package com.kronos.testing;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
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
import com.kronos.io.Folder;
import com.kronos.plugin.PluginLoader;

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
		ch = Kronos.graphixs.g2d.createHandler();

		ch.createComps();
		ch.load();
		PluginLoader pl = new PluginLoader(Folder.createFolder("kronos/plugins"));
		PluginLoader pll = new PluginLoader(Folder.createFolder("kronos/plugins/libs"));
		try {
			pll.loadPlugins();
			pl.loadPlugins();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		AppLoader.begin(new UITesting());
	}
}
