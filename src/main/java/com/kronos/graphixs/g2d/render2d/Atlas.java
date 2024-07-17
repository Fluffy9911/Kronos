/**
 * 
 */
package com.kronos.graphixs.g2d.render2d;

import java.util.HashMap;

import com.kronos.Kronos;
import com.kronos.graphixs.shaders.bufferobjects.AdvancedBufferObject;
import com.kronos.graphixs.shaders.render.RenderShader;
import com.kronos.graphixs.texture.Texture;

/**
 * 
 */
public class Atlas {
	int width, height;
	int images = 0;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getImages() {
		return images;
	}

	public Texture getTexture() {
		return texture;
	}

	public AdvancedBufferObject getAtlas_data() {
		return atlas_data;
	}

	public HashMap<String, Integer> getIds() {
		return ids;
	}

	Texture texture;

	AdvancedBufferObject atlas_data;
	HashMap<String, Integer> ids;

	public Atlas(int width, int height, int images, Texture texture, AdvancedBufferObject atlas_data) {
		super();
		this.width = width;
		this.height = height;
		this.images = images;
		this.texture = texture;
		this.atlas_data = atlas_data;
	}

	public Atlas(int width, int height, int images, Texture texture, AdvancedBufferObject atlas_data,
			HashMap<String, Integer> ids) {
		super();
		this.width = width;
		this.height = height;
		this.images = images;
		this.texture = texture;
		this.atlas_data = atlas_data;
		this.ids = ids;
		Kronos.debug.getLogger().debug("Atlas Built: width: {}, Height: {}, Images: {}", width, height, images);
	}

	public RenderShader compileAtlasShader(String v, String f) {
		RenderShader rs = new RenderShader(v, f) {

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
		return rs;
	}

}
