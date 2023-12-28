package com.kronos.graphixs.g2d.ui;

import java.util.ArrayList;
import java.util.List;

public class States {
	public static final int IS_CONTEXT_CURRENT = 1;
	public static final int IS_CLICKED = 2;
	public static final int IS_HIDDEN = 3;
	public static final int IS_MOVEABLE = 4;
	public static final int IS_HOVERED = 5;

	List<Integer> states;

	public States() {
		states = new ArrayList<Integer>();
	}

	public boolean hasState(int i) {
		return states.contains(i);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(int e) {
		return states.add(e);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public boolean remove(int i) {
		return states.remove(new Integer(i));
	}

}
