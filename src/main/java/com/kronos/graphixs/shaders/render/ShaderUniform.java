/**
 * 
 */
package com.kronos.graphixs.shaders.render;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.kronos.graphixs.shaders.bufferobjects.BufferObject;

/**
 * 
 */
public interface ShaderUniform {

	void setAttribs();

	void addUniform(String id, int value);

	void addUniform(String id, float value);

	void addUniform(String id, Vector4f vec4);

	void addUniform(String id, Vector3f vec4);

	void addUniform(String id, Vector2f vec4);

	void addUniform(String id, Matrix4f mat4);

	void setUniforms();

	void use();

	Vector2i bindBufferObject(BufferObject bo, String bufferName);

}