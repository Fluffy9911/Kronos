/**
 * 
 */
package com.kronos.io.config.configbuilder.node;

import java.util.List;

import com.kronos.io.config.configbuilder.EqualsBlock;
import com.kronos.io.config.configbuilder.FloatBlock;
import com.kronos.io.config.configbuilder.Node;
import com.kronos.io.config.configbuilder.TypeBlock;

/**
 * 
 */
public class FloatNode extends Node {
	EqualsBlock e;
	FloatBlock ib;
	TypeBlock tb;

	String name;
	float val, min, max;
	float[] valarr;

	public FloatNode(String name, float val, float min, float max) {
		e = new EqualsBlock(name, new String[] { Float.toString(val) });
		ib = new FloatBlock(name, min, max);
		tb = new TypeBlock("float");
		this.name = name;
		this.val = val;
		this.min = min;
		this.max = max;
	}

	public FloatNode(String name, float[] val, float min, float max) {
		String[] s = new String[val.length];
		for (int i = 0; i < val.length; i++) {
			s[i] = Float.toString(val[i]);
		}

		e = new EqualsBlock(name, s, true);
		ib = new FloatBlock(name, min, max);
		tb = new TypeBlock("float_arr");
		this.name = name;
		this.valarr = val;
		this.min = min;
		this.max = max;
		this.val = val[0];
	}

	@Override
	public String to(String pref, String app) {
		if (!e.isIsarr()) {
			e = new EqualsBlock(name, new String[] { Float.toString(val) });
			ib = new FloatBlock(name, min, max);
			tb = new TypeBlock("float");
		} else {
			String[] s = new String[valarr.length];
			for (int i = 0; i < valarr.length; i++) {
				s[i] = Float.toString(valarr[i]);
			}

			e = new EqualsBlock(name, s, true);
			ib = new FloatBlock(name, min, max);
			tb = new TypeBlock("float_arr");
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
	public FloatBlock getIb() {
		return ib;
	}

	/**
	 * @param ib the ib to set
	 */
	public void setIb(FloatBlock ib) {
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
	public float getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(float val) {
		this.val = val;
	}

	/**
	 * @return the min
	 */
	public float getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(float min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public float getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(float max) {
		this.max = max;
	}

	/**
	 * @return the valarr
	 */
	public float[] getValarr() {
		return valarr;
	}

	/**
	 * @param valarr the valarr to set
	 */
	public void setValarr(float[] valarr) {
		this.valarr = valarr;
	}

}
