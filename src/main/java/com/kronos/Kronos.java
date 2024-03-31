package com.kronos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kronos.core.CoreConfig;
import com.kronos.core.event.EngineListener;
import com.kronos.debug.Debugger;
import com.kronos.graphixs.Loop;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.io.Config;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;
import com.kronos.io.assets.InternalAssetLoader;
import com.kronos.plugin.PluginLoader;

public class Kronos {
	public static String[] args;
	private static List<EngineListener> listeners;
	private static HashMap<String, Config> registeredConfig;

	private static PluginLoader plugin_loader;

	// config
	public static Config k_config = new Config();

	// config values
	public static boolean debg = false;
	public static boolean logdebug = false;
	public static boolean extensivedebug = false;
	public static int max_threads = 2;

	public static boolean plugins = false;

	// resources
	public static String config_loc = "configs/main";
	public static String kronos_id = "kronos";
	public static ResourceIdentifier kronos_rid = new ResourceIdentifier() {

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return kronos_id + "-Main";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return "src\\main\\resources";
		}
	};

	public static ResourceIdentifier kronos_out = new ResourceIdentifier() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return kronos_id + "-out";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return kronos_id + "\\out";
		}
	};

	// modules
	public static Debugger debug = new Debugger();
	public static FileLoader loader = new FileLoader(kronos_rid);
	public static Graphixs graphixs = new Graphixs();

	/**
	 * not available until startup
	 */
	public static CoreConfig config;

	/**
	 * Starts Kronos default startup making config and listeners
	 */
	private static void defaultKronosInit() {
		listeners = new ArrayList<>();
		registeredConfig = new HashMap<>();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			onEnd();
		}));
		loader.create(kronos_out);
		if (loader.tryLoad(config_loc + "\\" + kronos_id + "_config.json", kronos_out) != null) {
			k_config = loader.tryRead(kronos_id + "_config.json", config_loc, kronos_out);

			debug.getLogger().debug("Loaded Config");
			buildConfig(k_config);
		} else {
			buildConfig(k_config);
		}
		debug.getLogger().debug("Base Kronos done setup");
		debug.getLogger().debug("Startup Config: {}", startupInfo());
		if (plugins) {
			debug.getLogger().debug("Beginning Plugin loading");
			try {
				plugin_loader.loadPlugins();
			} catch (Exception e) {
				debug.getLogger().error("Failed loading plugins: {}", e);
			}
		}
	}

	/**
	 * Starts opengl
	 * 
	 * @param sc
	 */
	public static void start(ScreenConfig sc) {
		long time = System.currentTimeMillis();
		defaultKronosInit();
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			EngineListener el = (EngineListener) iterator.next();
			el.engineStart();
		}
		graphixs.dev = false;
		graphixs.startGlSequence(debug.getLogger());
		createGLScreen(sc);
		debug.getLogger().debug("Loading time: {}MS", (System.currentTimeMillis() - time));
	}

	public static void createGLScreen(ScreenConfig sc) {
		graphixs.createScreen(sc);
		config = new CoreConfig(graphixs, graphixs.g2d, loader, new InternalAssetLoader(config_loc), debug.getLogger());
		config.setCurrent(graphixs.getConfig());
	}

	/**
	 * Starts kronos with dev features turned on
	 * 
	 * @param sc
	 */
	public static void startInDev(ScreenConfig sc) {
		long time = System.currentTimeMillis();
		defaultKronosInit();
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			EngineListener el = (EngineListener) iterator.next();
			el.engineStart();
		}
		graphixs.startGlSequenceDev(debug.getLogger());
		createGLScreen(sc);
		debug.getLogger().debug("Loading time in DEV: {}MS", (System.currentTimeMillis() - time));
	}

	public static void startInDev(ScreenConfig sc, String[] args) {
		Kronos.args = args;
		long time = System.currentTimeMillis();
		defaultKronosInit();
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			EngineListener el = (EngineListener) iterator.next();
			el.engineStart();
		}
		graphixs.startGlSequenceDev(debug.getLogger());
		createGLScreen(sc);
		debug.getLogger().debug("Loading time in DEV: {}MS", (System.currentTimeMillis() - time));
	}

	/**
	 * @param sc
	 * @param l
	 */
	public static void startDrawing(Loop l) {
		ScreenConfig sc = graphixs.getConfig();
		graphixs.render(l, sc.updateTime());
	}

	public static void onEnd() {

		loader.writeConfig(k_config, kronos_id + "_config.json", config_loc, kronos_out);
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			EngineListener el = (EngineListener) iterator.next();
			el.engineEnd();
		}
	}

	public static void registerListener(EngineListener el) {
		listeners.add(el);
	}

	public static void buildConfig(Config c) {
		debg = c.readOrWriteBoolean("use_debug", false);

		logdebug = c.readOrWriteBoolean("log_debug", false);

		extensivedebug = c.readOrWriteBoolean("extensive_logging", false);

		max_threads = c.readOrWriteInt("max_usable_threads", 2);

		kronos_id = c.readOrWriteString("kronos_id", "Kronos");

		for (Map.Entry<String, Config> entry : registeredConfig.entrySet()) {
			String key = entry.getKey();
			Config val = entry.getValue();
			registeredConfig.put(key, c.readOrWriteConfig(key, val));
		}

	}

	public static void registerConfig(String key, Config val) {
		registeredConfig.put(key, val);
	}

	public Config get(String key) {
		return registeredConfig.get(key);
	}

	public static void beginHeadlessGL() {
		defaultKronosInit();
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			EngineListener el = (EngineListener) iterator.next();
			el.engineStart();
		}
		graphixs.startGlSequence(debug.getLogger());
	}

	public static void enablePlugins(File folder) {
		plugins = true;
		plugin_loader = new PluginLoader(folder);
	}

	private static String startupInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("{UsingDebug=" + debg).append(", LoggingDebug=" + logdebug)
				.append(", ExtensiveDebug=" + extensivedebug).append(", MaxThreads=" + max_threads)
				.append(", UsingPlugins=" + plugins);
		if (plugin_loader != null) {
			sb.append(", PluginLocation=" + plugin_loader.getFolder().getAbsolutePath());
		}
		sb.append("}");
		return sb.toString();
	}

	private static void loadPlugins() {
		if (plugin_loader != null) {

		}

	}

	/**
	 * 
	 */
	public static void shutdown() {
		Kronos.debug.getLogger().debug("Shutting down");
		Kronos.graphixs.closeResources();
		System.exit(0);

	}

}
