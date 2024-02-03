/**
 * 
 */
package com.kronos.graphixs.shaders;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.graphixs.resources.Resource;

/**
 * 
 */
public abstract class BaseShader implements Resource {
	Logger shaderLogger = Kronos.debug.getLogger();

	public void logShaderError(String error) {
		shaderLogger.error("Shader Has Thrown an error: {}", error);
	}

}
