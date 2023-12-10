package com.kronos.graphixs.rendering;

import java.util.HashMap;
import java.util.Map;

import com.kronos.Kronos;
import com.kronos.graphixs.display.Graphixs;
import com.kronos.graphixs.geometry.Mesh;
import com.kronos.graphixs.shaders.ShaderProgram;

public class RenderManager {
	HashMap<String, Render> layers;
	private int i = 0;
	Render[] order;
	Graphixs g;

	public RenderManager(Graphixs g) {
		this.g = g;
		layers = new HashMap<>();
	}

	public void createRender(String id, ShaderProgram sp) {
		Render r = new Render(i, this) {

			@Override
			public void onInnit(Graphixs g) {
				Kronos.debug.getLogger().debug("Created render for order: {} ", this.getOrder_id());
			}

			@Override
			public void cleanup() {

			}

		};
		r.create(sp);
		i++;
		layers.put(id, r);
	}

	public void createRender(String id, String sp) {
		Render r = new Render(i, this) {

			@Override
			public void onInnit(Graphixs g) {
				Kronos.debug.getLogger().debug("Created render for order: {} ", this.getOrder_id());
			}

			@Override
			public void cleanup() {

			}

		};
		i++;
		r.create(g.getShader(id));
		layers.put(id, r);
	}

	public void addMesh(String r, Mesh m) {
		Render re = layers.get(r);
		if (re != null) {
			re.add(m);
		}
	}

	public boolean shouldSort() {
		if (order == null)
			return true;

		return order.length == layers.size();
	}

	public void renderLayers() {
		if (shouldSort())
			sort();

		for (int i = order.length; i > 0; i--) {
			order[i].render();
		}

	}

	private void sort() {
		order = new Render[layers.size()];

		for (Map.Entry<String, Render> entry : layers.entrySet()) {
			String key = entry.getKey();
			Kronos.debug.getLogger().debug("Sorted render: {} ", key);
			Render val = entry.getValue();
			order[val.getOrder_id()] = val;
		}

	}

}
