package com.kronos.graphixs.display;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.nio.IntBuffer;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.g2d.ScreenUtils;
import com.kronos.graphixs.resources.Resource;
import com.kronos.io.InputHandler;

public class Screen implements Resource {
	Graphixs g = Kronos.graphixs;
	Logger l = Kronos.debug.getLogger();
	ScreenConfig sc;
	long id = -99;

	protected long init(ScreenConfig config) {
		g.setConfig(config);
		this.sc = config;
		Map<Integer, Integer> hints = config.getHints();
		for (Map.Entry<Integer, Integer> entry : hints.entrySet()) {
			Integer key = entry.getKey();
			Integer val = entry.getValue();
			GLFW.glfwWindowHint(key, val);

		}
		long id = createScreen(config.title(), config.width(), config.height());

		closeCallback(id);
		memory(id);
		if (Kronos.extensivedebug)
			GLUtil.setupDebugMessageCallback();

		this.id = id;
		return id;
	}

	/**
	 * @param id
	 */
	public void closeCallback(long id) {
		GLFW.glfwSetWindowCloseCallback(id, new GLFWWindowCloseCallback() {
			@Override
			public void invoke(long window) {
				glfwSetWindowShouldClose(window, true);
				System.exit(0);
			}
		});
		InputHandler.init(id);
		glfwSetWindowSizeCallback(id, (w, wi, h) -> {
			resize(wi, h);
		});
	}

	private void resize(int wi, int h) {
		Kronos.graphixs.setConfig(ScreenUtils.updateDimensions(sc, wi, h));
		Kronos.graphixs.runEvent("screen_listener");
		Kronos.debug.getLogger().debug("Screen Resized To: W{} H{}", wi, h);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, sc.width(), sc.height(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL40.glViewport(0, 0, sc.width(), sc.height());

	}

	private long createScreen(String name, int w, int h) {
		g.test(l);
		return GLFW.glfwCreateWindow(w, h, name, MemoryUtil.NULL, MemoryUtil.NULL);

	}

	private void memory(long wid) {
		g.test(l);
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			glfwGetWindowSize(wid, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(wid, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}

		glfwMakeContextCurrent(wid);
		GL.createCapabilities();
	}

	public void makeWindowVisible(long wid, int si) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, sc.width(), sc.height(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL40.glViewport(0, 0, sc.width(), sc.height());
		glfwSwapInterval(si);
		glfwShowWindow(wid);

	}

	@Override
	public void load() {
//no loading required unless we want to save resolutions
		// sc.loadValues();
	}

	@Override
	public void close() {
		sc.disposeValues();
	}

	public void test() {
		if (id == -99) {

			l.fatal("No Window Found");
			System.exit(404);

		}
	}

	public boolean shouldRender() {
		test();
		return !glfwWindowShouldClose(id);
	}

	public void preRender() {
		glClear(GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
	}

	public void setClear(ScreenConfig sc) {
		Color clear = sc.getClearColor();
		l.debug("Color {} ", clear.toString());
		GL40.glClearColor(clear.getR(), clear.getG(), clear.getB(), 1);

	}

	public void postRender() {
		glfwSwapBuffers(id);
		glfwPollEvents();
	}

}
