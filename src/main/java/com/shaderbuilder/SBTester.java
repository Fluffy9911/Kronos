/**
 * 
 */
package com.shaderbuilder;

import com.shaderbuilder.ShaderBuilder.SType;
import com.shaderbuilder.ShaderBuilder.VType;

/**
 * 
 */
public class SBTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShaderBuilder sb = new ShaderBuilder(null, 430, SType.core);
		sb.buildVersion().newLine();
		sb.uniform(VType.vec2, "testIn");
		sb.newLine();
		sb.out(VType.vec4, "frag");
		sb.newLine();
		sb.in(VType.vec3, "opos");
		sb.newLine();
		sb.mainStart();
		sb.newLine();
		sb.createVec4("col", 1, 0, 0, 1);
		sb.newLine();
		sb.add("col = vec4(opos, 1);");

		sb.newLine();
		sb.set("frag", "col");
		sb.newLine();
		sb.mainEnd();
		ShaderTester.testShader(sb.build());
	}

}
