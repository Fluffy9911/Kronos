package com.kronos;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.InputHandler;

public class Testing {
	public static int i = 200;

	public static void main(String[] args) throws MalformedURLException {
		Kronos.args = args;
		Kronos.enablePlugins(new File("plugins"));
		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 1000;
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 1000;
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
				return 0;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return defaultHints();
			}

		});
		System.out.println(Kronos.hello());

		Kronos.startDrawing((a) -> {

			InputHandler.nextFrame();
		});

	}

}
