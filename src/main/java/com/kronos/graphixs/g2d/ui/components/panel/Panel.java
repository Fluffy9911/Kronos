package com.kronos.graphixs.g2d.ui.components.panel;

import com.kronos.Kronos;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;

public class Panel extends BaseComponent {
	Texture panel, top;

	public Panel(BasePosition bp, boolean cdren, boolean moveable, String id) {
		super(bp, cdren, moveable, false, id);
		panel = Kronos.graphixs.textures.get("bg");
		top = Kronos.graphixs.textures.get("top");

	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Graphixs2D g) {
		this.drawHere(batch, panel);

	}

}
