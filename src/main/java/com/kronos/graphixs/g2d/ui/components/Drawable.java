package com.kronos.graphixs.g2d.ui.components;

import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;

public interface Drawable {
	public void render(TextureBatch batch, FontRenderer fr, Graphixs2D g);

}
