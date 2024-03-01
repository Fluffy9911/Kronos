package com.kronos.graphixs.g2d.ui.components.clickables;

import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.listeners.MouseEventListener;
import com.kronos.graphixs.g2d.ui.listeners.adapter.InteractionType;
import com.kronos.graphixs.g2d.ui.listeners.adapter.MouseEvents;
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
			if (toggle && interacted) {
				interacted = false;
				current = getBase();
				click();
			} else {
				if (toggle && !interacted) {
					interacted = true;
				}
				current = getClicked();

				if (current == null) {
					current = getBase();
				}
				click();

			}
		}

	}

	@Override
	public void exited() {

		this.current = null;
		this.current = getBase();
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

		super.readWriteDatas(c);

		interacted = c.readOrWriteBoolean("is_interacted_with", interacted);
		toggle = c.readOrWriteBoolean("is_a_toggle", toggle);
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Graphixs2D g) {

		if (current == null) {
			current = getBase();
		}
		if (interacted) {
			current = getClicked();
		}
		this.drawHere(batch, current);
	}

	/**
	 * @return the hovering
	 */
	public boolean isHovering() {
		return hovering;
	}

	/**
	 * @param hovering the hovering to set
	 */
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

}
