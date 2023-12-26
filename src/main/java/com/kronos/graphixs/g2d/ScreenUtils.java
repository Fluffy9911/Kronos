package com.kronos.graphixs.g2d;

import java.util.Map;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.ScreenConfig;

public class ScreenUtils {

	public static ScreenConfig updateDimensions(ScreenConfig sc, int w, int h) {
		return new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return w;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return sc.updateTime();
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return sc.title();
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return h;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return sc.getHints();
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return sc.getClearColor();
			}
		};
	}

}
