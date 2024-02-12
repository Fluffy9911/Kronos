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
			try (Scanner s = new Scanner(is)) {
				while (s.hasNext()) {
					String ss = s.nextLine();
					onRecieve(ss);
					System.out.println("Received: " + ss);
				}
			} catch (Exception ex) {
				ex.printStackTrace(); // Add proper error handling/logging
			}
		});
	}

}
