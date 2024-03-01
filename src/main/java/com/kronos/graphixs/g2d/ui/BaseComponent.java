package com.kronos.graphixs.g2d.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.components.Drawable;
import com.kronos.graphixs.g2d.ui.components.Persistant;
import com.kronos.graphixs.g2d.ui.transform.DragNDrop;
import com.kronos.graphixs.g2d.ui.transform.KeepInBox;
import com.kronos.graphixs.texture.Texture;
import com.kronos.io.Config;
import com.kronos.io.config.ConfigFile;

public class BaseComponent implements Comp, Drawable, Persistant {

	ConfigFile config;
	ArrayList<UIListener> listeners;
	protected BasePosition bp;
	protected HashMap<String, BaseComponent> children;
	boolean cdren, moveable, update;
	protected boolean hidden, updateListeners = false;
	protected States state;
	protected ComponentHandler ch;
	protected KeepInBox kib = new KeepInBox();
	private BaseComponent parent;
	String id;
	DragNDrop dnd;

	public BaseComponent(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id) {
		super();
		this.bp = bp;
		this.cdren = cdren;
		this.moveable = moveable;
		this.hidden = hidden;
		state = new States();
		listeners = new ArrayList<>();
		children = new HashMap<>();
		createListeners();
		this.id = id;
		dnd = new DragNDrop();

	}

	public void createListeners() {

	}

	@Override
	public ComponentPosition getPosition() {
		// TODO Auto-generated method stub
		return bp;
	}

	@Override
	public void update() {
		updateChildren();
		if (!hidden) {
			render(ch.getBatcher(), ch.getFr(), ch.getG());
		}
		if (update) {
			recalculatePosition();
			update = false;
		}
		if (updateListeners) {
			listeners.forEach((ui) -> {
				ui.listen(this, state);
			});
		}
		if (moveable) {
			dnd.reposition(this.bp.getProvider(), bp, null);
		}

	}

	@Override
	public void recalculatePosition() {
		kib.reposition(this.ch.getSp(), bp, null);
		if (cdren) {
			updateChildren();
		}
	}

	@Override
	public void addChild(String cid, BaseComponent c) {
		this.children.put(cid, c);
		c.parent = this;
	}

	@Override
	public BaseComponent getChild(String cid) {
		// TODO Auto-generated method stub
		return children.get(cid);
	}

	@Override
	public void updateChildren() {
		for (Map.Entry<String, BaseComponent> entry : children.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.update();
			kib.reposition(ch.getSp(), val.bp, null);
		}
	}

	@Override
	public boolean canUpdateChildren() {
		// TODO Auto-generated method stub
		return cdren;
	}

	@Override
	public boolean movable() {
		// TODO Auto-generated method stub
		return moveable;
	}

	@Override
	public boolean hidden() {
		// TODO Auto-generated method stub
		return hidden;
	}

	/**
	 * @param i
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#hasState(int)
	 */
	public boolean hasState(int i) {
		return state.hasState(i);
	}

	/**
	 * @param e
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#add(int)
	 */
	public boolean add(int e) {
		return state.add(e);
	}

	/**
	 * @param i
	 * @return
	 * @see com.kronos.graphixs.g2d.ui.States#remove(int)
	 */
	public boolean remove(int i) {
		return state.remove(i);
	}

	public void move(float f, float g, float h, float i) {
		this.getPosition().pos().translate(f, g);
		this.getPosition().pos().translateSize(h, i);
		for (Map.Entry<String, BaseComponent> entry : children.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			val.getPosition().pos().translate(f, g);
			val.getPosition().pos().translateSize(h, i);
		}

	}

	@Override
	public void onShown() {
		// our stuff

		forEachChild((c, cid) -> {

			c.onShown();
		});

	}

	@Override
	public void onHidden() {
		// our stuff

		// children
		forEachChild((c, cid) -> {
			c.onHidden();
		});

	}

	@Override
	public void onDeletion() {
		// deletion stuff

		// delete children last
		forEachChild((c, cid) -> {
			c.onDeletion();
		});

	}

	@Override
	public void show() {
		hidden = false;
	}

	@Override
	public void onCreation(ComponentHandler ch) {
		this.ch = ch;
		this.register(this, ch);
		config = this.ch.getOrCreatePersistance(id);
		forEachChild((c, k) -> {
			c.onCreation(ch);
		});
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Graphixs2D g) {

		forEachChild((c, h) -> {
			c.render(batch, fr, g);
		});

	}

	public boolean hasParent() {
		return parent != null;
	}

	public void forEachChild(ChildHandler ch) {
		for (Map.Entry<String, BaseComponent> entry : children.entrySet()) {
			String key = entry.getKey();
			BaseComponent val = entry.getValue();
			ch.handle(val, key);
		}
	}

	public void registerListener(UIListener uil) {
		listeners.add(uil);
	}

	@Override
	public void load(ConfigFile file) {
		config = file;
		this.bp.pos.read(file.config, id + "_position");
		this.bp.ap.read(file.config, id + "_anchored_position");
		readWriteDatas(file.config);
	}

	@Override
	public void write(ConfigFile file) {

		this.bp.pos.put(file.config, id + "_position");
		this.bp.ap.put(file.config, id + "_anchored_position");
		readWriteDatas(file.config);
	}

	@Override
	public String id() {
		// TODO Auto-generated method stub
		return id;
	}

	public void readWriteDatas(Config c) {
		cdren = c.readOrWriteBoolean("update_children", cdren);
		hidden = c.readOrWriteBoolean("hidden", hidden);
		moveable = c.readOrWriteBoolean("moveable", moveable);
		update = c.readOrWriteBoolean("force_update", update);
	}

	public void drawHere(TextureBatch batch, Texture t) {
		batch.drawTexture((int) this.bp.pos().getX(), (int) this.bp.pos().getY(), (int) this.bp.pos().getW(),
				(int) this.bp.pos().getH(), t);
	}

	public void setUpdateListeners(boolean updateListeners) {
		this.updateListeners = updateListeners;
	}

}
