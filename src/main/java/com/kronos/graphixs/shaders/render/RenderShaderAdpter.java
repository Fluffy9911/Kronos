/**
 * 
 */
package com.kronos.graphixs.shaders.render;

/**
 * 
 */
public class RenderShaderAdpter {

	public static String[] toDat(RenderShader rs) {
		return new String[] { rs.vertexSource, rs.fragmentSource };
	}

	public static RenderShader from(String[] s) {
		return new ShaderProgram(s[0], s[1]);
	}

}
