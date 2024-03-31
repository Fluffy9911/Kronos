/**
 * 
 */
package com.kronos.gameFramework.f2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.joml.Vector2f;

import com.kronos.graphixs.g2d.TextureBatch;

/**
 * 
 */
public class ObjectHolder {
	HashMap<String, GameObject> objects = new HashMap<>();
	ArrayList<ObjectRenderer> update, remove, add;

	/**
	 * @return the objects
	 */
	public HashMap<String, GameObject> getObjects() {
		return objects;
	}

	/**
	 * @return the update
	 */
	public ArrayList<ObjectRenderer> getUpdate() {
		return update;
	}

	/**
	 * @return the remove
	 */
	public ArrayList<ObjectRenderer> getRemove() {
		return remove;
	}

	/**
	 * @return the add
	 */
	public ArrayList<ObjectRenderer> getAdd() {
		return add;
	}

	public ObjectHolder() {
		update = new ArrayList<>();
		remove = new ArrayList<>();
		add = new ArrayList<>();
	}

	public void addObject(String id, Position2D pd) {
		add.add(new ObjectRenderer(pd, objects.get(id)));
	}

	public void addAll() {
		update.addAll(add);
	}

	public void removeAll() {
		update.removeAll(remove);
	}

	public void renderObjects(TextureBatch tb) {
		resolveCollisions();
		for (Iterator iterator = update.iterator(); iterator.hasNext();) {
			ObjectRenderer or = (ObjectRenderer) iterator.next();
			or.go.update();
			or.renderAt(tb);

		}
		addAll();
		removeAll();
	}

	public void resolveCollisions() {
		for (Iterator iterator = update.iterator(); iterator.hasNext();) {
			ObjectRenderer or = (ObjectRenderer) iterator.next();
//			if (!or.go.collision() && !or.go.hardCollision()) {
//				continue;
//			}
			for (Iterator iterator2 = update.iterator(); iterator2.hasNext();) {
				ObjectRenderer orr = (ObjectRenderer) iterator2.next();
//				if (!orr.go.collision() && !orr.go.hardCollision()) {
//					continue;
//				}

				if (or.getId() != orr.getId()) {
					GameObject g = or.go;
					GameObject g2 = orr.go;
					System.out.println("here");
					g.p2d.applyForce(g.pos);
					g2.p2d.applyForce(g2.pos);
					g.pos = new Vector2f();
					g2.pos = new Vector2f();

					if (g.hardCollision() && g.p2d.intersects(g2.getP2d())) {
						g.onCollision(CollisionType.HARD, g2);
						g2.onCollision(CollisionType.HARD, g);
						System.out.println("HARD");
						g.p2d.resolveCollision(g2.getP2d());

					} else if (g.collision() && g.p2d.intersects(g2.getP2d())) {
						g.onCollision(CollisionType.SOFT, g2);
						g2.onCollision(CollisionType.SOFT, g);

					}

				}
			}
		}
	}

}
