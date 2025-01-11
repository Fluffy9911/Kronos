package com.kronos.io;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.kronos.Kronos;

public class FileLoader {
	ResourceIdentifier rid;

	public FileLoader(ResourceIdentifier rid) {
		this.rid = rid;
	}

	/***
	 * @return
	 * 
	 *         the rid
	 */

	public ResourceIdentifier getRid() {
		return rid;
	}

	/***
	 * 
	 * @param rid the rid to set
	 */

	public void setRid(ResourceIdentifier rid) {
		this.rid = rid;
	}

	public String tryLoad(String path) {
		URL f = FileLoader.class.getClassLoader().getResource(rid.getBasePath() + "\\" + path);

		if (f == null) {
			Kronos.debug.getLogger().error("File Path {} is null using resource identifier {} ",
					rid.getBasePath() + "\\" + path, rid.getNameid());
		}

		try {
			if (f != null) {
				Path p = Paths.get(f.toURI());
				byte[] bytes = Files.readAllBytes(p);
				return new String(bytes, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			Kronos.debug.getLogger().fatal("ERROR {} ", e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean tryWrite(String path, String content) {
		File file = new File(rid.getBasePath() + "\\" + path);

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
		URL imageFile = FileLoader.class.getClassLoader().getResource(path);

		try {
			if (imageFile == null) {
				Kronos.debug.getLogger().error("Image file path {} is null using resource identifier {} ",
						rid.getBasePath() + "\\" + path, rid.getNameid());
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
		File f = new File(rid.getBasePath() + "\\" + path);
		if (!f.isFile()) {
			Kronos.debug.getLogger().error("File Path {} is null using resource identifier {} ",
					rid.getBasePath() + "\\" + path, rid.getNameid());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(rid.getBasePath() + "\\" + path))) {
			String line;
			String out = "";
			while ((line = br.readLine()) != null) {
				out += line;
			}
			return out;

		} catch (FileNotFoundException fe) {
			Kronos.debug.getLogger().debug(
					"File Loading caught a soft error, sending error in-case this is not a false alarm. Attempting fix",
					fe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean tryWrite(String path, String content, ResourceIdentifier rid) {
		File file = new File(rid.getBasePath() + "\\" + path);

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
		File file = new File(rid.getBasePath() + "\\" + name);

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
		File file = new File(rid.getBasePath() + "\\" + name);

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
		File imageFile = new File(rid.getBasePath() + "\\" + path);

		try {
			if (!imageFile.isFile()) {
				Kronos.debug.getLogger().error("Image file path {} is null using resource identifier {} ",
						rid.getBasePath() + "\\" + path, rid.getNameid());
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
		File f = new File(rid.getBasePath() + "\\" + path);
		f.mkdirs();
		f = new File(rid.getBasePath() + "\\" + path + "\\" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void createAt(String file, ResourceIdentifier rid) {
		File fd = new File(rid.getBasePath());
		fd.mkdirs();
		File f = new File(rid.getBasePath() + "\\" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {} Full Path: {}", e.getMessage(),
					rid.getBasePath() + "\\" + file);
			e.printStackTrace();
		}
	}

	public void createAt(String path, String file) {
		File f = new File(rid.getBasePath() + "\\" + path);
		f.mkdirs();
		f = new File(rid.getBasePath() + "\\" + path + "\\" + file);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Kronos.debug.getLogger().error("Error creating file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void createAt(String file) {

		File f = new File(rid.getBasePath() + "\\" + file);
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
			Kronos.debug.getLogger().info("Path Already Exists: {} Skipping", "\\" + kronos_out.getBasePath());
			return;
		}
		f.mkdirs();
		Kronos.debug.getLogger().info("Created Path at: {}", "\\" + kronos_out.getBasePath());
	}

	public void createAndWrite(ResourceIdentifier rid, String file, String text) {
		create(rid);
		createAt(file, rid);
		tryWriteNamed(file, text, rid);
	}

	public String createFilePath(String path, String name) {
		return rid.getBasePath() + "\\" + path + "\\" + name;
	}

	public void writeConfig(Config c, String name, String path) {
		Kronos.debug.getLogger().debug("Path: {} \n {}", path + "\\" + name, c.writeOut());
		createAt(path, name);
		tryWriteNamed(name, c.writeOut());
	}

	public void writeConfig(Config c, String name, String path, ResourceIdentifier rd) {
		Kronos.debug.getLogger().debug("Writing config: {}", name);
		createAt(path, name, rd);
		tryWriteNamed(path + "\\" + name, c.writeOut(), rd);
	}

	public Config tryRead(String name, String path) {
		Kronos.debug.getLogger().debug("Reading config: {}", name);
		String d = tryLoad(path + "\\" + name);
		Gson g = new Gson();
		return g.fromJson(d, Config.class);
	}

	public Config getOrCreate(String name, String path) {
		Config c = null;
		try {
			c = tryRead(name, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c == null) {
			System.out.println("created");
			createAt(path, name + ".json");
			c = new Config();
		}
		return c;
	}

	public Config tryRead(String name, String path, ResourceIdentifier rd) {

		String d = "";
		Gson g = new Gson();
		try {
			d = tryLoad(path + "\\" + name, rd);
			if (d == null) {
				d = "";
			}

		} catch (Exception e) {
			Kronos.debug.getLogger().debug(
					"File Loading caught a soft error, sending error in-case this is not a false alarm. Attempting fix",
					e);
		}
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

	public static byte[] compressString(String input) throws IOException {
		byte[] data = input.getBytes(StandardCharsets.UTF_8);

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		deflater.end();
		outputStream.close();

		return outputStream.toByteArray();
	}

	public static String decompressString(byte[] compressedData) throws IOException, DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(compressedData);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);

		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		inflater.end();
		outputStream.close();

		return outputStream.toString(StandardCharsets.UTF_8.name());
	}
}
