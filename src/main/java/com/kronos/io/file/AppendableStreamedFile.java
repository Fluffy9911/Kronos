package com.kronos.io.file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AppendableStreamedFile extends BasicStreamedFile implements AppendableFile {

	public AppendableStreamedFile(String filePath) {
		super(filePath);

	}

	@Override
	public void appendString(String s) {
		byte[] bytes = s.getBytes();
		append(bytes);
	}

	@Override
	public void append(byte[] b) {
		try (RandomAccessFile raf = new RandomAccessFile(getFull(), "rw")) {
			raf.seek(raf.length());
			raf.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
