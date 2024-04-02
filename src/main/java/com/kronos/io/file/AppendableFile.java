/**
 * 
 */
package com.kronos.io.file;

/**
 * 
 */
public interface AppendableFile extends StreamedFile {

	public void appendString(String s);

	public void append(byte[] b);

}
