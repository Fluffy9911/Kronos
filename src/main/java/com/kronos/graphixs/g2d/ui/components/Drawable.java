package com.kronos.graphixs.g2d.ui.components;

import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;

public interface Drawable {
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g);

}
