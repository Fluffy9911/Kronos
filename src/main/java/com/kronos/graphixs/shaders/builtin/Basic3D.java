/**
 * 
 */
package com.kronos.graphixs.shaders.builtin;

import org.joml.Matrix4f;

import com.kronos.graphixs.display.camera.PerspectiveCamera;
import com.kronos.graphixs.shaders.ShaderProgram;

/**
 * 
 */
public class Basic3D extends ShaderProgram {
	public static String vert = "#version 400 core\r\n" + "\r\n" + "in vec3 in_pos;\r\n" + "in vec3 in_color;\r\n"
			+ "in vec3 in_normal;\r\n" + "\r\n" + "out vec3 f_color;\r\n" + "out vec3 f_normal;\r\n"
			+ "out vec3 f_fpos;\r\n" + "\r\n" + "uniform mat4 proj;\r\n" + "uniform mat4 view;\r\n"
			+ "uniform mat4 model;\r\n" + "uniform mat4 omodel\r\n" + "\r\n" + "void main() {\r\n" + "    \r\n"
			+ "    \r\n" + "f_color = in_color;\r\n" + "\r\n" + "\r\n"
			+ "//f_normal = mat3(transpose(inverse(model))) * in_normal;\r\n" + "f_normal = in_normal;\r\n"
			+ " f_fpos = vec3(model * vec4(in_pos, 1.0));\r\n"
			+ "    gl_Position = proj * view * model* omodel*  vec4(in_pos, 1.0);\r\n" + "    \r\n" + "}",
			frag = "#version 400 core \r\n" + "\r\n" + "\r\n" + "in vec3 f_color;\r\n" + "in vec3 f_normal;\r\n"
					+ "in vec3 f_fpos;\r\n" + "\r\n" + "uniform float as;\r\n" + "uniform vec3 ac;\r\n" + "\r\n"
					+ "uniform vec3 lp;\r\n" + "uniform vec3 lightColor;\r\n" + "uniform vec3 viewPos;\r\n" + "\r\n"
					+ "out vec4 fragOutput;\r\n" + "\r\n" + "void main() {\r\n" + "\r\n"
					+ "float specularStrength = 1;\r\n" + "\r\n" + "\r\n" + " float ambientStrength = as;\r\n"
					+ "    vec3 ambient = ambientStrength * ac;\r\n" + "\r\n" + "   \r\n" + "   \r\n" + "\r\n"
					+ "vec3 norm = normalize(f_normal);\r\n" + "vec3 lightDir = normalize(lp - f_fpos);\r\n" + "\r\n"
					+ "\r\n" + "float diff = max(dot(norm, lightDir), 0.0);\r\n"
					+ "vec3 diffuse = diff * lightColor;\r\n" + "\r\n" + "\r\n"
					+ "vec3 viewDir = normalize(viewPos - f_fpos);\r\n"
					+ "vec3 reflectDir = reflect(-lightDir, f_normal);  \r\n"
					+ "float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);\r\n"
					+ "vec3 specular = specularStrength * spec * lightColor;  \r\n" + "\r\n"
					+ "    vec3 result = (ambient + diffuse + specular) * f_color;\r\n"
					+ "fragOutput = vec4(result, 1.0);\r\n" + "}";

	/**
	 * @param vs
	 * @param fs
	 */
	public Basic3D(String vs, String fs) {
		super(vs, fs);

	}

	public Basic3D() {
		this(vert, frag);
	}

	public void appendCam(PerspectiveCamera pc) {

	}

	public void setObjectModel(Matrix4f model) {

	}

}
