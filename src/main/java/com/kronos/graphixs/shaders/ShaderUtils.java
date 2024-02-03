/**
 * 
 */
package com.kronos.graphixs.shaders;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import com.kronos.Kronos;
import com.kronos.graphixs.shaders.render.RenderShader;

/**
 * 
 */
public class ShaderUtils {

	public static void checkShaderCompilationStatus(RenderShader renderShader, int shaderID, String shaderType) {
		int status = GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS);
		if (status != GL20.GL_TRUE) {
			String infoLog = GL20.glGetShaderInfoLog(shaderID);
			Kronos.debug.getLogger().error("Error compiling " + shaderType + ":");
			Kronos.debug.getLogger().error(infoLog);
		}
	}

	public static void printAttributeNames(int shaderProgramID, int[] sizes, int[] types, int[] locations) {
		int attributeCount = GL20.glGetProgrami(shaderProgramID, GL20.GL_ACTIVE_ATTRIBUTES);
		System.out.println("Attribute Names:");
	
		for (int i = 0; i < attributeCount; i++) {
			ByteBuffer nameBuffer = MemoryUtil.memAlloc(128); // Adjust buffer size as needed
	
			GL20.glGetActiveAttrib(shaderProgramID, i, sizes, types, locations, nameBuffer);
	
			String attributeName = MemoryUtil.memUTF8(nameBuffer, sizes[0]);
			System.out.println("Attribute " + i + ": " + attributeName);
	
			// Record the size, type, and location
			locations[i] = GL20.glGetAttribLocation(shaderProgramID, attributeName);
	
			MemoryUtil.memFree(nameBuffer);
		}
	}

	public static void printAttributeNames(int shaderProgramID) {
		int attributeCount = GL20.glGetProgrami(shaderProgramID, GL20.GL_ACTIVE_ATTRIBUTES);
		System.out.println("Attribute Names:");
	
		for (int i = 0; i < attributeCount; i++) {
			int[] size = new int[1];
			int[] type = new int[1];
			int[] loc = new int[1];
			ByteBuffer nameBuffer = MemoryUtil.memAlloc(128); // Adjust buffer size as needed
	
			GL20.glGetActiveAttrib(shaderProgramID, i, size, type, loc, nameBuffer);
	
			String attributeName = MemoryUtil.memUTF8(nameBuffer, size[0]);
			System.out.println("Attribute " + i + ": " + attributeName);
	
		}
	}

}
