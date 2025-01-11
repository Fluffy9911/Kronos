/**
 * 
 */
package com.kronos.io.assets.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.kronos.io.assets.ResourceProvider;

/**
 * 
 */
public class External implements ResourceProvider {

	@Override
	public InputStream getStream(String in) {
		// TODO Auto-generated method stub
		try {
			return new FileInputStream(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public URL getLoc(String in) {
		// TODO Auto-generated method stub
		try {
			return new URL(in);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPath(String in) {
		// TODO Auto-generated method stub
		return in;
	}

}
