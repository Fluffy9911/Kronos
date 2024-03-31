package com.kronos.graphixs.g2d.ui.components.pre;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.components.clickables.BasicClickable;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureBuilder;

public class TextButton extends BasicClickable {
	protected Texture base, clicked, hover;
	public String text;

	public TextButton(BasePosition bp, String id) {
		super(bp, false, false, false, id);

	}

	public void create(Color b, Color s, Color h, String text) {
		base = TextureBuilder.buildTextureBordered((int) bp.pos().getW(), (int) bp.pos().getH(), 2, b, s);
		hover = TextureBuilder.buildTextureBordered((int) bp.pos().getW(), (int) bp.pos().getH(), 2, s, b);
		clicked = TextureBuilder.buildTextureBordered((int) bp.pos().getW(), (int) bp.pos().getH(), 2, b, h);
		this.text = text;
		this.current = base;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public Texture getBase() {
		// TODO Auto-generated method stub
		return base;
	}

	@Override
	public Texture getHover() {
		// TODO Auto-generated method stub
		return hover;
	}

	@Override
	public Texture getClicked() {
		// TODO Auto-generated method stub
		return clicked;
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		// TODO Auto-generated method stub
		super.render(batch, fr, g);

		fr.renderTextConstraints(text, (int) this.getPosition().pos().getX(), (int) this.getPosition().pos().getY(),
				(int) this.getPosition().pos().getW(), (int) this.getPosition().pos().getH(),
				fr.useDefaultFont().deriveFont(25f), java.awt.Color.BLACK, batch);
		if (hovering) {
			renderHovering(lx, ly, batch, fr, g);

		}

	}

	public void renderHovering(int lx, int ly, TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {

	}

	public void toggle() {
		this.toggle = true;
	}

	public boolean interacted() {
		return interacted;
	}

}
