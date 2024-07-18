/**
 * 
 */
package com.kronos.graphixs.g2d.ui.components.clickables;

import java.awt.Rectangle;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.handlers.ITextHandler;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureBuilder;

/**
 * 
 */
public class TextButton extends BasicClickable implements ITextHandler {
	public static float font_scale = 25f;

	String text;
	Color bg = Colors.Gray, txt = Colors.White, outline = Colors.Black;

	/**
	 * @param bp
	 * @param cdren
	 * @param moveable
	 * @param hidden
	 * @param id
	 */
	public TextButton(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id) {
		super(bp, cdren, moveable, hidden, id);

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
		return TextureBuilder.buildTextureBordered(size(FontRenderer.createDefault()), 2, bg, outline);
	}

	@Override
	public Texture getHover() {
		// TODO Auto-generated method stub
		return TextureBuilder.buildTextureBordered(size(FontRenderer.createDefault()), 2, bg.brighter(0.75f), outline);
	}

	@Override
	public Texture getClicked() {
		// TODO Auto-generated method stub
		return getHover();
	}

	@Override
	public Rectangle size(FontRenderer fr) {
		// TODO Auto-generated method stub
		return FontRenderer.getSize(text, fr.useDefaultFont().deriveFont(font_scale)).getBounds();
	}

	@Override
	public BasePosition asPosition(FontRenderer fr) {
		// TODO Auto-generated method stub
		return BasePosition.fromBounds(size(fr), getLocalProvider());

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		fr.renderTextConstraints(text, (int) this.getPosition().pos().getX(), (int) this.getPosition().pos().getY(),
				(int) this.getPosition().pos().getW(), (int) this.getPosition().pos().getH(),
				fr.useDefaultFont().deriveFont(font_scale), txt.asCol(), batch);
	}

}
