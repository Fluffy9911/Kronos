package com.kronos.io.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BasicStreamedFile implements StreamedFile {
	private File file;

	public BasicStreamedFile(String filePath) {
		this.file = new File(filePath);
	}

	@Override
	public String getFileName() {
		return file.getName();
	}

	@Override
	public String getExtension() {
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf('.');
		if (lastIndex == -1) {
			return ""; // No extension found
		}
		return fileName.substring(lastIndex + 1);
	}

	@Override
	public String getFull() {
		return file.getAbsolutePath();
	}

	@Override
	public byte[] readInput(int position, int length) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			raf.seek(position);
			byte[] buffer = new byte[length];
			int bytesRead = raf.read(buffer);
			if (bytesRead < length) {
				byte[] trimmedBuffer = new byte[bytesRead];
				System.arraycopy(buffer, 0, trimmedBuffer, 0, bytesRead);
				return trimmedBuffer;
			}
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	@Override
	public String readString(int position, int length) {
		byte[] bytes = readInput(position, length);
		return new String(bytes);
	}

	@Override
	public void write(int pos, byte[] b) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(pos);
			raf.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(int pos, String s) {
		write(pos, s.getBytes());
	}
}
