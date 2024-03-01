/**
 * 
 */
package com.kronos.fun.RT;

import java.util.Random;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.opengl.GL43C;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.g.Graphixs;
import com.kronos.graphixs.shaders.compute.ComputeShader;
import com.kronos.graphixs.shaders.utils.Struct;
import com.kronos.graphixs.texture.Texture;

/**
 * 
 */
public class RTCompute extends ComputeShader {
	Texture write;
	int width;
	int height;

	public double aspectRatio = 1.0;
	public int imageWidth;
	public double defocus_angle = 10;
	private int imageHeight;
	private Vector3f center, look, up, u, v, w, du, dv;
	private Vector3f pixel00Loc;
	private Vector3f pixelDeltaU;
	private Vector3f pixelDeltaV;
	private Vector2i ssbo;
	int spp = 50;
	int md = 10;
	float vfov = 90;

	Struct bo;

	public RTCompute(String s, Vector3i size, int width, int height) {
		super(s, size);
		this.width = width;
		this.height = height;
		bo = new Struct("sceneInfo");
		write = Texture.singleColor(width, height, Colors.White);
		center = new Vector3f(0, 0, 0);
		up = new Vector3f(0, 1, 0);
		look = new Vector3f(0, 0, 0);

	}

	public void upload() {
		initialize();
		bo.putFloat("aspect", (float) aspectRatio);
		bo.putFloat("iW", imageWidth);
		bo.putFloat("iH", imageHeight);
		bo.putFloat("defocus", (float) defocus_angle);
		bo.putFloat("spp", spp);
		bo.putFloat("maxDepth", md);
		bo.putFloat("vfov", vfov);

		bo.putVector("center", center);
		bo.putVector("look", look);
		bo.putVector("up", up);
		bo.putVector("u", u);
		bo.putVector("v", v);
		bo.putVector("w", w);
		bo.putVector("du", du);
		bo.putVector("dv", dv);
		bo.putVector("ploc", pixel00Loc);
		bo.putVector("pdu", pixelDeltaU);
		bo.putVector("pdv", pixelDeltaV);
		Random r = new Random();
		// this.addUniform("seed", r.nextInt());
	}

	public void render() {
		Graphixs g = Kronos.graphixs;
		upload();
		g.glErrors();

		g.glErrors();
		write.bindImage();
		g.glErrors();
		System.out.println("bound");
		g.glErrors();

		g.glErrors();
		System.out.println("rendered");
		Texture t = waitUntilFinished();
		g.glErrors();
		t.bind();
		g.glErrors();
		System.out.println("out");
		Kronos.graphixs.writeTextureOut("test_rt");
		g.glErrors();
	}

	public Texture waitUntilFinished() {
		GL43C.glMemoryBarrier(GL43C.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);
		return write;
	}

	private void initialize() {
//		imageHeight = (int) (imageWidth / aspectRatio);
//		imageHeight = (imageHeight < 1) ? 1 : imageHeight;

		// Determine viewport dimensions.

//		double viewportHeight = 2.0;
		defocus_angle = 0; // Variation angle of rays through each pixel
		double focus_dist = 3.4; // Distance from camera lookfrom point to plane of perfect focus
		double theta = Math.toRadians(vfov);
		double h = Math.tan(theta / 2);
		double viewportHeight = 2 * h * focus_dist;
		double viewportWidth = viewportHeight * (imageWidth / (double) imageHeight);
		// Calculate the vectors across the horizontal and down the vertical viewport

		w = new Vector3f(center).sub(look).normalize();
		u = new Vector3f(up).cross(w).normalize();
		v = new Vector3f(w).cross(u);
		Vector3f viewportU = new Vector3f(u).mul((float) viewportWidth); // Vector across viewport horizontal edge
		Vector3f viewportV = new Vector3f(v).mul((float) -viewportHeight); // Vector down viewport vertical edge

		// Calculate the horizontal and vertical delta vectors from pixel to pixel.
		pixelDeltaU = new Vector3f(viewportU).div(imageWidth);
		pixelDeltaV = new Vector3f(viewportV).div(imageHeight);
		Vector3f viewportUpperLeft = new Vector3f(center).sub(new Vector3f(w).mul((float) focus_dist))
				.sub(new Vector3f(viewportU).div(2)).sub(new Vector3f(viewportV).div(2));

		// Calculate the location of the upper left pixel.

		pixel00Loc = new Vector3f(viewportUpperLeft).add(new Vector3f(pixelDeltaU).add(pixelDeltaV).mul(0.5f));

		float defocusRadius = (float) (focus_dist * Math.tan(Math.toRadians(defocus_angle / 2)));
		du = new Vector3f(u).mul(defocusRadius);
		dv = new Vector3f(v).mul(defocusRadius);

//		System.exit(0);
	}
}
