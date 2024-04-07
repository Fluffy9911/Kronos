package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class SectionHeaderNode extends Block {
	String header;

	public SectionHeaderNode(String header) {
		this.header = header;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Section headers don't need to be read in
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + "Section*[" + header + "];";
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
