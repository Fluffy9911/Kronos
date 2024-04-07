/**
 * 
 */
package com.kronos.gameFramework.f2d;

import java.util.ArrayList;
import java.util.HashMap;

import org.joml.Vector2f;

import com.kronos.graphixs.color.Colors;
import com.kronos.io.Config;

/**
 * 
 */
public class GameObject implements Cloneable {
	ArrayList<String> tags = new ArrayList<>();
	HashMap<String, ObjectComponent> components = new HashMap<>();
	Config config;
	protected Position2D p2d;
	int id = -1;
	static int ii = 0;
	protected Vector2f pos = new Vector2f();

	public GameObject() {
		id = ii;
		ii++;

	}

	public <T extends ObjectComponent> T getComponent(String tag) {
		return (T) components.get(tag);
	}

	public boolean hasTag(String tag) {
		return tags.contains(tag);
	}

	public void appendTag(String tag) {
		tags.add(tag);
	}

	public void appendComponent(String si, ObjectComponent c) {
		components.put(si, c);
	}

	public void onRead(Config c) {

	}

	public void onWrite(Config c) {

	}

	public void onCreation(Position2D pd) {
		this.p2d = pd;

	}

	public RenderProvider getProvider() {
		return RenderProvider.createDEF(Colors.Green);
	}

	@Override
	public GameObject clone() {
		try {
			return (GameObject) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean hardCollision() {
		return true;
	}

	public boolean collision() {
		return true;
	}

	public boolean isSolid() {
		return false;
	}

	/**
	 * @return the tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * @return the components
	 */
	public HashMap<String, ObjectComponent> getComponents() {
		return components;
	}

	/**
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * @return the p2d
	 */
	public Position2D getP2d() {
		return p2d;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void onCollision(CollisionType ct, GameObject go) {
		if (ct == CollisionType.HARD) {

		}
	}

	public void update(Position2D p) {
		// System.out.println(p2d.getX());
	}
}
