/**
 * 
 */
package com.kronos.graphixs.shaders.builtin;

import org.joml.Matrix4f;

import com.kronos.graphixs.shaders.render.ShaderProgram;

/**
 * 
 */
public class Basic2DColored extends ShaderProgram {
	Matrix4f projection, model;

	public static final String vs = "#version 400 core\r\n" + "\r\n" + "in vec3 in_pos;\r\n" + "in vec3 in_color;\r\n"
			+ "\r\n" + "uniform mat4 proj;\r\n" + "uniform mat4 transform;\r\n" + "\r\n" + "out vec3 out_color;\r\n"
			+ "\r\n" + "void main() {\r\n" + "    \r\n" + "    \r\n" + "\r\n" + "	out_color = in_color;\r\n"
			+ "	\r\n" + "    gl_Position =  proj* transform*vec4(in_pos, 1.0);\r\n" + "    \r\n" + "}",
			fs = "#version 400 core \r\n" + "\r\n" + "\r\n" + "in vec3 out_color;\r\n" + "\r\n" + "\r\n"
					+ "out vec4 fragOutput;\r\n" + "\r\n" + "void main() {\r\n"
					+ "    fragOutput = vec4(out_color,1);\r\n" + "    \r\n" + "}";

	/**
	 * @param vs
	 * @param fs
	 */
	public Basic2DColored() {
		super(vs, fs);
		this.compileShader();
	}

	public Matrix4f getProjection() {
		return projection;
	}

	public void setProjection(Matrix4f projection) {
		this.projection = projection;
	}

	public Matrix4f getModel() {
		return model;
	}

	public void setModel(Matrix4f model) {
		this.model = model;
	}

	@Override
	public void setUniforms() {
		this.addUniform("proj", projection);
		this.addUniform("transform", model);
	}

}
