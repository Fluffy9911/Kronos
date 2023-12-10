package com.kronos.graphixs.rendering;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.kronos.Kronos;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.shaders.ShaderProgram;

public abstract class Render {

	private final int order_id;

	public Render(int order_id, RenderManager rmng) {
		this.order_id = order_id;
	}

	List<Mesh> to_render;

	ShaderProgram draw_program;

	public void create(ShaderProgram sp) {
		draw_program = sp;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Mesh e) {
		return to_render.add(e);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Mesh> c) {
		return to_render.addAll(c);
	}

	public abstract void onInnit(Graphixs g);

	public abstract void cleanup();

	public void render() {
		if (draw_program == null) {
			Kronos.debug.getLogger().fatal("Invalid shader given to renderer {} ", order_id);
			return;
		}

		for (Iterator iterator = to_render.iterator(); iterator.hasNext();) {
			Mesh mesh = (Mesh) iterator.next();
			mesh.render(draw_program);
		}
	}

	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}

}
