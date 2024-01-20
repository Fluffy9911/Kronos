package com.kronos.dynamo.simulation.simulator;

import com.kronos.core.util.TimeValue;

public interface Simulator {

	public TimeValue stepTime();

	public void step();

	public void pause();

}
