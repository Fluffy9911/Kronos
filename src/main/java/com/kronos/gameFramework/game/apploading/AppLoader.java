/**
 * 
 */
package com.kronos.gameFramework.game.apploading;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;

/**
 * 
 */
public class AppLoader {

	public static Game current;

	public static SubApp sub;

	public static Logger log;

	public static void begin(Game g) {
		current = g;
		Kronos.beginHeadlessGL();
		log = Kronos.debug.getLogger();
		log.debug("Starting APPLoading");
		log.debug("Starting app at location: {}, {}", g.programLocation(), g.getClass().getName());
		preLoad();
		loadTextures();

	}

	public static void preLoad() {
		log.debug("begining pre-loading");
		current.init();
	}

	public static void loadTextures() {
		log.debug("beginging texture loading");
		List<String> texs = current.requestedTextures();
		for (Iterator iterator = texs.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			try {

				File f = new File(string);
				log.debug("Loading requested texture: {}", string);
				Kronos.graphixs.createTexture(f.getName(), string);
			} catch (Exception e) {

				log.debug("Failed Loading Texture: {}, {}", string, e);
			}
		}
		log.debug("textures loaded");
	}

}
