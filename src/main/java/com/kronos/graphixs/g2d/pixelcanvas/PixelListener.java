package com.kronos.graphixs.g2d.pixelcanvas;

import org.joml.Vector2i;
import org.joml.Vector4f;

public interface PixelListener {

	public Vector4f getColor(int[][] beside, Vector2i pos);

}
