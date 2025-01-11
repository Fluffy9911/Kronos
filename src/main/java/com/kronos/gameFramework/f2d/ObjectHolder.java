/**
 * 
 */
package com.kronos.gameFramework.f2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.io.InputHandler;

/**
 * 
 */
public class ObjectHolder {
	HashMap<String, GameObject> objects = new HashMap<>();
	ArrayList<ObjectRenderer> update, remove, add;
	public HashMap<String, ObjectRenderer> ss = new HashMap<String, ObjectRenderer>();
	public long phys = 0;

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
		ss.put(id, new ObjectRenderer(pd, objects.get(id), pd.trans));
	}

	public void addAll() {
		update.addAll(add);
		add.clear();
	}

	public void removeAll() {
		update.removeAll(remove);
		remove.clear();
	}

	public void renderObjects(TextureBatch tb) {

		for (Map.Entry<String, ObjectRenderer> entry : ss.entrySet()) {
			String key = entry.getKey();
			ObjectRenderer or = entry.getValue();
			or.go.update(or.position);
			or.renderAt(tb);
			if (or.go.getProvider().getSelectionTexture() != null
					&& or.position.toRect().contains(InputHandler.getLastMouseX(), InputHandler.getLastMouseY())) {
				or.renderAt(tb, or.go.getProvider().getSelectionTexture());
			}
			checkMovement(or);
		}

		for (Iterator iterator = update.iterator(); iterator.hasNext();) {
			ObjectRenderer or = (ObjectRenderer) iterator.next();
			or.go.update(or.position);
			or.renderAt(tb);
			if (or.go.getProvider().getSelectionTexture() != null
					&& or.position.toRect().contains(InputHandler.getLastMouseX(), InputHandler.getLastMouseY())) {
				or.renderAt(tb, or.go.getProvider().getSelectionTexture());
			}
		}
		resolveCollisions();
		addAll();
		removeAll();
	}

	public void resolveCollisions() {
		long ms = System.currentTimeMillis();
		for (Iterator iterator = update.iterator(); iterator.hasNext();) {
			ObjectRenderer o = (ObjectRenderer) iterator.next();
			checkMovement(o);
		}
		phys = (System.currentTimeMillis() - ms);
	}

	public void checkMovement(ObjectRenderer obj) {
		for (Iterator iterator = update.iterator(); iterator.hasNext();) {
			ObjectRenderer g = (ObjectRenderer) iterator.next();
			if (g == obj) {
				continue; // Skip checking collision with itself
			} else {
				Position2D tb = obj.position;
				Position2D ob = g.position;

				// Check if objects can move to each other's positions

				// Check for hard collisions

				if (g.go.hardCollision() && obj.go.hardCollision() && tb.intersects(ob)) {
					g.go.onCollision(CollisionType.HARD, obj.go);
					obj.go.onCollision(CollisionType.HARD, g.go);

					if (tb.canMove(ob)) {
						tb.moveToTarget();
					} else {
						if (g.go.isSolid()) {
							tb.resolveCollisionSolid(ob);
						} else {
							tb.resolveCollisionsNonSolid(ob);
						}
					}
				} else if (g.go.collision() && tb.intersects(ob)) { // Check for soft collisions
					g.go.onCollision(CollisionType.SOFT, obj.go);
					obj.go.onCollision(CollisionType.SOFT, g.go);
					tb.moveToTarget();
				} else {
					tb.moveToTarget();
				}
			}
		}
	}

}
