/**
 * 
 */
package com.kronos.io;

import java.io.File;
import java.util.List;

/**
 * Uses a RandomAccessFile to store the actual data in the list in storage
 * rather than memory. caches x number of elements for faster access. Chunks are
 * the size of the block size at each element. we dont want to maybe store a GB+
 * in a single line. Another pro is that with massive lists we keep it in
 * storage without having to worry about the jvm doing stuff to our lists.
 * 
 * NOT FOR FAST SMALL LISTS.
 * 
 */
public interface StreamedList<T> {

	public T readIndex(int n);

	public void append(T t);

	public void appendIndex(int n, T t);

	public int size();

	public int getChunkSize(int n);

	public void setChunkSize(int n);

	public int totalSize();

	public void clear();

	public StreamedList<T> copy(File loc);

	public StreamedList<T> copyTo(StreamedList<T> dest);

	public List<T> readToMemory();

	public void writeFromMemory(List<T> list);

	public void writeTo(T[] t);

	public List<T> getCached();

	public void setCacheSize(int size);

	public int getCacheSize();

	public void clearCache();

	public void readContentsToCache();

	public void writeContentsFromCache();
}
