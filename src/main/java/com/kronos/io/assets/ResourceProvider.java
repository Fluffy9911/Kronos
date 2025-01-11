/**
 * 
 */
package com.kronos.io.assets;

import java.io.InputStream;
import java.net.URL;

/**
 * 
 */
public interface ResourceProvider {

	public InputStream getStream(String in);

	public URL getLoc(String in);

	public String getPath(String in);

}
