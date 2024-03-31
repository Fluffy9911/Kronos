/**
 * 
 */
package com.kronos.gameFramework.f2d;

import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureBuilder;

/**
 * 
 */
public interface RenderProvider {

	public Texture getTextureToRender(GameObject go);

	public static RenderProvider createDEF(Color c) {
		return new RenderProvider() {

			@Override
			public Texture getTextureToRender(GameObject go) {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(2, 2, 1, c, c);
			}

		};
	}

}
