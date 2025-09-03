package com.mudkipboy7.mudJavaEngine.level.entity;

import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.physics.Direction;
import com.mudkipboy7.mudJavaEngine.level.tile.CollidableTile;
import com.mudkipboy7.mudJavaEngine.level.tile.Tile;
import com.mudkipboy7.mudJavaEngine.level.tile.TilePos;
import com.mudkipboy7.mudJavaEngine.render.Renderers;
import com.mudkipboy7.mudJavaEngine.render.renderers.EntityRenderer;

public abstract class AbstractEntityObject {
	protected EntityManager entityManager;
	protected Level level;
	public LevelPos levelPos;
	protected EntityRenderer renderer = Renderers.defaultEntityRenderer;
	protected float width = 1.0F;
	protected float height = 1.0F;
	public Direction direction = Direction.Down;
	public int animationFrame = 0;
	public boolean isRunning = false;
	public int movementLeftInCurrent = 0;
	public float currentMovementSpeed = 0; // how many ticks it will take to traverse the current movement

	public AbstractEntityObject(Level level, LevelPos levelPos) {
		this.level = level;
		if (levelPos != null)
			this.levelPos = levelPos;
		this.entityManager = level.getEntityManager();
	}

	@SuppressWarnings("unused")
	public boolean moveX(float ammount) {

		levelPos.moveX(ammount);
		return true;
	}

	@SuppressWarnings("unused")
	public boolean moveY(float ammount) {
		levelPos.moveY(ammount);
		return true;
	}

	public boolean moveZ(float ammount) {
		levelPos.moveZ(ammount);
		return true;
	}

	public abstract void tick();

	public float getXPos() {
		return this.levelPos.x;
	}

	public float getYPos() {
		return this.levelPos.y;
	}

	public float getZPos() {
		return this.levelPos.z;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Level getLevel() {
		return level;
	}

	public EntityRenderer getRenderer() {
		return renderer;
	}

	public int getAnimationFrame() {
		return animationFrame;
	}

	public boolean getShouldBeMirroredX() {
		return false;

	}

	public boolean getShouldBeMirroredY() {
		return false;
	}

	public boolean queueMovement(Direction direction, boolean isRunning) {
		//if(movementLeftInCurrent )
		return false;
	}

	public boolean Move() {
		return false;
	//	if(this.movementLeftInCurrent != 0) {
		//this.movementLeftInCurrent -=currentMovementSpeed;
		//this.currentMovementSpeed 
	}
}
