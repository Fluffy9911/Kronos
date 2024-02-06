/**
 * 
 */
package com.kronos.fun.rt;

import java.util.Map;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;

/**
 * 
 */
public class RTTest {
	public static void main(String[] args) {
		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 800;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return -1;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "RTTest";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 800;
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
		});
		Graphixs2D g2d = Kronos.graphixs.g2d;
		TextureBatch batch = Kronos.graphixs.g2d.createBatch();

	}
}
