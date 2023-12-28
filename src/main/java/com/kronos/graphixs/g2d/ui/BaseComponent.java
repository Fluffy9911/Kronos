package com.kronos.graphixs.g2d.ui;

import java.util.HashMap;
import java.util.Map;

public class BaseComponent implements Comp {
	protected BasePosition bp;
	protected HashMap<String, BaseComponent> children;
	boolean cdren, moveable;
	protected boolean hidden;
	protected States state;
	protected ComponentHandler ch;

	public BaseComponent(BasePosition bp, boolean cdren, boolean moveable, boolean hidden) {
		super();
		this.bp = bp;
		this.cdren = cdren;
		this.moveable = moveable;
		this.hidden = hidden;
		state = new States();
	}

	@Override
	public ComponentPosition getPosition() {
		// TODO Auto-generated method stub
		return bp;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recalculatePosition() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChild(String cid, Comp c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Comp getChild(String cid) {
		// TODO Auto-generated method stub
		return children.get(cid);
	}

	@Override
	public void updateChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canUpdateChildren() {
		// TODO Auto-generated method stub
		return cdren;
	}

	@Override
	public boolean movable() {
		// TODO Auto-generated method stub
		return moveable;
	}

	@Override
	public boolean hidden() {
		// TODO Auto-generated method stub
		return hidden;
	}

	/**
	 * @param i
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#hasState(int)
	 */
	public boolean hasState(int i) {
		return state.hasState(i);
	}

	/**
	 * @param e
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#add(int)
	 */
	public boolean add(int e) {
		return state.add(e);
	}

	/**
	 * @param i
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#remove(int)
	 */
	public boolean remove(int i) {
		return state.remove(i);
	}

	public void move(float f, float g, float h, float i) {
		for (Map.Entry<String, BaseComponent> entry : children.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.getPosition().pos().translate(f, g);
			val.getPosition().pos().translateSize(h, i);
		}

	}

	@Override
	public void onShown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHidden() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeletion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreation(ComponentHandler ch) {
		this.ch = ch;

	}

}
