package com.kronos.graphixs.g2d.ui;

import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.display.textures.TextureBuilder;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.TextureBatch;

public class BasePosition implements ComponentPosition {
	ScreenCord pos, ap;
	int xpad, ypad;
	ScreenProvider provider;

	public BasePosition(ScreenCord pos, ScreenCord ap, int xpad, int ypad, ScreenProvider provider) {
		super();
		this.pos = pos;
		this.ap = ap;
		this.xpad = xpad;
		this.ypad = ypad;
		this.provider = provider;
	}

	public BasePosition(ScreenCord pos, ScreenCord ap, int xpad, ScreenProvider provider) {

		this(pos, ap, xpad, 0, provider);
	}

	public BasePosition(ScreenCord pos, ScreenCord ap, ScreenProvider provider) {
		this(pos, ap, 0, 0, provider);
	}

	public BasePosition(ScreenCord pos, ScreenProvider provider) {
		this(pos, new ScreenCord(0, 0, provider.getConfig().width(), provider.getConfig().height()), 0, 0, provider);
	}

	@Override
	public ScreenCord pos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public int xpad() {
		// TODO Auto-generated method stub
		return xpad;
	}

	@Override
	public int ypad() {
		// TODO Auto-generated method stub
		return ypad;
	}

	@Override
	public ScreenCord anchoredPos() {
		// TODO Auto-generated method stub
		return ap;
	}

	public void setPos(ScreenCord pos) {
		this.pos = pos;
	}

	public void setAp(ScreenCord ap) {
		this.ap = ap;
	}

	public void setXpad(int xpad) {
		this.xpad = xpad;
	}

	public void setYpad(int ypad) {
		this.ypad = ypad;
	}

	public void drawDebug(TextureBatch batch) {
		// Texture a = Texture.singleColor((int) ap.getW(), (int) ap.getH(),
		// Colors.Lime);
		Texture a = TextureBuilder.buildRadialGradientTexture((int) ap.getW(), (int) ap.getH(), Colors.White,
				Colors.Teal);
		Texture p = TextureBuilder.buildTextureBordered((int) ap.getW(), (int) ap.getH(), 6, Colors.Salmon, Colors.Red);
		batch.drawTexture((int) ap.getX(), (int) ap.getY(), (int) ap.getW(), (int) ap.getH(), a);
		batch.drawTexture((int) pos.getX(), (int) pos.getY(), (int) pos.getW(), (int) pos.getH(), p);
	}

	public static BasePosition single(int x, int y, int w, int h, ScreenProvider prov) {
		return new BasePosition(new ScreenCord(x, y, w, h), new ScreenCord(x, y, w, h), prov);
	}

}
