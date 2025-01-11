/**
 * 
 */
package com.kronos.gameFramework.f2d;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.color.Colors;
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

	public default Texture imageFromString(String id) {
		Texture t = Kronos.graphixs.getTexture(id);
		if (t == null) {
			return createDEF(Colors.randColorAlpha()).getTextureToRender(null);
		} else {
			return t;
		}
	}

	public default Texture getSelectionTexture() {
		return null;
	}

}
