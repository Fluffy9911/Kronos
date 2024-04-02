/**
 * 
 */
package com.kronos.io;

import java.util.Random;

import com.kronos.Kronos;
import com.kronos.io.streamedlists.StreamedDynamicList;

/**
 * 
 */
public class ListTesting {

	public static <T> void test(StreamedDynamicList<T> sd, T[] rw, int size, int times) {
		int tr = 0;
		int tw = 0;
		long trr = 0;
		long tww = 0;
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			sd.appendIndex(i, rw[r.nextInt(0, rw.length)]);
		}

		for (int i = 0; i < times; i++) {

			if (r.nextBoolean()) {
				long t = System.nanoTime();
				T y = sd.readIndex(r.nextInt(0, size));
				trr += (System.nanoTime() - t);
				tr++;
				System.out.println("Read:" + (System.nanoTime() - t));
			} else {
				long t = System.nanoTime();
				sd.appendIndex(r.nextInt(0, size), rw[r.nextInt(0, rw.length)]);
				tww += (System.nanoTime() - t);
				tw++;
				System.out.println("Write:" + (System.nanoTime() - t));
			}

		}

		Kronos.debug.getLogger().debug("List Read Time AVG: {}, List Time Write AVG: {}", (trr / tr), (tww / tw));

	}

}
