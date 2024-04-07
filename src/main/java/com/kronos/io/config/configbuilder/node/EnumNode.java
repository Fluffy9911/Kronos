package com.kronos.io.config.configbuilder.node;

import java.util.List;

import com.kronos.io.config.configbuilder.EqualsBlock;
import com.kronos.io.config.configbuilder.Node;
import com.kronos.io.config.configbuilder.PossibleValuesBlock;
import com.kronos.io.config.configbuilder.TypeBlock;

public class EnumNode extends Node {
	EqualsBlock e;
	TypeBlock tb;
	PossibleValuesBlock pvb;

	String name;
	String value;
	String[] options;

	public EnumNode(String name, String value, String[] options, String possibleValues) {
		this.name = name;
		this.value = value;
		this.options = options;
		e = new EqualsBlock(name, new String[] { value });
		tb = new TypeBlock("enum");
		pvb = new PossibleValuesBlock(name, possibleValues, options);
	}

	@Override
	public String to(String pref, String app) {
		return e.blockOut(pref, app, 0) + tb.blockOut(pref, app, 1) + pvb.blockOut(pref, app, 2);
	}

	@Override
	public void from(List<String> line, String pref, String app) {
		String s = line.get(0);
		String d = line.get(1);
		String f = line.get(2);

		e.readIn(s, pref, app);
		tb.readIn(d, pref, app);
		pvb.readIn(f, pref, app);
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

	public PossibleValuesBlock getPvb() {
		return pvb;
	}

	public void setPvb(PossibleValuesBlock pvb) {
		this.pvb = pvb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}
}
