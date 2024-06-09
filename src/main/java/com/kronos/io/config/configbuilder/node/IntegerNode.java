/**
 * 
 */
package com.kronos.io.config.configbuilder.node;

import java.util.List;

import com.kronos.io.config.configbuilder.EqualsBlock;
import com.kronos.io.config.configbuilder.IntegerBlock;
import com.kronos.io.config.configbuilder.Node;
import com.kronos.io.config.configbuilder.TypeBlock;

/**
 * 
 */
public class IntegerNode extends Node {
	EqualsBlock e;
	IntegerBlock ib;
	TypeBlock tb;

	String name;
	int val, min, max;
	int[] valarr;

	public IntegerNode(String name, int val, int min, int max) {
		e = new EqualsBlock(name, new String[] { Integer.toString(val) });
		ib = new IntegerBlock(name, min, max);
		tb = new TypeBlock("int");
		this.name = name;
		this.val = val;
		this.min = min;
		this.max = max;
	}

	public IntegerNode(String name, int[] val, int min, int max) {
		String[] s = new String[val.length];
		for (int i = 0; i < val.length; i++) {
			s[i] = Integer.toString(val[i]);
		}

		e = new EqualsBlock(name, s, true);
		ib = new IntegerBlock(name, min, max);
		tb = new TypeBlock("int_arr");
		this.name = name;
		this.valarr = val;
		this.min = min;
		this.max = max;
		this.val = val[0];
	}

	@Override
	public String to(String pref, String app) {
		if (!e.isIsarr()) {
			e = new EqualsBlock(name, new String[] { Integer.toString(val) });
			ib = new IntegerBlock(name, min, max);
			tb = new TypeBlock("int");
		} else {
			String[] s = new String[valarr.length];
			for (int i = 0; i < valarr.length; i++) {
				s[i] = Integer.toString(valarr[i]);
			}

			e = new EqualsBlock(name, s, true);
			ib = new IntegerBlock(name, min, max);
			tb = new TypeBlock("int_arr");
		}
		return e.blockOut(pref, app, 0) + tb.blockOut(pref, app, 1) + ib.blockOut(pref, app, 2);
	}

	@Override
	public void from(List<String> line, String pref, String app) {

		String s = line.get(0);
		String d = line.get(1);
		String f = line.get(2);

		e.readIn(s, pref, app);
		tb.readIn(d, pref, app);
		ib.readIn(f, pref, app);
	}

	/**
	 * @return the e
	 */
	public EqualsBlock getE() {
		return e;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(EqualsBlock e) {
		this.e = e;
	}

	/**
	 * @return the ib
	 */
	public IntegerBlock getIb() {
		return ib;
	}

	/**
	 * @param ib the ib to set
	 */
	public void setIb(IntegerBlock ib) {
		this.ib = ib;
	}

	/**
	 * @return the tb
	 */
	public TypeBlock getTb() {
		return tb;
	}

	/**
	 * @param tb the tb to set
	 */
	public void setTb(TypeBlock tb) {
		this.tb = tb;
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
	public int getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(int val) {
		this.val = val;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the valarr
	 */
	public int[] getValarr() {
		return valarr;
	}

	/**
	 * @param valarr the valarr to set
	 */
	public void setValarr(int[] valarr) {
		this.valarr = valarr;
	}

}
