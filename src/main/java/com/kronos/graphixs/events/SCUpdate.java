package com.kronos.graphixs.events;

import com.kronos.core.util.SListener;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.graphixs.g2d.Graphixs2D;

public class SCUpdate implements GraphicEvent {

	SListener sl;

	public SCUpdate(SListener sl) {
		this.sl = sl;
	}

	@Override
	public void updated(Graphixs g) {
		sl.updateSC(g.getConfig());

	}

	@Override
	public void update2D(Graphixs2D g) {

	}

}
