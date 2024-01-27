package com.kronos.fun.ca;

import java.util.HashMap;
import java.util.Map;

public class CaManager {
	int[][] board;
	int width, height;
	HashMap<Integer, Cell> cells;
	static int id = 0;

	public CaManager(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		board = new int[width][height];
	}

	public void register(Cell c) {
		cells.put(id, c);
		c.recieveId(id);
		id++;

	}

	public boolean isWithinRange(int x, int y) {
		return ((x >= 0 && y >= 0) && (x < width && y < height));
	}

	public void step() {
		for (Map.Entry<Integer, Cell> entry : cells.entrySet()) {
			Integer key = entry.getKey();
			Cell val = entry.getValue();

		}
	}

}
