/**
 * 
 */
package com.kronos.testing;

import java.io.File;
import java.util.Map;
import java.util.Random;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.streamedlists.StreamedDynamicList;

/**
 * 
 */
public class Perf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScreenConfig sc = new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 500;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "PTest";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 250;
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
		StreamedDynamicList<Integer> list = new StreamedDynamicList<Integer>(new File("kronos\\testsdl"));
		list.setChunkSize(1000);
		Random r = new Random();
		for (int i = 0; i < 40; i++) {
			list.appendIndex(i, r.nextInt(0, 60));
		}
		// System.out.println(list.size());
		for (int i = 0; i < 40; i++) {
			System.out.println(list.readIndex(i));

		}
		System.exit(0);
		Kronos.start(sc);
		Kronos.startDrawing((la) -> {
			System.out.println(la.getFps() + ":" + la.getDeltaTime() + ":" + la.target());
		});
	}

}
