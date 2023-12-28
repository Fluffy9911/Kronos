package com.kronos.graphixs.g2d.ui.components;

import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;

public class Text extends BaseComponent implements Drawable {
	String text;
	int x, y, dw, dh;

	public Text(BasePosition bp, String text, int x, int y, int dw, int dh) {
		super(bp, false, false, false);
		this.text = text;
		this.x = x;
		this.y = y;
		this.dw = dw;
		this.dh = dh;
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Graphixs2D g) {
		fr.renderText(text, x, y, dw, dh, batch);
	}

}
