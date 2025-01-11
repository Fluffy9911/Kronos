package com.kronos.io.streamedlists;

import java.io.File;

public class StreamDynamicList2D<T> {
	private StreamedDynamicList<T[]> storage;
	private int sizeX;
	private int sizeY;

	public StreamDynamicList2D(File loc, int chunkSizeX, int chunkSizeY, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		// Calculate chunk size based on chunkSizeX and sizeY
		int chunkSize = chunkSizeX * sizeY * calculateElementSize();
		storage = new StreamedDynamicList<>(loc);
		storage.setChunkSize(chunkSize);
	}

	public T readPosition(int x, int y) {
		if (x >= sizeX || y >= sizeY || x < 0 || y < 0) {
			throw new IllegalArgumentException("Invalid position");
		}
		int index = y * sizeX + x;
		T[] chunk = storage.readIndex(index);
		if (chunk == null) {
			return null;
		}
		return chunk[x % chunk.length];
	}

	public void writePosition(int x, int y, T value) {
		if (x >= sizeX || y >= sizeY || x < 0 || y < 0) {
			throw new IllegalArgumentException("Invalid position");
		}
		int index = y * sizeX + x;
		T[] chunk = storage.readIndex(index);
		if (chunk == null) {
			chunk = createEmptyChunk();
		}
		chunk[x % chunk.length] = value;
		storage.appendIndex(index, chunk);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	// Calculate the size of each element in bytes
	private int calculateElementSize() {
		// Assuming each element is of type T
		// You may need to adjust this calculation based on your actual data types
		return estimateObjectSize(new Object());
	}

	// Estimate the size of an object in bytes
	private int estimateObjectSize(Object obj) {
		return 8; // Assuming a rough estimate of 8 bytes per object
		// You may use a more accurate method for estimating object size
	}

	// Create an empty chunk of type T[]
	private T[] createEmptyChunk() {
		// Create a new array of size equal to chunkSizeX
		// You may need to adjust this based on your requirements
		return (T[]) new Object[sizeX];
	}

	// Additional methods can be added as needed

}
