package com.kronos.graphixs.display;

import static org.lwjgl.glfw.GLFW.glfwInit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL43C;
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
import com.kronos.graphixs.g2d.builder.ShapeRenderer;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.graphixs.rendering.RenderTarget.TargetConfig;
import com.kronos.graphixs.resources.Resource;
import com.kronos.graphixs.resources.ResourceManager;
import com.kronos.graphixs.shaders.builtin.Shader3D;
import com.kronos.graphixs.shaders.render.RenderShader;
import com.kronos.graphixs.shaders.render.ShaderProgram;
import com.kronos.graphixs.shaders.render.ShaderUniform;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

public class Graphixs {
	boolean g_lock = true, dev = false;
	private ScreenConfig config;
	private Screen screen;
	private ResourceManager manager = new ResourceManager();
	private FixedLoopSystem fls;
	private Logger l = Kronos.debug.getLogger();
	public Mesh post_process_quad;
	public HashMap<String, FrameBuffer> buffers = new HashMap<>();
	private HashMap<String, RenderShader> shaders = new HashMap<String, RenderShader>();
	private long window_id = -1;

	public Graphixs2D g2d;
	private ListMap<String, GraphicEvent> events;
	String fs, vs;
	public HashMap<String, Texture> textures;

	public double DELTA_TIME = 0;

	// modules
	public ShapeRenderer shapeRenderer = new ShapeRenderer();

	public void createShader(String id, RenderShader s) {
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
		textures = new HashMap<>();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			manager.close();
		}));

	}

	public void startGlSequenceDev(Logger l) {
		dev = true;
		l.debug("Starting OPENGL");
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		l.debug("GL Version: {} ", GLFW.glfwGetVersionString());
		l.debug("GL Started");
		g_lock = false;
		events = new ListMap<String, GraphicEvent>();
		textures = new HashMap<>();
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
		this.setConfig(config);
		this.screen = new Screen();
		window_id = screen.init(config);
		manager.add(screen);
		screen.load();
		if (dev) {
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
			createShader("3d", new Shader3D(Kronos.loader.tryLoad("shaders/threed.vs"),
					Kronos.loader.tryLoad("shaders/basiccolor.fs"), null));

			fs = Kronos.loader.tryLoad("shaders/texture.fs");
			vs = Kronos.loader.tryLoad("shaders/fragment.fs");
			shaders.get("texture").compileShader();
		}
		g2d = new Graphixs2D(buffers.get("graphixs2d_pane"), new ScreenProvider(config),
				(ShaderProgram) shaders.get("texture"));

		l.debug("Shaders Loaded:");
		for (Map.Entry<String, RenderShader> entry : shaders.entrySet()) {
			String key = entry.getKey();
			RenderShader val = entry.getValue();
			l.debug("RenderShader: {}, status? {}", key, val.getShaderCompilationStatus());
		}
		l.debug("Loading textures");
		if (dev) {
			textures.put("test_shape", new Texture(Kronos.loader.tryLoadImage("texture/test_shape.png")));
			textures.put("button_base", new Texture(Kronos.loader.tryLoadImage("texture/button.png")));
			textures.put("down", new Texture(Kronos.loader.tryLoadImage("texture/down.png")));
			textures.put("slider_left", new Texture(Kronos.loader.tryLoadImage("texture/slider_left.png")));
			textures.put("slider_right", new Texture(Kronos.loader.tryLoadImage("texture/slider_right.png")));
			textures.put("slider", new Texture(Kronos.loader.tryLoadImage("texture/slider_middle.png")));
			textures.put("toggle_off", new Texture(Kronos.loader.tryLoadImage("texture/bg.png")));
			textures.put("toggle_on", new Texture(Kronos.loader.tryLoadImage("texture/bg.png")));
			textures.put("top", new Texture(Kronos.loader.tryLoadImage("texture/top.png")));
			textures.put("up", new Texture(Kronos.loader.tryLoadImage("texture/up.png")));
		}
		for (Map.Entry<String, Texture> entry : textures.entrySet()) {
			String key = entry.getKey();
			Texture val = entry.getValue();

			l.debug("Loaded Texture: {} with size of W: {} H : {}", key, val.getHeight(), val.getHeight());

		}

		screen.makeWindowVisible(window_id, config.updateTime());
	}

	public FrameBuffer requestBufferCreation(String id, TargetConfig tc) {
		FrameBuffer fb = new FrameBuffer(tc.width(), tc.height(), true);
		l.debug("FrameBuffer built to: w: {} h: {}", tc.width(), tc.height());
		this.createBuffer(id, fb);
		return fb;
	}

	public void createBuffer(String id, FrameBuffer fb) {
		this.buffers.put(id, fb);
	}

	public void render(Runnable r) {
		screen.setClear(config);
		fls = new FixedLoopSystem(60) {

			@Override
			public void update() {
				while (screen.shouldRender()) {
					screen.preRender();
					r.run();
					DELTA_TIME = fls.getDeltaTime();
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

	public void writeTextureOut(String name, int w, int h) {
		test(l);
		ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);
		GL11.glReadPixels(0, 0, w, h, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		// Save the pixel data as a PNG file
		l.debug("Writing texture out: W {} H: {}", w, h);
		STBImageWrite.stbi_write_png(Kronos.loader.createFilePath("test/out", name + ".png"), w, h, 4, buffer, 0);
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

	public void drawPPQuad(RenderShader shader) {

		GL40.glActiveTexture(GL40.GL_TEXTURE0);
		buffers.get("post_proccess").bindTexture();

		post_process_quad.renderPPO(shader, buffers.get("post_proccess"));

		// post_process_quad.render(shader);

		buffers.get("post_proccess").unbindTexture();
	}

	public void drawPPQuad(RenderShader shader, FrameBuffer fb) {
		post_process_quad.renderPPO(shader, fb);
	}

	public <T extends ShaderUniform> T getShader(String id) {
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

	public void renderEdge(RenderShader s) {
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

	public void drawToScreen(RenderShader s) {
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

	public void drawTexture(double x, double y, double w, double h, Texture t, ShaderProgram sp) {
		Mesh m = Builtin.textured_quad((float) x, (float) y, (float) w, (float) h);
		t.bind();
		sp.use();
		sp.setUniforms();
		m.render(sp);
		t.unbind();
		m.cleanup();
		GL40.glUseProgram(0);
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

	public class Targets {

	}

	public Obj readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		return ObjUtils.convertToRenderable(ObjReader.read(stream));
	}

	/**
	 * 
	 */
	public void closeResources() {
		this.logChanges("Resource Manager was requested to cleaup resources");
		this.manager.close();

	}

	public void createTexture(String name, String path) {
		l.debug("loaded texture: {}", name);
		textures.put(name, new Texture(Kronos.loader.tryLoadImage(path)));
	}

	public Texture getTexture(String id) {
		return textures.get(id);
	}

	public void endableCullingCCW(int type) {
		GL43C.glEnable(GL43.GL_CULL_FACE);
		GL43C.glCullFace(type);
		GL43C.glFrontFace(GL40.GL_CCW);
	}

	public void endableCullingCW(int type) {
		GL43C.glEnable(GL43.GL_CULL_FACE);
		GL43C.glCullFace(type);
		GL43C.glFrontFace(GL40.GL_CW);
	}
}
