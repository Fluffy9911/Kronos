package com.kronos.graphixs.display;

import static org.lwjgl.glfw.GLFW.glfwInit;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL46;
import org.lwjgl.stb.STBImageWrite;

import com.kronos.Kronos;
import com.kronos.core.util.ListMap;
import com.kronos.graphixs.FixedLoopSystem;
import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.Loop;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.events.GraphicEvent;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.graphixs.rendering.RenderManager;
import com.kronos.graphixs.resources.Resource;
import com.kronos.graphixs.resources.ResourceManager;
import com.kronos.graphixs.shaders.Shader;
import com.kronos.graphixs.shaders.ShaderProgram;

public class Graphixs {
	boolean g_lock = true;
	private ScreenConfig config;
	private Screen screen;
	private ResourceManager manager = new ResourceManager();
	private FixedLoopSystem fls;
	private Logger l = Kronos.debug.getLogger();
	public Mesh post_process_quad;
	public HashMap<String, FrameBuffer> buffers = new HashMap<>();
	private HashMap<String, Shader> shaders = new HashMap<String, Shader>();
	private long window_id = -1;
	public RenderManager manager_render = new RenderManager(this);
	public Graphixs2D g2d;
	private ListMap<String, GraphicEvent> events;
	String fs, vs;

	public void createShader(String id, Shader s) {
		test(l);
		s.compileShader();
		l.info("Compiled and registered shaderid: {}", id);
		shaders.put(id, s);
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(ScreenConfig config) {
		Kronos.debug.getLogger().info("Set Current ScreenConfig to {}", config.getData());
		this.config = config;

	}

	public void setFrame() {
		GL20.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_LINE);
	}

	public void setFilled() {
		GL20.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_FILL);
	}

	/**
	 * @return the config
	 */
	public ScreenConfig getConfig() {
		return config;
	}

	public void startGlSequence(Logger l) {
		l.debug("Starting OPENGL");
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		l.debug("GL Version: {} ", GLFW.glfwGetVersionString());
		l.debug("GL Started");
		g_lock = false;
		events = new ListMap<String, GraphicEvent>();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			manager.close();
		}));

	}

	public void test(Logger l) {
		if (g_lock) {
			l.fatal("Graphics Not Initialized");
			System.exit(404);
		}
	}

	public void sceenClear(float r, float g, float b, float a) {
		test(Kronos.debug.getLogger());
		GL40.glClearColor(r, g, b, a);
	}

	/**
	 * G2D is not Available until this method is called!
	 * 
	 * @param config
	 */
	public void createScreen(ScreenConfig config) {
		this.screen = new Screen();
		window_id = screen.init(config);
		manager.add(screen);
		screen.load();
		buffers.put("edge_detection", new FrameBuffer(config.width(), config.height(), true));
		buffers.put("post_proccess", new FrameBuffer(config.width(), config.height(), true));
		buffers.put("graphixs2d_pane", new FrameBuffer(config.width(), config.height(), true));
		post_process_quad = Builtin.screenQuad();
		createShader("texture", new TextureProgram(Kronos.loader.tryLoad("shaders/texture.vs"),
				Kronos.loader.tryLoad("shaders/texture.fs")));
		createShader("highlight", new HighlightProgram(Kronos.loader.tryLoad("shaders/texture.vs"),
				Kronos.loader.tryLoad("shaders/highlight.fs")));
		createShader("highlight_g", new HighlightProgram(Kronos.loader.tryLoad("shaders/texture.vs"),
				Kronos.loader.tryLoad("shaders/highlighted_g.fs")));
		createShader("pp_tex", new ShaderProgram(Kronos.loader.tryLoad("shaders/vertex.vs"),
				Kronos.loader.tryLoad("shaders/fragment.fs")));
		fs = Kronos.loader.tryLoad("shaders/texture.fs");
		vs = Kronos.loader.tryLoad("shaders/fragment.fs");
		g2d = new Graphixs2D(buffers.get("graphixs2d_pane"), new ScreenProvider(config),
				(ShaderProgram) shaders.get("texture"));
		shaders.get("texture").compileShader();
		l.debug("Shaders Loaded:");
		for (Map.Entry<String, Shader> entry : shaders.entrySet()) {
			String key = entry.getKey();
			Shader val = entry.getValue();
			l.debug("Shader: {}, status? {}", key, val.getShaderCompilationStatus());
		}

	}

	public void render(Runnable r) {
		screen.setClear(config);
		fls = new FixedLoopSystem(60) {

			@Override
			public void update() {
				while (screen.shouldRender()) {
					screen.preRender();
					r.run();
					screen.postRender();
				}

			}
		};
		fls.start();

	}

	public void render(Loop r, int fps) {
		screen.setClear(config);
		fls = new FixedLoopSystem(fps) {

			@Override
			public void update() {
				if (screen.shouldRender()) {
					screen.preRender();
					r.update(getAccessor());
					screen.postRender();
				}

			}
		};
		fls.start();

	}

	public void closeNow(Resource rc) {
		rc.close();
	}

	public void loadNow(Resource rc) {
		rc.load();
	}

	/**
	 * @param e
	 * @return
	 * @see com.kronos.graphixs.resources.ResourceManager#add(com.kronos.graphixs.resources.Resource)
	 */
	public boolean add(Resource e) {
		return manager.add(e);
	}

	/**
	 * 
	 * @param name dont append file extensions
	 */
	public void writeTextureOut(String name) {
		test(l);
		ByteBuffer buffer = BufferUtils.createByteBuffer(config.width() * config.height() * 4);
		GL11.glReadPixels(0, 0, config.width(), config.height(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		// Save the pixel data as a PNG file
		STBImageWrite.stbi_write_png(Kronos.loader.createFilePath("test/out", name + ".png"), config.width(),
				config.height(), 4, buffer, 0);
	}

	public void enablePPBuffer() {
		buffers.get("post_proccess").start();
	}

	public void disablePPBuffer() {
		buffers.get("post_proccess").end();
	}

	public void enableEdgeBuffer() {
		buffers.get("edge_detection").start();
	}

	public void disableEdgeBuffer() {
		buffers.get("edge_detection").end();
	}

	public void drawPPQuad(Shader shader) {

		GL40.glActiveTexture(GL40.GL_TEXTURE0);
		buffers.get("graphixs2d_pane").bindTexture();

		// post_process_quad.renderPPO(shader, buffers.get("graphixs2d_pane"));

		post_process_quad.render(shader);

		buffers.get("graphixs2d_pane").unbindTexture();
	}

	public void drawPPQuad(Shader shader, FrameBuffer fb) {
		post_process_quad.renderPPO(shader, fb);
	}

	public <T extends Shader> T getShader(String id) {
		return (T) shaders.get(id);
	}

	public void clearScreen(Color c) {
		GL40.glClearColor(c.getR(), c.getG(), c.getB(), c.getA());
		GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * @return the window_id
	 */
	public long getWindow_id() {
		return window_id;
	}

	public FrameBuffer getBuffer(String string) {

		return buffers.get(string);
	}

	public void renderEdge(Shader s) {
		// enableEdgeBuffer();
		post_process_quad.renderPPO(s, getBuffer("post_proccess"));
		// writeTextureOut("edge_test");
		// disableEdgeBuffer();
	}

	public void renderPixel(Runnable run) {
		enablePPBuffer();

		run.run();
		// writeTextureOut("pixel_test");
		disablePPBuffer();
	}

	public void drawToScreen(Shader s) {
		GL40.glEnable(GL40.GL_TEXTURE_2D);
		buffers.get("edge_detection").bindTexture();
		buffers.get("post_proccess").bindTexture();

		post_process_quad.render(s);
		buffers.get("edge_detection").unbindTexture();
		buffers.get("post_proccess").unbindTexture();
	}

	public void drawTexture(double x, double y, double w, double h, Texture t) {
		Mesh m = Builtin.textured_quad((float) x, (float) y, (float) w, (float) h);
		t.bind();
		shaders.get("texture").addUniform("tex", t.textureId);
		m.render(shaders.get("texture"));
		t.unbind();
		m.cleanup();
	}

	public void glErrors() {
		int gl = GL46.glGetError();
		if (gl != 0)
			Kronos.debug.getLogger().debug("GL Error: {}", gl);

	}

	public void logChanges(String i) {
		l.debug("Graphixs Config has changed: {}", i);
	}

	public void logErrors(String e) {
		l.error("The Program Has Reported Graphixs Errors: {}", e);
	}

	public void registerEvent(String s, GraphicEvent ge) {
		events.put(s, ge);
	}

	public void createEvent(String key) {
		events.create(key);
	}

	public void runEvent(String key) {
		List<GraphicEvent> ev = events.get(key);
		for (Iterator iterator = ev.iterator(); iterator.hasNext();) {
			GraphicEvent ge = (GraphicEvent) iterator.next();
			ge.updated(this);

		}

	}

	public void runEvent2D(String key) {
		List<GraphicEvent> ev = events.get(key);
		for (Iterator iterator = ev.iterator(); iterator.hasNext();) {
			GraphicEvent ge = (GraphicEvent) iterator.next();
			ge.update2D(g2d);

		}

	}

	public String getFs() {
		return fs;
	}

	public String getVs() {
		return vs;
	}

}
