/**
 * 
 */
package com.kronos.testing;

import java.io.File;
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
import com.kronos.io.assets.TextureBuffer;

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
		TextureBuffer b = new TextureBuffer();
		try {
			b.addIData("test1", new File("C:\\Users\\James.M\\OneDrive\\Desktop\\rt\\rr.png").toURL());
			b.addIData("test2", new File("C:\\Users\\James.M\\OneDrive\\Desktop\\rt\\rrr.png").toURL());
			b.addIData("test3", new File("C:\\Users\\James.M\\OneDrive\\Desktop\\rt\\r.png").toURL());
			b.addIData("test4", new File("C:\\Users\\James.M\\OneDrive\\Desktop\\rt\\t.png").toURL());
			b.addIData("test5", new File("C:\\Users\\James.M\\OneDrive\\Desktop\\rt\\tt.png").toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.loadToImages();
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
