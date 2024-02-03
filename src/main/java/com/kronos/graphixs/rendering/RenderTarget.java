/**
 * 
 */
package com.kronos.graphixs.rendering;

import com.kronos.Kronos;
import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.display.camera.Camera;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.rendering.buffers.MeshBuffer;
import com.kronos.graphixs.shaders.builtin.Shader3D;
import com.kronos.graphixs.shaders.render.ShaderUniform;

/**
 * 
 */
public class RenderTarget {
	FrameBuffer buffer;

	MeshBuffer<Mesh> meshes;
	Shader3D render;

	String bufferid;
	TargetConfig config;
	Camera targetCamera;

	public RenderTarget(Shader3D render, String bufferid, TargetConfig tc) {
		super();
		this.render = render;
		this.bufferid = bufferid;
		config = tc;
		rebuildBuffer();

	}

	public void rebuildBuffer() {
		this.buffer = Kronos.graphixs.requestBufferCreation(bufferid, config);
	}

	public MeshBuffer<Mesh> getMeshes() {
		return meshes;
	}

	public void setMeshes(MeshBuffer<Mesh> meshes) {
		this.meshes = meshes;
	}

	public Texture render(BufferRender br) {
		this.buffer.start();
		Kronos.graphixs.clearScreen(config.clear());
		br.render(render, meshes);
		this.buffer.end();
		return new Texture(buffer.getTextureID());
	}

	public TargetConfig getConfig() {
		return config;
	}

	public void setConfig(TargetConfig config) {
		this.config = config;
	}

	public Camera getTargetCamera() {
		return targetCamera;
	}

	public void setTargetCamera(Camera targetCamera) {
		this.targetCamera = targetCamera;
	}

	public static interface BufferRender {

		public void render(ShaderUniform s, MeshBuffer<Mesh> meshes);

	}

	public static interface TargetConfig {

		public int width();

		public int height();

		public Color clear();

	}
}
