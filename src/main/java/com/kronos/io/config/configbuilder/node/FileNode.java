package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class FileNode extends Block {
	String fileLocation;

	public FileNode(String name, String fileLocation) {
		super(false, name, new String[] { fileLocation });
		this.fileLocation = fileLocation;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting file location
		int fileStartIndex = val.indexOf("\"") + 1;
		int fileEndIndex = val.lastIndexOf("\"");
		this.fileLocation = val.substring(fileStartIndex, fileEndIndex);
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + fileLocation + "\";type=file;";
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
