package com.kronos.core.event;

import com.kronos.Kronos;
import com.kronos.io.Config;

public class StartupConfigManager {

	public static void create(Config conf) {

		if (conf.readOrWriteBoolean("use_low_end", true) == true) {
			configureForLowEnd(conf);
		}

	}

	public static void configureForLowEnd(Config c) {
		Kronos.debug.getLogger().debug("Kronos Is running in low-end mode, some features may be unavailable");
	}

}
