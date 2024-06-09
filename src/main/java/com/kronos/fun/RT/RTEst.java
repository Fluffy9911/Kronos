/**
 * 
 */
package com.kronos.fun.RT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.joml.Vector3i;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.texture.Texture;

/**
 * 
 */
public class RTEst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Kronos.startInDev(new ScreenConfig() {

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
		String ss = "null";
		try {
			ss = Files.readString(Path.of(
					"C:\\Users\\James.M\\Documents\\Media\\Repos\\Kronos\\src\\main\\resources\\shaders\\rtcompute.glsl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RTCompute comp = new RTCompute(ss, new Vector3i(w, h, 1), w, h);
		comp.compileShader();
		comp.render();
		Texture t = comp.waitUntilFinished();
		TextureBatch b = Kronos.graphixs.g2d.createBatch(Kronos.graphixs.g2d);
		Kronos.startDrawing((l) -> {
			b.drawTexture(0, 0, 400, 400, t);
			b.render();
			b.end();
			t.bind();
			Kronos.graphixs.writeTextureOut("rt_test");
		});
	}

}
