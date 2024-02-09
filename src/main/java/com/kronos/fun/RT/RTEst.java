/**
 * 
 */
package com.kronos.fun.RT;

import java.util.Map;

import org.joml.Vector3i;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.TextureBatch;

/**
 * 
 */
public class RTEst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Kronos.start(new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 400;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return -1;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "nulkllll";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 400;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return this.defaultHints();
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return Colors.Black;
			}
		});

		int w = 400;
		int h = 400;
		String ss = Kronos.loader.tryLoad("shaders/rtcompute.glsl");
		RTCompute comp = new RTCompute(ss, new Vector3i(w, h, 1), w, h);
		comp.compileShader();
		comp.render();
		Texture t = comp.waitUntilFinished();
		TextureBatch b = Kronos.graphixs.g2d.createBatch();
		Kronos.startDrawing((l) -> {
			b.drawTexture(0, 0, 400, 400, t);
			b.render();
			b.end();
			t.bind();
			Kronos.graphixs.writeTextureOut("rt_test");
		});
	}

}
