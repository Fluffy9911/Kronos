/**
 * 
 */
package com.shaderbuilder;

import java.util.Map;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.display.ScreenConfig;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.graphixs.shaders.render.RenderShader;
import com.kronos.io.InputHandler;
import com.kronos.io.Keys;
import com.shaderbuilder.ShaderBuilder.SType;
import com.shaderbuilder.ShaderBuilder.VType;

/**
 * 
 */
public class ShaderTester {

	public static void testShader(String fs) {
		ShaderBuilder sb = new ShaderBuilder(null, 430, SType.core);
		sb.buildVersion().newLine();
		sb.in(VType.vec3, "in_pos");

		sb.newLine().newLine();
		sb.out(VType.vec3, "opos");
		sb.newLine();
		sb.mainStart();
		sb.newLine();

		sb.createVec4FromVec3("glOut", "in_pos", 1);
		sb.newLine();

		sb.set("opos", "in_pos");
		sb.newLine();

		sb.set("gl_Position", "glOut");
		sb.newLine();
		sb.mainEnd();
		ScreenConfig sc = new ScreenConfig() {

			@Override
			public int width() {
				// TODO Auto-generated method stub
				return 900;
			}

			@Override
			public int updateTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String title() {
				// TODO Auto-generated method stub
				return "Shader Testing";
			}

			@Override
			public int height() {
				// TODO Auto-generated method stub
				return 900;
			}

			@Override
			public Map<Integer, Integer> getHints() {
				// TODO Auto-generated method stub
				return this.defaultHints();
			}

			@Override
			public Color getClearColor() {
				// TODO Auto-generated method stub
				return Colors.White;
			}
		};

		Kronos.start(sc);
		RenderShader rs = new RenderShader(sb.build(), fs) {

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}

			@Override
			public void close() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setUniforms() {
				// TODO Auto-generated method stub

			}

			@Override
			public void setAttribs() {
				// TODO Auto-generated method stub

			}
		};
		Kronos.graphixs.createShader("test_shader", rs);
		Mesh m = Builtin.screenQuad();
		Kronos.startDrawing((l) -> {

			m.render(rs);
			if (InputHandler.isKeyReleased(Keys.A)) {
				System.out.println("Dumping Shader Code...");
				System.out.println(sb.build());
				System.out.println(fs);
			}
			InputHandler.nextFrame();
		});
	}

}
