package com.kronos.graphixs.g2d.ui.components.clickables;

import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.listeners.MouseEventListener;
import com.kronos.graphixs.g2d.ui.listeners.adapter.InteractionType;
import com.kronos.graphixs.g2d.ui.listeners.adapter.MouseEvents;
import com.kronos.io.Config;

public abstract class BasicClickable extends BaseComponent implements MouseEvents, ClickListener {
	boolean interacted = false, toggle = false;
	Texture current;

	public BasicClickable(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id) {
		super(bp, cdren, moveable, hidden, id);
		this.setUpdateListeners(true);
	}

	@Override
	public void hover(int mx, int my) {
		current = getHover();
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

}
