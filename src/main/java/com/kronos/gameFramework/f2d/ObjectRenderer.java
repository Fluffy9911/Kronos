/**
 * 
 */
package com.kronos.gameFramework.f2d;

import org.joml.Matrix4f;

import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.texture.Texture;

/**
 * 
 */
public class ObjectRenderer {
	public Position2D position;
	public GameObject go;
	int id = 0;
	static int ii = 0;
	public Matrix4f tf, bb;

	public ObjectRenderer(Position2D position, GameObject go) {
		this.position = position;
		this.go = go;
		id = ii++;
		go.onCreation(position);
	}

	public ObjectRenderer(Position2D position, GameObject go, Matrix4f tf) {
		this.position = position;
		this.go = go;
		this.tf = tf;
		id = ii++;
		go.onCreation(position);
	}

	public void renderAt(TextureBatch tb) {
		if (go.getDefaultTransformation() != null) {
			bb = tf;
			tf = go.getDefaultTransformation();
		}
		if (tf == null) {
			tf = bb;
			if (tf == null)
				tf = new Matrix4f();
		}

		RenderProvider rp = go.getProvider();
		if (rp != null) {

			if (tf != null) {
				tb.drawTexture(position.x(), position.y(), position.width(), position.height(),
						rp.getTextureToRender(go), tf);
			} else {
				tb.drawTexture(position.x(), position.y(), position.width(), position.height(),
						rp.getTextureToRender(go));
			}
		}

	}

	public void renderAt(TextureBatch tb, Texture t) {
		RenderProvider rp = go.getProvider();
		if (rp != null) {

			if (tf != null) {
				tb.drawTexture(position.x(), position.y(), position.width(), position.height(), t, tf);
			} else {
				tb.drawTexture(position.x(), position.y(), position.width(), position.height(), t);
			}
		}

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
