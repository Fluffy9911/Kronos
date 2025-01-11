/**
 * 
 */
package com.kronos.gameFramework.game.apploading;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.core.event.EngineListener;
import com.kronos.graphixs.texture.AssetLoader;
import com.kronos.io.Config;
import com.kronos.io.assets.TextureBuffer;

/**
 * 
 */
public class AppLoader {

	public static Game current;

	public static SubApp sub;

	public static Logger log;

	public static void begin(Game g) {
		current = g;
		Kronos.start(g.getScreen());
		log = Kronos.debug.getLogger();
		log.debug("Starting APPLoading");
		log.debug("Starting app at location: {}, {}", g.programLocation(), g.getClass().getName());
		preLoad();
		loadTextures();
		Kronos.registerListener(new EngineListener() {

			@Override
			public void engineStart() {
				// TODO Auto-generated method stub

			}

			@Override
			public void engineEnd() {
				current.onEnd();

			}

			@Override
			public void configChange(Config c) {
				// TODO Auto-generated method stub

			}
		});
		Kronos.startDrawing((la) -> {
			g.loop(la);
		});
	}

	public static void preLoad() {
		log.debug("begining pre-loading");
		current.init();
	}

	public static void loadTextures() {
		log.debug("beginging texture loading");

		TextureBuffer tb = new TextureBuffer();
		AssetLoader asl = AssetLoader.create();
		for (Iterator iterator = current.requestedTextures().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			URL u = null;
			try {
				u = new File(type).toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("Loading: {}", new File(type).getName().replace(".png", ""));

		}
		// tb.loadToImages();
//		if (current.requestedTextures() != null) {
//			List<String> texs = current.requestedTextures();
//			for (Iterator iterator = texs.iterator(); iterator.hasNext();) {
//				String string = (String) iterator.next();
//				try {
//
//					File f = new File(string);
//					log.debug("Loading requested texture: {}", string);
//					Kronos.graphixs.createTexture(f.getName(), string);
//				} catch (Exception e) {
//
//					log.debug("Failed Loading Texture: {}, {}", string, e);
//				}
//			}
//		}
		log.debug("textures loaded");
	}

	public static void addPluginNature(File folder) {
		Kronos.enablePlugins(folder);
	}
}
