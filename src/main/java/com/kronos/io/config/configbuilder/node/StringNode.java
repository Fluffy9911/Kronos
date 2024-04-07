package com.kronos.io.config.configbuilder.node;

import java.util.List;

import com.kronos.io.config.configbuilder.EqualsBlock;
import com.kronos.io.config.configbuilder.Node;
import com.kronos.io.config.configbuilder.TypeBlock;

public class StringNode extends Node {
	EqualsBlock e;
	TypeBlock tb;

	String name;
	String[] val;

	public StringNode(String name, String[] val) {
		e = new EqualsBlock(name, val, val.length > 1); // Checking if it's an array
		tb = new TypeBlock(val.length > 1 ? "string_arr" : "string");
		this.name = name;
		this.val = val;
	}

	@Override
	public String to(String pref, String app) {
		return e.blockOut(pref, app, 0) + tb.blockOut(pref, app, 1);
	}

	@Override
	public void from(List<String> line, String pref, String app) {
		String s = line.get(0);
		String d = line.get(1);

		e.readIn(s, pref, app);
		tb.readIn(d, pref, app);
	}

	public EqualsBlock getE() {
		return e;
	}

	public void setE(EqualsBlock e) {
		this.e = e;
	}

	public TypeBlock getTb() {
		return tb;
	}

	public void setTb(TypeBlock tb) {
		this.tb = tb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getVal() {
		return val;
	}

	public void setVal(String[] val) {
		this.val = val;
	}
}
