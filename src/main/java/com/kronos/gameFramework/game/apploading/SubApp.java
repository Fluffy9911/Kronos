/**
 * 
 */
package com.kronos.gameFramework.game.apploading;

import com.kronos.graphixs.g2d.TextureBatch;

/**
 * 
 */
public interface SubApp {

	void init();

	void render(TextureBatch batch);

	void begin();

	void end();

	String id();

}
