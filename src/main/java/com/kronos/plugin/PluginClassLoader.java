package com.kronos.plugin;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
	public PluginClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		try {
			// Check if the class is already loaded by this classloader
			Class<?> c = findLoadedClass(name);
			if (c != null) {
				return c;
			}

			// Load the class from the parent classloader if it's not already loaded
			return super.loadClass(name, resolve);
		} catch (ClassNotFoundException e) {
			return super.loadClass(name, resolve);
		}
	}
}
