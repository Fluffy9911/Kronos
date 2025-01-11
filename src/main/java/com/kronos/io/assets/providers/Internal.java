/**
 * 
 */
package com.kronos.io.assets.providers;

import java.io.InputStream;
import java.net.URL;

import com.kronos.io.assets.ResourceProvider;

/**
 * 
 */
public class Internal implements ResourceProvider {

	@Override
	public InputStream getStream(String in) {
		// TODO Auto-generated method stub
		return this.getClass().getResourceAsStream(in);
	}

	@Override
	public URL getLoc(String in) {
		// TODO Auto-generated method stub
		return this.getClass().getResource(in);
	}

	@Override
	public String getPath(String in) {
		// TODO Auto-generated method stub
		return getLoc(in).getPath();
	}

}
