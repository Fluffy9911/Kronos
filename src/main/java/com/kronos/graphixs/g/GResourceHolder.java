/**
 * 
 */
package com.kronos.graphixs.g;

import java.util.HashMap;
import java.util.function.Supplier;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.graphixs.resources.Resource;

/**
 * 
 */
public abstract class GResourceHolder<T extends Resource> {
	HashMap<String, T> resources;
	Graphixs g;
	Logger l;

	public GResourceHolder(Graphixs g) {
		super();
		this.g = g;
		resources = new HashMap<String, T>();
		l = Kronos.debug.getLogger();
	}

	public abstract void load(Graphixs g);

	public abstract void close(Graphixs g);

	public T getResource(String s) {
		return resources.get(s);
	}

	public void registerResource(String s, Supplier<T> res) {
		resources.put(s, res.get());
	}

}
