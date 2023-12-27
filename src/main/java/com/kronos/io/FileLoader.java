package com.kronos.io;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.kronos.Kronos;

public class FileLoader {
	ResourceIdentifier rid;

	public FileLoader(ResourceIdentifier rid) {
		this.rid = rid;
	}

	public String tryLoad(String path) {
		File f = new File(rid.getBasePath() + "/" + path);
		if (!f.isFile()) {
			Kronos.debug.getLogger().error("File Path {} is null using resource identifier {} ",
					rid.getBasePath() + "/" + path, rid.getNameid());
		}

		try {
			Path p = Paths.get(f.toURI());
			byte[] bytes = Files.readAllBytes(p);
			return new String(bytes, StandardCharsets.UTF_8);

		} catch (IOException e) {
			Kronos.debug.getLogger().fatal("ERROR {} ", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public boolean tryWrite(String path, String content) {
		File file = new File(rid.getBasePath() + "/" + path);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
			return true;
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error writing to file: {}", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public BufferedImage tryLoadImage(String path) {
		File imageFile = new File(rid.getBasePath() + "/" + path);

		try {
			if (!imageFile.isFile()) {
				Kronos.debug.getLogger().error("Image file path {} is null using resource identifier {} ",
						rid.getBasePath() + "/" + path, rid.getNameid());
				return null;
			}

			return ImageIO.read(imageFile);
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error loading image: {}", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public String tryLoad(String path, ResourceIdentifier rid) {
		File f = new File(rid.getBasePath() + "/" + path);
		if (!f.isFile()) {
			Kronos.debug.getLogger().error("File Path {} is null using resource identifier {} ",
					rid.getBasePath() + "/" + path, rid.getNameid());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(rid.getBasePath() + "/" + path))) {
			String line;
			String out = "";
			while ((line = br.readLine()) != null) {
				out += line;
			}
			return out;
		} catch (IOException e) {
			Kronos.debug.getLogger().fatal("ERROR {} ", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public boolean tryWrite(String path, String content, ResourceIdentifier rid) {
		File file = new File(rid.getBasePath() + "/" + path);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
			return true;
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error writing to file: {}", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public boolean tryWriteNamed(String name, String content, ResourceIdentifier rid) {
		File file = new File(rid.getBasePath() + "/" + name);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
			return true;
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error writing to file: {}", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public boolean tryWriteNamed(String name, String content) {
		File file = new File(rid.getBasePath() + "/" + name);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
			return true;
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error writing to file: {}", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public BufferedImage tryLoadImage(String path, ResourceIdentifier rid) {
		File imageFile = new File(rid.getBasePath() + "/" + path);

		try {
			if (!imageFile.isFile()) {
				Kronos.debug.getLogger().error("Image file path {} is null using resource identifier {} ",
						rid.getBasePath() + "/" + path, rid.getNameid());
				return null;
			}

			return ImageIO.read(imageFile);
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error loading image: {}", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public void createAt(String path, String file, ResourceIdentifier rid) {
		File f = new File(rid.getBasePath() + "/" + path);
		f.mkdirs();
		f = new File(rid.getBasePath() + "/" + path + "/" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void createAt(String file, ResourceIdentifier rid) {

		File f = new File(rid.getBasePath() + "/" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {} Full Path: {}", e.getMessage(),
					rid.getBasePath() + "/" + file);
			e.printStackTrace();
		}
	}

	public void createAt(String path, String file) {
		File f = new File(rid.getBasePath() + "/" + path);
		f.mkdirs();
		f = new File(rid.getBasePath() + "/" + path + "/" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void createAt(String file) {

		File f = new File(rid.getBasePath() + "/" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void create(ResourceIdentifier kronos_out) {
		File f = new File(kronos_out.getBasePath());
		if (f.exists()) {
			Kronos.debug.getLogger().info("Path Already Exists: {} Skipping", "/" + kronos_out.getBasePath());
			return;
		}
		f.mkdirs();
		Kronos.debug.getLogger().info("Created Path at: {}", "/" + kronos_out.getBasePath());
	}

	public void createAndWrite(ResourceIdentifier rid, String file, String text) {
		create(rid);
		createAt(file, rid);
		tryWriteNamed(file, text, rid);
	}

	public String createFilePath(String path, String name) {
		return rid.getBasePath() + "/" + path + "/" + name;
	}

	public void writeConfig(Config c, String name, String path) {
		createAt(path, name);
		tryWriteNamed(name, c.writeOut());
	}

	public void writeConfig(Config c, String name, String path, ResourceIdentifier rd) {
		createAt(path, name, rd);
		tryWriteNamed(path + "/" + name, c.writeOut(), rd);
	}

	public Config tryRead(String name, String path) {
		String d = tryLoad(path + "/" + name);
		Gson g = new Gson();
		return g.fromJson(d, Config.class);
	}

	public Config tryRead(String name, String path, ResourceIdentifier rd) {

		String d = tryLoad(path + "/" + name, rd);
		Gson g = new Gson();
		return g.fromJson(d, Config.class);
	}

	public Font loadFont(File fontFile) {
		try {
			if (!fontFile.exists()) {
				System.err.println("Font file does not exist: " + fontFile.getAbsolutePath());
				return null;
			}

			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			return font;
		} catch (FontFormatException | IOException e) {
			System.err.println("Failed to load font: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

}
