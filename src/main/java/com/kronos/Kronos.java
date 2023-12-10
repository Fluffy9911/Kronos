package com.kronos;

import com.kronos.core.Config;
import com.kronos.debug.Debugger;
import com.kronos.graphixs.Loop;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;

public class Kronos {
	public static Debugger debug = new Debugger();
	public static Config config = new Config();
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
		loader.create(kronos_out);

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

}
