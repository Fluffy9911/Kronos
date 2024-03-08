package com.kronos.graphixs.g2d;

import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;

<<<<<<< Updated upstream
import com.kronos.Kronos;
=======
import com.kronos.graphixs.color.Color;
>>>>>>> Stashed changes
import com.kronos.graphixs.display.ScreenConfig;

/**
 * Converts cordinates to opengl cords and does all the math
 */
public class ScreenProvider {
	public static ScreenProvider getDefault() {
		return Kronos.graphixs.g2d.getProvider();
	}

	Cam2D cam;

	ScreenConfig config;
	float ppuw = 1, du = 10, ppuh = 1;

	public ScreenProvider(ScreenConfig config) {
		super();
		this.config = config;
		cam = new Cam2D(new Vector3f(0, 0, 1), config.width(), config.height(), 0, 0);
		cam.update();
		ppuw = config.width() / du;
		ppuh = config.height() / du;

	}

	public Matrix4f collectTransform() {
		return cam.collectTransform();
	}

	public void update() {
		cam.update();
	}

	public Cam2D getCam() {
		return cam;
	}

	public ScreenConfig getConfig() {
		return config;
	}

	public int toUnitsX(int u) {

		float f = (float) Math.floor(ppuw * u);
		return (int) f;
	}

	public int toUnitsY(int u) {

		float f = (float) Math.floor(ppuh * u);
		return (int) f;
	}

	public float aspectRatio() {
		return (config.width() / config.height());
	}

	public float halfHeight() {
		return (config.height() / 2);
	}

	public float halfWidth() {
		return (config.width() / 2);
	}

	public float percentOfWidth(int i) {
		int p = Math.min(Math.max(i, 1), 100);
		float v = (p / 100);
		return config.width() * v;
	}

	public float percentOfHeight(int i) {
		int p = Math.min(Math.max(i, 1), 100);
		float v = (p / 100);
		return config.height() * v;
	}

	public float getCenter() {
		return (halfHeight() + halfWidth());
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#width()
	 */
	public int width() {
		return config.width();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#height()
	 */
	public int height() {
		return config.height();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#title()
	 */
	public String title() {
		return config.title();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#getClearColor()
	 */
	public Color getClearColor() {
		return config.getClearColor();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#updateTime()
	 */
	public int updateTime() {
		return config.updateTime();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#getHints()
	 */
	public Map<Integer, Integer> getHints() {
		return config.getHints();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#defaultHints()
	 */
	public Map<Integer, Integer> defaultHints() {
		return config.defaultHints();
	}

	/**
	 * 
	 * @see com.kronos.graphixs.display.ScreenConfig#loadValues()
	 */
	public void loadValues() {
		config.loadValues();
	}

	/**
	 * 
	 * @see com.kronos.graphixs.display.ScreenConfig#disposeValues()
	 */
	public void disposeValues() {
		config.disposeValues();
	}

	/**
	 * @return
	 * @see com.kronos.graphixs.display.ScreenConfig#getData()
	 */
	public String getData() {
		return config.getData();
	}

}
