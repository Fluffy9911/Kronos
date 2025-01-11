/**
 * 
 */
package com.kronos.io.streamedlists;

import java.io.File;
import java.util.List;

import com.kronos.io.StreamedList;

/**
 * 
 */
public interface ListedStreamedList<T> extends List<T> {
	public T readIndex(int n);

	public void append(T t);

	public void appendIndex(int n, T t);

	@Override
	public int size();

	public int getChunkSize(int n);

	public int totalSize();

	@Override
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
