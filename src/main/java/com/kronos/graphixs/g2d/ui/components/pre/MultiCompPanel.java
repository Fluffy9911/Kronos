package com.kronos.graphixs.g2d.ui.components.pre;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.components.clickables.BasicClickable;
import com.kronos.graphixs.g2d.ui.transform.PositionX;
import com.kronos.graphixs.g2d.ui.transform.PositionY;
import com.kronos.graphixs.texture.Texture;

public class MultiCompPanel extends BaseComponent {
	String name;
	int num = 0, pad = 5;
	public List<BaseComponent> base, render;
	List<List<BaseComponent>> renders;
	Texture bg;
	BasicClickable back, foreward;

	public MultiCompPanel(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id,
			String dispname) {
		super(bp, cdren, moveable, hidden, id);
		this.name = dispname;
		render = new ArrayList<BaseComponent>();
		base = new ArrayList<BaseComponent>();

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		for (Iterator iterator = render.iterator(); iterator.hasNext();) {
			BaseComponent b = (BaseComponent) iterator.next();

			b.update();
		}
	}

	public MultiCompPanel add(BaseComponent bc) {
		this.base.add(bc);
		return this;
	}

//for a list that only renders what is seen done by size
	public void build() {
		renders = new ArrayList<List<BaseComponent>>();
		int space = (int) (this.getPosition().pos().getH());
		int spacey = (int) (this.getPosition().pos().getW());
		ArrayList<BaseComponent> c = new ArrayList<BaseComponent>();
		for (Iterator iterator = base.iterator(); iterator.hasNext();) {
			BaseComponent comp = (BaseComponent) iterator.next();
			comp.getPosition().anchoredPos().set(this.bp.pos());
			this.kib.reposition(this.bp.getProvider(), (BasePosition) comp.getPosition(), null);
			if (comp.getPosition().pos().getW() > spacey) {
				comp.getPosition().pos().setW(spacey);
				comp.forEachChild((child, h) -> child.getPosition().pos().set(comp.getPosition().pos()));
			}
//still space
			if ((space - (comp.getPosition().pos().getH() + pad)) > 0) {
				comp.getPosition().pos().setY(this.getPosition().pos().getH() - space + pad);
				comp.forEachChild((child, h) -> child.getPosition().pos().set(comp.getPosition().pos()));
				space -= comp.getPosition().pos().getH() + pad;

				c.add(comp);

			} else {
				renders.add(c);
				c = new ArrayList<BaseComponent>();
				space = (int) (this.getPosition().pos().getH());
			}

		}
		renders.add(c);
		this.render = renders.get(0);
	}

	public List<BaseComponent> getNext() {
		if ((num + 1) == renders.size()) {
			num = 0;
			return renders.get(num);
		} else {
			num++;
			return renders.get(num);
		}
	}

	public List<BaseComponent> getPrev() {
		if ((num - 1) <= 0) {
			if (num < 0) {
				num = 0;

				num = renders.size();
			}
			return renders.get(num);
		} else {
			num--;
			if (num < 0) {
				num = 0;
			}
			return renders.get(num);
		}
	}

	public void positionButtonBack(PositionX px, PositionY py) {
		if (back != null) {

			back.getPosition().pos().setToCenterOf(this.bp.pos(), px, py);

		}
	}

	public void positionButtonForeward(PositionX px, PositionY py) {
		if (foreward != null) {

			foreward.getPosition().pos().setToCenterOf(this.bp.pos(), px, py);

		}
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		if (bg != null) {
			batch.drawTexture((int) this.getPosition().pos().getX(), (int) this.getPosition().pos().getY(),
					(int) this.getPosition().pos().getW(), (int) this.getPosition().pos().getH(), bg);
		}
		for (Iterator iterator = render.iterator(); iterator.hasNext();) {
			BaseComponent baseComponent = (BaseComponent) iterator.next();
			baseComponent.render(batch, fr, g);
		}
		if (back != null) {
			back.render(batch, fr, g);
			back.update();
		}
		if (foreward != null) {
			foreward.render(batch, fr, g);
			foreward.update();
		}

//		Utils.drawTextbox("    " + name + "    ", fr, batch,
//				new Vector2i((int) this.getPosition().pos().getX(), (int) this.getPosition().pos().getH()),
//				Color.BLACK);
	}

	/**
	 * @return the bg
	 */
	public Texture getBg() {
		return bg;
	}

	/**
	 * @param bg the bg to set
	 */
	public void setBg(Texture bg) {
		this.bg = bg;
	}

	/**
	 * @return the back
	 */
	public BasicClickable getBack() {
		return back;
	}

	/**
	 * @param back the back to set
	 */
	public void setBack(BasicClickable back) {
		this.back = back;
	}

	/**
	 * @return the foreward
	 */
	public BasicClickable getForeward() {
		return foreward;
	}

	/**
	 * @param foreward the foreward to set
	 */
	public void setForeward(BasicClickable foreward) {
		this.foreward = foreward;
	}

	public void next() {
		this.render = getNext();
	}

	public void prev() {
		this.render = getPrev();
	}
}
