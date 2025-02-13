package com.kronos.graphixs.g2d.ui;

import java.awt.Rectangle;

import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureBuilder;
import com.kronos.io.InputHandler;

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
		Texture a = Texture.singleColor((int) ap.getW(), (int) ap.getH(), Colors.Lime);

		Texture p = TextureBuilder.buildTextureBordered((int) ap.getW(), (int) ap.getH(), 6, Colors.Salmon, Colors.Red);

		if (mouseInsideAP()) {
			batch.drawTexture((int) ap.getX(), (int) ap.getY(), (int) ap.getW(), (int) ap.getH(), a, "highlight_g");
		} else {
			batch.drawTexture((int) ap.getX(), (int) ap.getY(), (int) ap.getW(), (int) ap.getH(), a);
		}
		if (mouseInsidePos()) {
			batch.drawTexture((int) pos.getX(), (int) pos.getY(), (int) pos.getW(), (int) pos.getH(), p, "highlight_g");
		} else {
			batch.drawTexture((int) pos.getX(), (int) pos.getY(), (int) pos.getW(), (int) pos.getH(), p);
		}
	}

	public static BasePosition single(int x, int y, int w, int h, ScreenProvider prov) {
		return new BasePosition(new ScreenCord(x, y, w, h), new ScreenCord(x, y, w, h), prov);
	}

	/**
	 * @return the provider
	 */
	public ScreenProvider getProvider() {
		return provider;
	}

	public boolean mouseInsidePos() {
		return pos.contains((int) InputHandler.getLastMouseX(), (int) InputHandler.getLastMouseY());
	}

	public boolean mouseInsideAP() {
		return ap.contains((int) InputHandler.getLastMouseX(), (int) InputHandler.getLastMouseY());
	}

	public BasePosition translate(int x, int y, int w, int h) {
		BasePosition bp = new BasePosition(new ScreenCord(pos.getX() + x, pos.getY() + y, w, h),
				new ScreenCord(ap.getX() + x, ap.getY() + y, w, h), this.getProvider());
		return bp;
	}

	public static BasePosition fromBounds(Rectangle rec, ScreenProvider sp) {
		return new BasePosition(new ScreenCord(rec.x, rec.y, rec.width, rec.height), sp);
	}
}
