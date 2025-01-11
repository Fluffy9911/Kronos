/**
 * 
 */
package com.kronos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 
 */
public class FileVerification {
	List<String> files;
	PrintWriter writer;

	public FileVerification(List<String> files) {
		this.files = files;
		try {
			new File("kronos/verification").mkdirs();
			new File("kronos/verification/verify.txt").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer = new PrintWriter(new File("kronos/verification/verify.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verify() {
		int verified = 0;
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			File f = new File(string);
			if (f.exists()) {
				verified++;
				writer.println("Verified File[" + string + "]");
			} else {
				writer.println("File Not Found[" + string + "]");
			}
		}
		if (verified != files.size()) {
			JOptionPane.showMessageDialog(null, "Unable To Verify All files... ");

		} else {
			System.out.println("all verified");
		}
		writer.close();
	}

}
