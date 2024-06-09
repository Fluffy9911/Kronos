/**
 * 
 */
package com.kronos.graphixs.simplex;

import com.kronos.graphixs.LoopAccessor;
import com.kronos.graphixs.color.Colors;

/**
 * 
 */
public class SimplexTester extends SimplexApp {

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(LoopAccessor l) {
		Simplex.drawTriangle(0, 500, 250, 0, 500, 500, Colors.Blue, Colors.Red, Colors.Green);
		Simplex.drawText(0, 10, "FPS: " + l.getFps(), Colors.Black);

		Simplex.getBatch().end();
		Simplex.getBatch().render();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimplexApp.startApp(new SimplexTester());

	}

}
