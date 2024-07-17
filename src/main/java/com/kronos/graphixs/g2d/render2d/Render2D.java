/**
 * 
 */
package com.kronos.graphixs.g2d.render2d;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4f;

import com.kronos.Kronos;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.geometry.meshing.Builtin;
import com.kronos.graphixs.shaders.bufferobjects.AdvancedBufferObject;
import com.kronos.graphixs.shaders.render.RenderShader;

/**
 * 
 */
public class Render2D {
	Atlas use;
	AdvancedBufferObject textures;

	HashMap<Integer, Vector4f> buf;
	RenderShader rs;
	Vector2i t, a;

	public Render2D(Atlas use, RenderShader rs) {
		super();
		this.use = use;
		this.rs = rs;
		buf = new HashMap<>();
	}

	public void sus() {
		t = rs.bindBufferObject(textures, "ImgDat", rs);
		a = rs.bindBufferObject(use.getAtlas_data(), "TexDat", rs);

	}

	public void addToBuffer(int id, int x, int y, int w, int h) {
		buf.put(id, new Vector4f(x, y, w, h));
	}

	public static Vector2f splitFloat(float value) {
		int top = (int) Math.floor(value);
		float dec = ((value * 1000) - (top * 1000));
		dec = dec /= 1000;
		if (countDecimalPlaces(dec) == 1) {
			dec = dec * 100;
		} else {
			dec = dec * countDecimalPlaces(dec);
		}
		return new Vector2f(top, dec);
	}

	public static int countDecimalPlaces(double num) {
		int count = 0;
		double n2 = num * 10;
		n2 = round(n2);
		// Multiply by 10 until no fraction remains
		System.out.println(round(n2 - Math.floor(n2)));
		while ((n2 - Math.floor(n2)) < 1) {
			n2 *= 10;
			count++;
		}
		System.out.println("num " + num + " count " + count);
		return count;
	}

	public static double round(double n2) {
		n2 = Math.round(n2 * 100000d) / 100000d;
		return n2;
	}

	public void prepare() {
		textures = new AdvancedBufferObject(buf.size() * 6);
		System.err.println("Buf Size: " + buf.size());
		for (Map.Entry<Integer, Vector4f> entry : buf.entrySet()) {
			Integer key = entry.getKey();
			Vector4f val = entry.getValue();
			textures.putVector(new Vector2f(key.intValue(), 1));
			textures.putVector(val);
			System.err.println("BuffData: " + val.toString());
		}

	}

	public void render() {
		Kronos.graphixs.glErrors();
		rs.use();
		Kronos.graphixs.glErrors();
		rs.bindSSBO(t);
		Kronos.graphixs.glErrors();
		rs.bindSSBO(a);
		Kronos.graphixs.glErrors();

		// rs.addUniform("projection",
		// Kronos.graphixs.g2d.getProvider().collectTransform());
		// rs.addUniform("screenSize",
		// new Vector2f(Kronos.graphixs.g2d.getProvider().width(),
		// Kronos.graphixs.g2d.getProvider().height()));
		// rs.addUniform("textureAtlas", 0);
		use.texture.bind();
		Mesh sq = Builtin.screenQuad();

		sq.render();

	}

	public void end() {
		buf.clear();
	}
}
