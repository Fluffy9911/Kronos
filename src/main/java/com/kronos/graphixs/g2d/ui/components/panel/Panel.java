package com.kronos.graphixs.g2d.ui.components.panel;

import com.kronos.Kronos;
import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.texture.Texture;

public class Panel extends BaseComponent {
	Texture panel, top;

	public Panel(BasePosition bp, boolean cdren, boolean moveable, String id) {
		super(bp, cdren, moveable, false, id);
		panel = Kronos.graphixs.textures.get("bg");
		top = Kronos.graphixs.textures.get("top");

	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		this.drawHere(batch, panel);
		int fh = (int) fr.getSize(this.id(), fr.useDefaultFont()).getHeight();
		fr.renderText(this.id(), (int) this.bp.pos().getX() + 2, (int) this.bp.pos().getY() + 2,
				(int) fr.getSize(this.id(), fr.useDefaultFont()).getWidth(), fh, batch);

	}

}
