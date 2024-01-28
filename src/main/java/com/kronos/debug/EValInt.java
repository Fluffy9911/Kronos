package com.kronos.debug;

import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.text.NumberFormatter;

public class EValInt {
	JButton fw, bw;
	JFormattedTextField num;
	String id;
	int val, inc, saved;

	public EValInt(String id, int val, int inc) {
		this.id = id;
		this.val = val;
		this.inc = inc;
		fw = new JButton();
		fw.setToolTipText("Increments By: " + inc);
		bw = new JButton();
		bw.setToolTipText("Decrements By: " + inc);
		saved = val;
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);
		num = new JFormattedTextField(formatter);
//		num.add(fw);
//		num.add(bw);
		num.show();
	}

	public void append(JScrollPane jsp) {
		jsp.add(num);

	}

}
