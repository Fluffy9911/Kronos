/**
 * 
 */
package com.kronos.core.util;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;

/**
 * 
 */
public abstract class BufferedStreamReader {

	public abstract void onRecieve(String s);

	public void begin(Executor e, InputStream is) {
		e.execute(() -> {
			Scanner s = new Scanner(is);
			while (s.hasNext()) {
				onRecieve(s.nextLine());
			}
			s.close();
		});

	}

}
