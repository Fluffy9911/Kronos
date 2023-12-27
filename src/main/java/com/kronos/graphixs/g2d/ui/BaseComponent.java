package com.kronos.graphixs.g2d.ui;

import java.util.HashMap;

public class BaseComponent implements Comp {
	BasePosition bp;
	HashMap<String, BaseComponent> children;
	boolean cdren, moveable;

	public BaseComponent(BasePosition bp, boolean cdren, boolean moveable) {
		super();
		this.bp = bp;
		this.cdren = cdren;
		this.moveable = moveable;
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

}
