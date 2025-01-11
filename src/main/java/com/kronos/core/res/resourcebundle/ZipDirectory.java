package com.kronos.core.res.resourcebundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {

	public static void zipDirectory(File dir, String zipDirName) throws IOException {
		FileOutputStream fos = new FileOutputStream(zipDirName);
		ZipOutputStream zos = new ZipOutputStream(fos);

		zipFile(dir, dir.getName(), zos);
		zos.close();
		fos.close();
	}

	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zos.putNextEntry(new ZipEntry(fileName));
				zos.closeEntry();
			} else {
				zos.putNextEntry(new ZipEntry(fileName + "/"));
				zos.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zos);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		fis.close();
	}

	public static void main(String[] args) {
		String sourceDirectory = "C:\\Users\\James.M\\OneDrive\\Desktop\\buildtest\\out";
		String outputFile = "kronos/res/test.zip";

		try {
			zipDirectory(new File(sourceDirectory), outputFile);
			System.out.println("Directory successfully zipped to " + outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
