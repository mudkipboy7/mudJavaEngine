package com.mudkipboy7.mudJavaEngine.level.entity;

import java.util.ArrayList;

import com.mudkipboy7.mudJavaEngine.level.Level;

public class EntityManager {

	public Level level;
	// private ArrayList<AbstractEntityObject> loadedProjectiles = new
	// ArrayList<AbstractEntityObject>();
	private ArrayList<AbstractEntityObject> loadedEntities = new ArrayList<AbstractEntityObject>();

	public EntityManager(Level level) {
		this.level = level;
	}

	@SuppressWarnings("unchecked")
	public void physicsTickEntities() {
		// System.out.println( getLoadedEntities());
		// Goes over each entity and does stuff to them.
		((ArrayList<AbstractEntityObject>) loadedEntities.clone()).forEach(entity -> entity.tick());

	}

	public ArrayList<AbstractEntityObject> getLoadedEntities() {

		return loadedEntities;
	}

	public void addEntity(AbstractEntityObject entity) {
		loadedEntities.add(entity);
	}

	public void deleteEntity(AbstractEntityObject entity) {
		if (entity instanceof VictimObject) {
			this.level.getGameMain().enemiesKilled += 1;
		}
		loadedEntities.remove(entity);
	}
}
