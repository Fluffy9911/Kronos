/**
 * 
 */
package com.kronos.graphixs.shaders.builtin;

import org.joml.Matrix4f;

import com.kronos.graphixs.shaders.render.ShaderProgram;

/**
 * 
 */
public class Basic2DTextured extends ShaderProgram {
	Matrix4f projection, model;
	int texid = 0;

	public static final String vs = "#version 400 core\r\n" + "\r\n" + "in vec3 in_pos;\r\n" + "in vec2 uv;\r\n"
			+ "\r\n" + "uniform mat4 proj;\r\n" + "uniform mat4 transform;\r\n" + "\r\n" + "out vec2 uv_p;\r\n" + "\r\n"
			+ "void main() {\r\n" + "    \r\n" + "    \r\n" + "\r\n" + "	uv_p = uv;\r\n" + "	\r\n"
			+ "    gl_Position =  proj* transform*vec4(in_pos, 1.0);\r\n" + "    \r\n" + "}",
			fs = "#version 400 core \r\n" + "\r\n" + "\r\n" + "in vec2 uv_p;\r\n" + "uniform sampler2D tex;\r\n"
					+ "out vec4 fragOutput;\r\n" + "\r\n" + "void main() {\r\n" + "\r\n"
					+ "    fragOutput = texture2D(tex,vec2(-uv_p.x,-uv_p.y)).rgba;\r\n" + "    \r\n"
					+ "    if(fragOutput.w == 0){\r\n" + "    discard;\r\n" + "    }\r\n" + "}\r\n" + "";

	/**
	 * @param vs
	 * @param fs
	 */
	public Basic2DTextured() {
		super(vs, fs);
		this.compileShader();
	}

	@Override
	public void setUniforms() {
		this.addUniform("proj", projection);
		this.addUniform("transform", model);
		this.addUniform("tex", texid);
	}

}
