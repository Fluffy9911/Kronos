package com.kronos.fun.ca;

import org.joml.Vector2i;

public interface Cell {

	public Vector2i move(int[][] loc, int[][] board, CaManager ca);

	public void recieveId(int id);

}
