package com.kronos.core.event;

import com.kronos.io.Config;

public interface EngineListener {

	public void configChange(Config c);
	
	public void engineStart();
	
	public void engineEnd();
	
}
