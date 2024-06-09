/**
 * 
 */
package com.kronos.graphixs.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.io.assets.TextureBuffer;

/**
 * 
 */
public class LoadTask implements Runnable {
	TextureBuffer tb;
	String s;
	URL u;

	public LoadTask(TextureBuffer tb, String s, URL u) {
		super();
		this.tb = tb;
		this.s = s;
		this.u = u;
	}

	Logger l = Kronos.debug.getLogger();

	@Override
	public void run() {

		try {
			System.out.println(u.getPath());
			BufferedImage i = ImageIO.read(u);
			tb.addImage(s, i);
			l.debug("Loading Finished for image: {} at: {}", s, u.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
