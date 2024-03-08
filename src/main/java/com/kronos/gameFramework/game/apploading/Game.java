/**
 * 
 */
package com.kronos.gameFramework.game.apploading;

import java.util.List;

import com.kronos.gameFramework.game.Application;
import com.kronos.graphixs.LoopAccessor;
import com.kronos.graphixs.display.ScreenConfig;

/**
 * 
 */
public abstract class Game extends Application {

	public abstract List<String> requestedTextures();

	public abstract ScreenConfig getScreen();

	public abstract void loop(LoopAccessor la);
}
