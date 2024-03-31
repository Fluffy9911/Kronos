/**
 * 
 */
package com.kronos.gameFramework.f2d;

import com.kronos.graphixs.g2d.TextureBatch;

/**
 * 
 */
public class ObjectRenderer {
	Position2D position;
	GameObject go;
	int id = 0;
	static int ii = 0;

	public ObjectRenderer(Position2D position, GameObject go) {
		this.position = position;
		this.go = go;
		id = ii++;
		go.onCreation(position);
	}

	public void renderAt(TextureBatch tb) {
		RenderProvider rp = go.getProvider();
		if (rp != null) {
			tb.drawTexture(position.x(), position.y(), position.width(), position.height(), rp.getTextureToRender(go));
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
