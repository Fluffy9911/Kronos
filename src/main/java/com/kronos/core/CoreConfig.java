package com.kronos.core;

import org.apache.logging.log4j.Logger;

import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.io.FileLoader;
import com.kronos.io.assets.InternalAssetLoader;

public class CoreConfig {
	Graphixs g;
	Graphixs2D g2;
	FileLoader loader;
	InternalAssetLoader asset;
	Logger l;
	ScreenConfig current;

	public CoreConfig(Graphixs g, Graphixs2D g2, FileLoader loader, InternalAssetLoader asset, Logger l) {
		this.g = g;
		this.g2 = g2;
		this.loader = loader;
		this.asset = asset;
		this.l = l;
	}

	/**
	 * @return the g
	 */
	public Graphixs getG() {
		return g;
	}

	/**
	 * @return the g2
	 */
	public Graphixs2D getG2() {
		return g2;
	}

	/**
	 * @return the loader
	 */
	public FileLoader getLoader() {
		return loader;
	}

	/**
	 * @return the asset
	 */
	public InternalAssetLoader getAsset() {
		return asset;
	}

	/**
	 * @return the l
	 */
	public Logger getL() {
		return l;
	}

	/**
	 * @return the current
	 */
	public ScreenConfig getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(ScreenConfig current) {
		this.current = current;
	}

}
