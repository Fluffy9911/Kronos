/**
 * 
 */
package com.kronos.io.config.configbuilder.node;

import java.util.List;

import com.kronos.io.config.configbuilder.EqualsBlock;
import com.kronos.io.config.configbuilder.Node;
import com.kronos.io.config.configbuilder.TypeBlock;

/**
 * 
 */
public class BoolNode extends Node {
	String name;
	boolean val;
	boolean[] vs;
	EqualsBlock eb;
	TypeBlock tb;

	public BoolNode(String name, boolean val) {
		this.name = name;
		this.val = val;
		if (vs == null) {
			eb = new EqualsBlock(name, new String[] { String.valueOf(val) });
			tb = new TypeBlock("bool");
		} else {
			String[] s = new String[vs.length];
			for (int i = 0; i < vs.length; i++) {
				s[i] = Boolean.toString(vs[i]);
			}
			eb = new EqualsBlock(name, s, true);
			tb = new TypeBlock("bool_arr");
		}
	}

	public BoolNode(String name, boolean[] val) {
		this.name = name;
		this.vs = val;
		if (vs == null) {
			eb = new EqualsBlock(name, new String[] { String.valueOf(val) });
			tb = new TypeBlock("bool");
		} else {
			String[] s = new String[vs.length];
			for (int i = 0; i < vs.length; i++) {
				s[i] = Boolean.toString(vs[i]);
			}
			eb = new EqualsBlock(name, s, true);
			tb = new TypeBlock("bool_arr");
		}
	}

	@Override
	public String to(String pref, String app) {
		if (vs == null) {
			eb = new EqualsBlock(name, new String[] { String.valueOf(val) });
			tb = new TypeBlock("bool");
		} else {
			String[] s = new String[vs.length];
			for (int i = 0; i < vs.length; i++) {
				s[i] = Boolean.toString(vs[i]);
			}
			eb = new EqualsBlock(name, s, true);
			tb = new TypeBlock("bool_arr");
		}
		return eb.blockOut(pref, app, 0) + tb.blockOut(pref, app, 1);
	}

	@Override
	public void from(List<String> line, String pref, String app) {
		eb.readIn(line.get(0), pref, app);
		tb.readIn(line.get(1), pref, app);
		this.name = eb.getData()[0];
		this.val = Boolean.valueOf(eb.getData()[1]);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the val
	 */
	public boolean isVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(boolean val) {
		this.val = val;
	}

}
