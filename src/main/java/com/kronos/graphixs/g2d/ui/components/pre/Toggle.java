package com.kronos.graphixs.g2d.ui.components.pre;

import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.components.clickables.BasicClickable;
import com.kronos.graphixs.texture.Texture;

public class Toggle extends BasicClickable {
	Texture base, clicked;
	Runnable onclick;

	public Toggle(BasePosition bp, String id, Texture base, Texture clicked) {
		super(bp, false, false, false, id);
		this.current = base;
		this.base = current;
		this.clicked = clicked;
		this.toggle = true;

	}

	@Override
	public void click() {

		if (onclick != null) {
			onclick.run();
		}

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
		return base;
	}

	@Override
	public Texture getClicked() {
		// TODO Auto-generated method stub
		return clicked;
	}

	/**
	 * @return the onclick
	 */
	public Runnable getOnclick() {
		return onclick;
	}

	/**
	 * @param onclick the onclick to set
	 */
	public void setOnclick(Runnable onclick) {
		this.onclick = onclick;
	}

	@Override
	public void hover(int mx, int my) {

	}

	@Override
	public void exited() {
		if (!interacted) {
			current = getBase();
		}
		hovering = false;
	}

}
