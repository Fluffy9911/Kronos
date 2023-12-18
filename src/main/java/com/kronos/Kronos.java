package com.kronos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kronos.core.event.EngineListener;
import com.kronos.debug.Debugger;
import com.kronos.graphixs.Loop;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.Config;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;

public class Kronos {

	private static List<EngineListener> listeners;

	private static HashMap<String, Config> registeredConfig;

	public static String config_loc = "configs/main";

	public static Config k_config = new Config();
	public static Debugger debug = new Debugger();

	public static boolean debg = false, logdebug = false, extensivedebug = false;
	public static int max_threads = 2;

	public static String kronos_id = "kronos";

	public static ResourceIdentifier kronos_rid = new ResourceIdentifier() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return kronos_id + "-Main";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return "src/main/resources";
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
			return kronos_id + "/out";
		}
	};

	public static FileLoader loader = new FileLoader(kronos_rid);
	public static Graphixs graphixs = new Graphixs();

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
	}

	public static void start(ScreenConfig sc) {
		defaultKronosInit();
		graphixs.startGlSequence(debug.getLogger());
		graphixs.createScreen(sc);

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

}
