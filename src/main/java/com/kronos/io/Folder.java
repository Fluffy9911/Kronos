/**
 * 
 */
package com.kronos.io;

import java.io.File;

/**
 * 
 */
public class Folder {

	public static File createFolder(String path) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

}
