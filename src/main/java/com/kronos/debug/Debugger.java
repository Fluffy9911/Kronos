package com.kronos.debug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;

public class Debugger {
	Logger logger;

	public Debugger() {
		init();
	}

	void init() {
		logger = LogManager.getLogger(Kronos.class);

		logger.info("Dubugger created");

	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

}
