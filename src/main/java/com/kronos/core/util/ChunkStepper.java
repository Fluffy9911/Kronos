package com.kronos.core.util;

import java.util.List;

public class ChunkStepper<T> {
	private final List<T> masterList;
	private final int chunkSize;
	private int currentIndex;

	public ChunkStepper(List<T> masterList, int chunkSize) {
		if (chunkSize <= 0) {
			throw new IllegalArgumentException("Chunk size must be greater than zero.");
		}
		this.masterList = masterList;
		this.chunkSize = chunkSize;
		this.currentIndex = 0;
	}

	public boolean hasNextChunk() {
		return currentIndex < masterList.size();
	}

	public List<T> getNextChunk() {
		if (!hasNextChunk()) {
			throw new IllegalStateException("No more chunks available.");
		}

		int endIndex = Math.min(currentIndex + chunkSize, masterList.size());
		List<T> chunk = masterList.subList(currentIndex, endIndex);
		currentIndex += chunkSize;
		return chunk;
	}

	public boolean hasRightAmount(int desiredAmount) {
		return currentIndex + desiredAmount <= masterList.size();
	}

	public List<T> getRightAmount(int desiredAmount) {
		if (!hasRightAmount(desiredAmount)) {
			throw new IllegalStateException("Not enough elements remaining in the master list.");
		}

		List<T> chunk = masterList.subList(currentIndex, currentIndex + desiredAmount);
		currentIndex += desiredAmount;
		return chunk;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

}
