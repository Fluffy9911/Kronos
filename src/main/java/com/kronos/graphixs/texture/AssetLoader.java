package com.kronos.graphixs.texture;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.kronos.Kronos;

public class AssetLoader {

	private Map<String, String> preLoaded;
	private Map<String, URL> loadable = new HashMap<>();
	private String basePath = "";
	private Map<String, BufferedImage> loaded;
	private boolean isloaded = false;

	public AssetLoader() {

		this.preLoaded = new HashMap<>();
		this.loaded = new HashMap<>();

	}

	public void addBasePath(String path) {
		this.basePath = path;
	}

	public void loadAsset() {
//		ArrayList<String> nm = new ArrayList<String>();
//		for (Map.Entry<String, URL> entry : loadable.entrySet()) {
//			nm.add(entry.getValue().getFile());
//		}
//		new FileVerification(nm).verify();
		System.out.println(loadable.size() + " Assets to load!");
		for (Map.Entry<String, URL> entry : loadable.entrySet()) {
			BufferedImage img = new BufferedImage(16, 16, BufferedImage.OPAQUE);
			try {
				img = ImageIO.read(entry.getValue());
				System.out.println("Loaded image: " + entry.getKey());
				Kronos.graphixs.textures.put(entry.getKey(), new Texture(img));
				loaded.put(entry.getKey(), img);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		isloaded = true;
	}

	public void add(String name, String sub) {

		preLoaded.put(name, sub);

	}

	public BufferedImage getTexture(String key) {
		if (!isloaded) {

			return null;
		}
		if (loaded.get(key) == null) {

			return null;
		}
		return loaded.get(key);
	}

	public boolean validatePath(String name, String sub) {

		if (this.getFileLocation(name, sub) != null) {
			return true;
		}
		return false;
	}

	public URL getFileLocation(String name, String sub) {
		System.out.println("/" + basePath + "/" + sub + "/" + name + ".png");
		return this.getClass().getResource("/" + basePath + "/" + sub + "/" + name + ".png");
	}

	public URL getFileLocation(String file) {
		System.out.println(file);
		return this.getClass().getResource(file);
	}

	public String readAll(String file) {
		System.out.println(
				"RSC: " + (this.getClass().getResourceAsStream(file) != null) + " File:" + new File(file).exists());

		if (this.getClass().getResourceAsStream(file) != null) {
			try (InputStream inputStream = this.getClass().getResourceAsStream(file)) {
				// Buffer to store read data dynamically
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];
				int bytesRead;

				// Read from the input stream into the buffer
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					// Write the read data into the dynamic buffer
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}

				// Access the complete data as a byte array
				byte[] resultBytes = byteArrayOutputStream.toByteArray();

				// Process the complete data (here, simply printing to console)
				System.out.println(new String(resultBytes));
				return new String(resultBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try (InputStream inputStream = new FileInputStream(new File(file))) {
				// Buffer to store read data dynamically
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];
				int bytesRead;

				// Read from the input stream into the buffer
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					// Write the read data into the dynamic buffer
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}

				// Access the complete data as a byte array
				byte[] resultBytes = byteArrayOutputStream.toByteArray();

				// Process the complete data (here, simply printing to console)
				// System.out.println(new String(resultBytes));
				return new String(resultBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}

	public void searchForAssets() {
		System.out.println("Starting asset search at: " + System.currentTimeMillis());
		for (Map.Entry<String, String> entry : preLoaded.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();

			if (this.getFileLocation(key, val) != null) {
				System.out.println(this.getFileLocation(key, val).toString());
				loadable.put(key, this.getFileLocation(key, val));
			}
		}
		System.out.println(loadable.size());
	}

	public Map<String, BufferedImage> getLoaded() {
		return loaded;
	}

	public static AssetLoader create() {
		return new AssetLoader();
	}
}