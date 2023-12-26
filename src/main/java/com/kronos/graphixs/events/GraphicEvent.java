package com.kronos.graphixs.events;

import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.g2d.Graphixs2D;

public interface GraphicEvent {

	public void updated(Graphixs g);

	public void update2D(Graphixs2D g);

}
