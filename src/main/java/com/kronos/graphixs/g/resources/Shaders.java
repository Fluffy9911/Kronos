/**
 * 
 */
package com.kronos.graphixs.g.resources;

import com.kronos.graphixs.g.GResourceHolder;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.graphixs.shaders.BaseShader;
import com.kronos.graphixs.shaders.compute.ComputeShader;
import com.kronos.graphixs.shaders.geometry.GeometryShader;
import com.kronos.graphixs.shaders.render.RenderShader;

/**
 * 
 */
public class Shaders extends GResourceHolder<BaseShader> {
	public static final int GL_SHADER_VERTEX = 0;
	public static final int GL_SHADER_FRAGMENT = 1;
	public static final int GL_SHADER_GEOMETRY = 2;
	public static final int GL_SHADER_COMPUTE = 3;
	public static final int GL_SHADER_PROGRAM = 4;
	public static final int GL_SHADER_NULL = -1;
	public static final int GL_SHADER_RENDER = 5;

	/**
	 * @param g
	 */
	public Shaders(Graphixs g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load(Graphixs g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close(Graphixs g) {
		// TODO Auto-generated method stub

	}

	public int glGetShaderType(String id) {
		BaseShader shader = getResource(id);

		if (shader instanceof RenderShader) {
			return GL_SHADER_RENDER;
		}
		if (shader instanceof GeometryShader) {
			return GL_SHADER_GEOMETRY;
		}
		if (shader instanceof ComputeShader) {
			return GL_SHADER_COMPUTE;
		}
		return GL_SHADER_NULL;
	}

	public String glGetShaderSource(String id, int sid, int st) {
		if (glGetShaderType(id) == GL_SHADER_RENDER) {
			if (sid == GL_SHADER_FRAGMENT) {
				return ((RenderShader) getResource(id)).fragmentSource;
			}
			if (sid == GL_SHADER_VERTEX) {
				return ((RenderShader) getResource(id)).vertexSource;
			}
			return "null";
		}
		if (glGetShaderType(id) == GL_SHADER_GEOMETRY) {
			return ((GeometryShader) getResource(id)).geometrySource;
		}
		if (glGetShaderType(id) == GL_SHADER_COMPUTE) {
			return ((ComputeShader) getResource(id)).computeSource;
		}
		return "null";
	}

}
