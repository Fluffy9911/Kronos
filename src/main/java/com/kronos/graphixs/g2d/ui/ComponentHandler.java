package com.kronos.graphixs.g2d.ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kronos.graphixs.g2d.Abstract2DGraphixs;
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
	String default_pane = "default", current_pane = default_pane;
	Abstract2DGraphixs g;
	TextureBatch batcher;
	FontRenderer fr;
	ScreenProvider sp;
	HashMap<String, HashMap<String, BaseComponent>> comps;
	HashMap<String, BaseComponent> active = new HashMap<String, BaseComponent>();

	public ComponentHandler(Abstract2DGraphixs g) {
		configs = new HashMap<String, ConfigFile>();
		comps = new HashMap<String, HashMap<String, BaseComponent>>();

		this.g = g;
		batcher = g.createBatch(this.g);
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
		HashMap<String, BaseComponent> comps = this.comps.get(default_pane); // <String, BaseComponent>
		return comps.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public BaseComponent put(String key, BaseComponent value) {
		HashMap<String, BaseComponent> comps = this.comps.get(default_pane);
		if (comps == null) {
			this.comps.put(default_pane, comps = new HashMap<String, BaseComponent>());
		}
		return comps.put(key, value);
	}

	public void showComponent(String id) {
		BaseComponent bc = get(id);
		if (bc == null)
			return;
		bc.onShown();
		bc.show();
	}

	/**
	 * creates all the components regardless of there ID
	 */
	public void createComps() {
		for (Map.Entry<String, HashMap<String, BaseComponent>> ent : comps.entrySet()) {
			HashMap<String, BaseComponent> c = ent.getValue();
			for (Map.Entry<String, BaseComponent> entry : c.entrySet()) {
				String key = entry.getKey();
				BaseComponent val = entry.getValue();
				val.onCreation(this);
			}
		}
	}

	public void deleteAll() {
		for (Map.Entry<String, HashMap<String, BaseComponent>> ent : comps.entrySet()) {
			HashMap<String, BaseComponent> c = ent.getValue();
			for (Map.Entry<String, BaseComponent> entry : c.entrySet()) {
				String key = entry.getKey();
				BaseComponent val = entry.getValue();
				val.onDeletion();
			}
		}
	}

	public void update() {
		for (Map.Entry<String, HashMap<String, BaseComponent>> ent : comps.entrySet()) {
			HashMap<String, BaseComponent> c = ent.getValue();
			for (Map.Entry<String, BaseComponent> entry : c.entrySet()) {

				String key = entry.getKey();
				BaseComponent val = entry.getValue();
				val.update();
				if (val.canUpdateChildren()) {
					val.updateChildren();
				}

				val.render(batcher, fr, g);

			}
		}
		batcher.render();
		batcher.end();
	}

	public Abstract2DGraphixs getG() {
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

	public void setActive(String id) {
		this.active = this.comps.get(id);
		this.current_pane = id;
	}

	public void update(String id) {
		HashMap<String, BaseComponent> c = this.comps.get(id);
		for (Map.Entry<String, BaseComponent> entry : c.entrySet()) {

			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.update();
			if (val.canUpdateChildren()) {
				val.updateChildren();
			}

			val.render(batcher, fr, g);

		}
	}

	public void create(String id) {
		HashMap<String, BaseComponent> c = this.comps.get(id);
		for (Map.Entry<String, BaseComponent> entry : c.entrySet()) {

			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.onCreation(this);

		}
	}

	public void updateShown() {
		for (Map.Entry<String, BaseComponent> entry : active.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.update();
			if (val.canUpdateChildren()) {
				val.updateChildren();
			}

			val.render(batcher, fr, g);
		}
	}

	public void saveDataForInactiveComponents() {
		for (Map.Entry<String, HashMap<String, BaseComponent>> entry : comps.entrySet()) {
			HashMap<String, BaseComponent> components = entry.getValue();
			for (Map.Entry<String, BaseComponent> componentEntry : components.entrySet()) {
				String key = componentEntry.getKey();
				BaseComponent component = componentEntry.getValue();

				// Check if the component is not in the active pane
				if (!active.containsValue(component)) {
					// Save the data for the component
					component.write(getOrCreatePersistance(current_pane + "_" + key));
				}
			}
		}
	}

	public void saveData() {
		for (Map.Entry<String, HashMap<String, BaseComponent>> entry : comps.entrySet()) {
			HashMap<String, BaseComponent> components = entry.getValue();
			for (Map.Entry<String, BaseComponent> componentEntry : components.entrySet()) {
				BaseComponent component = componentEntry.getValue();
				if (isOffscreen(component)) {
					component.write(getOrCreatePersistance(current_pane + "_" + componentEntry.getKey()));
				}
			}
		}
	}

	private boolean isOffscreen(BaseComponent component) {
		Rectangle screen = new Rectangle(0, 0, g.getProvider().width(), g.getProvider().height());
		Rectangle bounds = component.bp.pos.getBounds();
		return !screen.intersects(bounds); // Placeholder return, implement your offscreen check logic
	}

	public void renderComponents(boolean updateNotSeen) {
		for (Map.Entry<String, HashMap<String, BaseComponent>> entry : comps.entrySet()) {
			HashMap<String, BaseComponent> components = entry.getValue();
			for (Map.Entry<String, BaseComponent> componentEntry : components.entrySet()) {
				BaseComponent component = componentEntry.getValue();
				if (isOffscreen(component) && updateNotSeen) {
					component.update();
					if (component.canUpdateChildren()) {
						component.updateChildren();
					}

				} else {
					// do nothing
				}
			}
		}
	}
}
