package com.kronos.core.util;

import com.kronos.Kronos;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.events.SCUpdate;

public interface SListener {

	public void updateSC(ScreenConfig sc);

	public default void registerEvent() {
		Kronos.graphixs.registerEvent("screen_listener", new SCUpdate(this));
	}

}
