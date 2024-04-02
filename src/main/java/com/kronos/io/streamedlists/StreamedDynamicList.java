/**
 * 
 */
package com.kronos.io.streamedlists;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.kronos.io.StreamedList;

/**
 * 
 */
public class StreamedDynamicList<T> implements StreamedList<T> {
	RandomAccessFile raf;

	File loc;
	private int cacheSize = 10;
	private int index = 0, chunksize = 1000000;

	public StreamedDynamicList(File loc) {
		this.loc = loc;
		try {
			raf = new RandomAccessFile(loc, "rws");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<T> cache = new ArrayList<T>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(T e) {
			boolean added = super.add(e);
			if (size() > cacheSize) {
				removeEldest();
			}
			return added;
		}

		private void removeEldest() {
			remove(0); // Remove the eldest entry (the first one in the list)
		}
	};

	@Override
	public T readIndex(int n) {
//		if (cache.contains(loc)) {
//			System.out.println("found in cache");
//			return cache.get(n);
//		}
		if ((n * chunksize) < 0) {
			return null;
		}
		try {
			raf.seek(0);
			raf.seek(n * chunksize);
			byte[] b = new byte[chunksize];
			raf.readFully(b);
			return readFromBytes(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void append(T t) {
		if (index == 0) {
			appendIndex(0, t);
			index++;
			return;
		}
		appendIndex(index + 1, t); // TODO Auto-generated method stub
		index++;
	}

	@Override
	public void appendIndex(int n, T t) {
		try {
			if ((n * chunksize) < 0) {
				return;
			}
			raf.seek(n * chunksize);
			raf.write(toBytes(t, chunksize));
			// cache.put(n, t);
			if (index < n) {
				index = n;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public int getChunkSize(int n) {
		// TODO Auto-generated method stub
		return chunksize;
	}

	@Override
	public int totalSize() {
		// TODO Auto-generated method stub
		return index * chunksize;
	}

	@Override
	public void clear() {
		index = 0;

	}

	@Override
	public StreamedList<T> copy(File loc) {
		try {
			File newFile = new File(loc.getPath());
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			try (RandomAccessFile newRaf = new RandomAccessFile(newFile, "rwa")) {
				byte[] buffer = new byte[chunksize];
				int bytesRead;
				raf.seek(0);
				while ((bytesRead = raf.read(buffer)) != -1) {
					newRaf.write(buffer, 0, bytesRead);
				}
			}
			StreamedDynamicList<T> copy = new StreamedDynamicList<>(loc);
			copy.loc = newFile;
			copy.chunksize = chunksize;
			copy.index = index;
			return copy;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StreamedList<T> copyTo(StreamedList<T> dest) {
		if (dest instanceof StreamedDynamicList) {
			StreamedDynamicList<T> destination = (StreamedDynamicList<T>) dest;
			try {
				byte[] buffer = new byte[chunksize];
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
					int bytesRead = raf.read(buffer);
					destination.raf.write(buffer, 0, bytesRead);
				}
				destination.index = this.index;
				return destination;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<T> readToMemory() {
		List<T> list = new ArrayList<>();
		try {
			raf.seek(0);
			while (raf.getFilePointer() < raf.length()) {
				byte[] buffer = new byte[chunksize];
				int bytesRead = raf.read(buffer);
				T obj = readFromBytes(buffer);
				list.add(obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void writeFromMemory(List<T> list) {
		try {
			clear();
			for (T item : list) {
				append(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeTo(T[] t) {
		try {
			clear();
			for (T item : t) {
				append(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> getCached() {
		return cache;
	}

	@Override
	public void setCacheSize(int size) {
		this.cacheSize = size;
	}

	@Override
	public int getCacheSize() {
		// TODO Auto-generated method stub
		return cacheSize;
	}

	@Override
	public void clearCache() {
		this.cache.clear();

	}

	@Override
	public void readContentsToCache() {
		cache.clear();
		try {
			raf.seek(0);
			for (int i = 0; i <= index; i++) {
				raf.seek(i * chunksize);
				byte[] buffer = new byte[chunksize];
				int bytesRead = raf.read(buffer);
				T obj = readFromBytes(buffer);
				// cache.put(i, obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeContentsFromCache() {
		try {
			raf.setLength(0); // Clear the file before writing
//			for (Map.Entry<Integer, T> entry : cache.entrySet()) {
//				appendIndex(entry.getKey(), entry.getValue());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts the object to a byte array of specified chunk size. The returned
	 * array has to be the chunk size even if it's all 0s.
	 * 
	 * @param t     The object to convert.
	 * @param chunk The desired chunk size.
	 * @return The byte array representing the object.
	 */
	private byte[] toBytes(T t, int chunk) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(bos));
			oos.writeObject(t);
			oos.flush();
			byte[] objBytes = bos.toByteArray();
			if (objBytes.length < chunk) {
				byte[] paddedBytes = new byte[chunk];
				System.arraycopy(objBytes, 0, paddedBytes, 0, objBytes.length);
				return paddedBytes;
			}
			return objBytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private T readFromBytes(byte[] t) {
		ByteArrayInputStream bos = new ByteArrayInputStream(t);
		try {
			ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(bos));
			return (T) oos.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setChunkSize(int n) {
		this.chunksize = n;

	}
}
