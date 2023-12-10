package com.kronos.graphixs.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ResourceManager {

	List<Resource> resources;

	public ResourceManager() {
		resources = new ArrayList<Resource>();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return resources.contains(o);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Resource e) {

		return resources.add(e);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return resources.remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Resource> c) {
		return resources.addAll(c);
	}

	/**
	 * @param operator
	 * @see java.util.List#replaceAll(java.util.function.UnaryOperator)
	 */
	public void replaceAll(UnaryOperator<Resource> operator) {
		resources.replaceAll(operator);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#get(int)
	 */
	public Resource get(int index) {
		return resources.get(index);
	}

	public void loadResources() {

		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			Resource rc = (Resource) iterator.next();
			rc.load();
		}

	}

	public void close() {
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			Resource rc = (Resource) iterator.next();
			rc.close();
		}
	}

}
