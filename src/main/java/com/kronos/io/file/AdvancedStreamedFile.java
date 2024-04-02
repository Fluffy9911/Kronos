/**
 * 
 */
package com.kronos.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * 
 */
public interface AdvancedStreamedFile extends AppendableFile {

	public File asFile();

	public RandomAccessFile asRAF();

	// all reads start at the beggining of the file
	public String readEntire();

	public void readInto(CharBuffer cb);

	public byte[] readEntireBytes();

	public void readIntoByte(byte[] bs);

	public ByteBuffer readTo();

	public Scanner getScannerStream();

	public FileInputStream readInto();

	// appends to position 0
	public void appendObject(Serializable s);

	// reads from position 0
	public Object read();

	// appends to position
	public void appendObjectPos(Serializable s, int pos);

	// reads from position
	public Object read(int pos);

}
