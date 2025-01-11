package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class NewLineNode extends Block {
	int count;

	public NewLineNode(int count) {
		this.count = count;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// NewLineNode doesn't need to be read in
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			builder.append("\n");
		}
		return builder.toString();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
