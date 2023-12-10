package com.kronos;


import com.kronos.debug.Debugger;
import com.kronos.graphixs.Loop;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.Config;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;

public class Kronos {
	
	public static String config_loc = "configs/main";
	
	public static com.kronos.io.Config k_config = new Config();
	public static Debugger debug = new Debugger();

	public static ResourceIdentifier kronos_rid = new ResourceIdentifier() {

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return "Kronos-Main";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return "src/main/resources";
		}
	};

	public static ResourceIdentifier kronos_out = new ResourceIdentifier() {

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return "Kronos-out";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return "kronos/out";
		}
	};

	public static FileLoader loader = new FileLoader(kronos_rid);
	public static Graphixs graphixs = new Graphixs();

	private static void defaultKronosInit() {
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			onEnd();
		}));
		loader.create(kronos_out);
if(loader.tryLoad(config_loc)!=null) {
	k_config = loader.tryRead("kronos_config.json", config_loc);
	debug.getLogger().debug("Loaded Config");
}else {
	k_config.appendInt("kronos_version", 1);
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
		loader.writeConfig(k_config, "kronos_config.json", config_loc,kronos_out);
	}
	
	
}
