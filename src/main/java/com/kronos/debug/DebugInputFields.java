package com.kronos.debug;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class DebugInputFields extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<EValInt> vals;
	JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public DebugInputFields() {
		vals = new ArrayList<EValInt>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane = new JScrollPane();

		contentPane.add(scrollPane);
	}

	/**
	 * @param scrollPane
	 */
	public void build(JScrollPane scrollPane) {
		for (Iterator iterator = vals.iterator(); iterator.hasNext();) {
			EValInt eValInt = (EValInt) iterator.next();
			eValInt.append(scrollPane);
		}
	}

	public void add(String name, int val, int in) {
		vals.add(new EValInt(name, val, in));
	}

	@Override
	public void show() {
		build(scrollPane);
		this.setVisible(true);
	}
}
