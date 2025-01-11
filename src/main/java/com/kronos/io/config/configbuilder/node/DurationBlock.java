package com.kronos.io.config.configbuilder.node;

import java.time.Duration;

import com.kronos.io.config.configbuilder.Block;

public class DurationBlock extends Block {
	Duration durationValue;

	public DurationBlock(String name, Duration durationValue) {
		super(false, name, new String[] { durationValue.toString() });
		this.durationValue = durationValue;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting duration value
		int durationStartIndex = val.indexOf("\"") + 1;
		int durationEndIndex = val.lastIndexOf("\"");
		String durationStr = val.substring(durationStartIndex, durationEndIndex);
		this.durationValue = Duration.parse(durationStr);
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + durationValue.toString() + "\";";
	}

	public Duration getDurationValue() {
		return durationValue;
	}

	public void setDurationValue(Duration durationValue) {
		this.durationValue = durationValue;
	}
}
