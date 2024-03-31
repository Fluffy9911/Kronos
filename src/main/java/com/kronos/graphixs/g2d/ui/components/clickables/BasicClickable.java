package com.kronos.graphixs.g2d.ui.components.clickables;

import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.listeners.MouseEventListener;
import com.kronos.graphixs.g2d.ui.listeners.adapter.InteractionType;
import com.kronos.graphixs.g2d.ui.listeners.adapter.MouseEvents;
import com.kronos.graphixs.texture.Texture;
import com.kronos.io.Config;

public abstract class BasicClickable extends BaseComponent implements MouseEvents, ClickListener {
	protected boolean interacted = false, toggle = false, hovering = false;
	protected Texture current;
	protected int lx = 0;
	protected int ly = 0;

	public BasicClickable(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id) {
		super(bp, cdren, moveable, hidden, id);
		this.setUpdateListeners(true);
		// current = getBase();
	}

	@Override
	public void hover(int mx, int my) {
		hovering = true;
		current = getHover();
		lx = mx;
		ly = my;
		if (current == null) {
			current = getBase();
		}
	}

	@Override
	public void clicked(InteractionType type) {
		if (type == InteractionType.LEFT_CLICK) {
			current = getClicked();
			interacted = true;
			if (current == null) {
				current = getBase();
			}
			click();
		}
		if (toggle && interacted) {
			interacted = false;
			current = getBase();
		}
	}

	@Override
	public void exited() {
		current = getBase();
		hovering = false;
	}

	@Override
	public void createListeners() {

		super.createListeners();
		this.registerListener(new MouseEventListener(this));

	}

	public abstract Texture getBase();

	public abstract Texture getHover();

	public abstract Texture getClicked();

	@Override
	public void readWriteDatas(Config c) {
		// TODO Auto-generated method stub
		super.readWriteDatas(c);
		interacted = c.readOrWriteBoolean("is_interacted_with", false);
		toggle = c.readOrWriteBoolean("is_a_toggle", false);
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		this.drawHere(batch, current);
	}

}
