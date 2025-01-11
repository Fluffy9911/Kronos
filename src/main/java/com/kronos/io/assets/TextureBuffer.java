/**
 * 
 */
package com.kronos.io.assets;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kronos.Kronos;
import com.kronos.graphixs.texture.LoadTask;
import com.kronos.graphixs.texture.TexData;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureTask;

/**
 * 
 */
public class TextureBuffer {
	ConcurrentHashMap<String, TexData> buffer;
	ConcurrentHashMap<String, URL> ibuf;
	ConcurrentHashMap<String, BufferedImage> img;
	int currentimg = 0;
	int currentt = 0;

	public TextureBuffer() {
		super();
		buffer = new ConcurrentHashMap<>();
		ibuf = new ConcurrentHashMap<String, URL>();
		img = new ConcurrentHashMap<>();
	}

	public synchronized void addData(String s, TexData d) {
		buffer.put(s, d);
		currentt--;
	}

	public synchronized void addIData(String s, URL d) {
		ibuf.put(s, d);
		currentimg++;
	}

	public synchronized void addImage(String s, BufferedImage d) {
		img.put(s, d);
		currentimg--;
		currentt++;
	}

	public void loadToImages() {
		ExecutorService e = Executors.newCachedThreadPool();
		ArrayList<LoadTask> tsk = new ArrayList<>();
		for (Map.Entry<String, URL> entry : ibuf.entrySet()) {
			String key = entry.getKey();
			URL val = entry.getValue();
			tsk.add(new LoadTask(this, key, val));
		}
		for (Iterator iterator = tsk.iterator(); iterator.hasNext();) {
			LoadTask loadTask = (LoadTask) iterator.next();
			e.submit(loadTask);
		}

		e.shutdown();

		try {
			e.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loadToTextures();
	}

	public void loadToTextures() {
		ExecutorService e = Executors.newCachedThreadPool();
		ArrayList<TextureTask> tsk = new ArrayList<>();
		for (Map.Entry<String, BufferedImage> entry : img.entrySet()) {
			String key = entry.getKey();
			BufferedImage val = entry.getValue();
			tsk.add(new TextureTask(this, val, key));
		}
		for (Iterator iterator = tsk.iterator(); iterator.hasNext();) {
			TextureTask loadTask = (TextureTask) iterator.next();
			e.submit(loadTask);
		}
		e.shutdown();

		try {
			e.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("loaded");
		for (Map.Entry<String, TexData> entry : buffer.entrySet()) {
			String key = entry.getKey();
			TexData val = entry.getValue();
			Kronos.graphixs.put(key, new Texture(val.width(), val.height(), val.buf()));
		}
	}
}
