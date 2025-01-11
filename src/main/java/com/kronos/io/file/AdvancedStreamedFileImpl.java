package com.kronos.io.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Scanner;

public class AdvancedStreamedFileImpl extends AppendableStreamedFile implements AdvancedStreamedFile {

	public AdvancedStreamedFileImpl(String filePath) {
		super(filePath);
	}

	@Override
	public File asFile() {
		return new File(getFull());
	}

	@Override
	public RandomAccessFile asRAF() {
		try {
			return new RandomAccessFile(getFull(), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String readEntire() {
		try (FileReader fileReader = new FileReader(asFile());
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void readInto(CharBuffer cb) {
		try (FileReader fileReader = new FileReader(asFile())) {
			fileReader.read(cb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] readEntireBytes() {
		try {
			FileInputStream fis = new FileInputStream(asFile());
			byte[] data = new byte[(int) asFile().length()];
			fis.read(data);
			fis.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	@Override
	public void readIntoByte(byte[] bs) {
		try (FileInputStream fis = new FileInputStream(asFile())) {
			fis.read(bs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ByteBuffer readTo() {
		try (RandomAccessFile raf = new RandomAccessFile(asFile(), "r")) {
			byte[] data = new byte[(int) raf.length()];
			raf.read(data);
			return ByteBuffer.wrap(data);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Scanner getScannerStream() {
		try {
			return new Scanner(asFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public FileInputStream readInto() {
		try {
			return new FileInputStream(asFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void appendObject(Serializable s) {
		try (FileOutputStream fos = new FileOutputStream(asFile(), true);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object read() {
		try (FileInputStream fis = new FileInputStream(asFile()); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void appendObjectPos(Serializable s, int pos) {
		try (RandomAccessFile raf = new RandomAccessFile(asFile(), "rw")) {
			raf.seek(pos);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(s);
			byte[] objectBytes = baos.toByteArray();
			raf.write(objectBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object read(int pos) {
		try (RandomAccessFile raf = new RandomAccessFile(asFile(), "r")) {
			raf.seek(pos);
			byte[] objectBytes = new byte[(int) (raf.length() - pos)];
			raf.readFully(objectBytes);
			ByteArrayInputStream bais = new ByteArrayInputStream(objectBytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
