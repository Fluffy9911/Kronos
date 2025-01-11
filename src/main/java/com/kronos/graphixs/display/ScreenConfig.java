package com.kronos.graphixs.display;

import java.util.Map;

import org.lwjgl.glfw.GLFW;

import com.kronos.graphixs.color.Color;

public interface ScreenConfig {

	public int width();

	public int height();

	public String title();

	public Color getClearColor();

	public int updateTime();

	public Map<Integer, Integer> getHints();

	public default Map<Integer, Integer> defaultHints() {
		return Map.of(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE, GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
	}

	public default void loadValues() {

	}

	public default void disposeValues() {

	}

	public default String getData() {
		return "{" + "Width=[" + width() + "], Height=[" + height() + "], Title=[" + title() + "], ClearColor=["
				+ getClearColor().toString() + "], UpdateTime=[" + updateTime() + "], Hints=[" + getHints().toString()
				+ "]}";
	}

	public default boolean resizeable() {
		return false;
	}

}
