package com.kronos.graphixs.g2d.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.components.Persistant;
import com.kronos.io.ResourceIdentifier;
import com.kronos.io.config.ConfigFile;

public class ComponentHandler {
	ResourceIdentifier base = new ResourceIdentifier() {

		@Override
		public String getNameid() {
			// TODO Auto-generated method stub
			return "kronos.components.persistance";
		}

		@Override
		public String getBasePath() {
			// TODO Auto-generated method stub
			return "kronos/out/UI/components";
		}
	};
	String path = "persistance";

	HashMap<String, ConfigFile> configs;
	ArrayList<Persistant> persistant = new ArrayList<Persistant>();
	HashMap<String, BaseComponent> comps;
	Graphixs2D g;
	TextureBatch batcher;
	FontRenderer fr;
	ScreenProvider sp;

	public ComponentHandler(Graphixs2D g) {
		configs = new HashMap<String, ConfigFile>();
		comps = new HashMap<String, BaseComponent>();

		this.g = g;
		batcher = g.createBatch();
		fr = FontRenderer.createDefault();

	}

	public ConfigFile getOrCreatePersistance(String id) {
		ConfigFile f = configs.get(id);
		if (f == null) {
			f = new ConfigFile(id, path, base);
			configs.put(id, f);
			return f;
		}
		return f;
	}

	public void load() {
		for (Map.Entry<String, ConfigFile> entry : configs.entrySet()) {
			String key = entry.getKey();
			ConfigFile val = entry.getValue();
			val.load();
		}
		for (Iterator iterator = persistant.iterator(); iterator.hasNext();) {
			Persistant p = (Persistant) iterator.next();
			p.load(getOrCreatePersistance(p.id()));
		}
	}

	public void write() {

		for (Iterator iterator = persistant.iterator(); iterator.hasNext();) {
			Persistant p = (Persistant) iterator.next();
			p.write(getOrCreatePersistance(p.id()));
		}

		for (Map.Entry<String, ConfigFile> entry : configs.entrySet()) {
			String key = entry.getKey();
			ConfigFile val = entry.getValue();
			val.write();
		}
	}

	public void register(String id, Persistant p) {
		persistant.add(p);
		getOrCreatePersistance(id);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public BaseComponent get(Object key) {
		return comps.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public BaseComponent put(String key, BaseComponent value) {
		return comps.put(key, value);
	}

	public void showComponent(String id) {
		BaseComponent bc = get(id);
		if (bc == null)
			return;
		bc.onShown();
		bc.show();
	}

	public void createComps() {
		for (Map.Entry<String, BaseComponent> entry : comps.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.onCreation(this);
		}
	}

	public void deleteAll() {
		for (Map.Entry<String, BaseComponent> entry : comps.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.onDeletion();
		}
	}

	public void update() {

		for (Map.Entry<String, BaseComponent> entry : comps.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.update();
			if (val.canUpdateChildren()) {
				val.updateChildren();
			}
			val.render(batcher, fr, g);
		}
		batcher.render();
		batcher.end();
	}

	public Graphixs2D getG() {
		return g;
	}

	public TextureBatch getBatcher() {
		return batcher;
	}

	public FontRenderer getFr() {
		return fr;
	}

	public ScreenProvider getSp() {
		return sp;
	}

}
