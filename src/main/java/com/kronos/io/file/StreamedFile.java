/**
 * 
 */
package com.kronos.io.file;

/**
 * 
 */
public interface StreamedFile {

	public String getFileName();

	public String getExtension();

	public String getFull();

	public byte[] readInput(int position, int length);

	public String readString(int position, int length);

	public void write(int pos, byte[] b);

	public void write(int pos, String s);

}
