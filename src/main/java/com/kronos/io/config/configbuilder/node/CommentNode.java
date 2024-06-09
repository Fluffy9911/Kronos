package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class CommentNode extends Block {
	String comment;

	public CommentNode(String comment) {
		this.comment = comment;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Comments don't need to be read in
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
