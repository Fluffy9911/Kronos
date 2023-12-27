package com.kronos.graphixs.g2d.ui;

public interface Comp {

	public ComponentPosition getPosition();

	public void update();

	public void recalculatePosition();

	public void addChild(String cid, Comp c);

	public Comp getChild(String cid);

	public void updateChildren();

	public boolean canUpdateChildren();

	/**
	 * if the component responds to mouse dragging
	 * 
	 * @return
	 */
	public boolean movable();

}
