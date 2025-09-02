package com.mudkipboy7.mudJavaEngine.level.entity;

import java.util.ArrayList;

import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.physics.Direction;
import com.mudkipboy7.mudJavaEngine.render.Renderers;

public class ProjectileObject extends AbstractEntityObject {
	protected float speedX = 0;
	protected float speedY = 0;
	// protected static final float maxSpeedY = maxSpeedX;
	private Integer lifeSpanLeft = null;

	public ProjectileObject(Level level, LevelPos levelPos, float speedX, float speedY, Integer lifeSpan,
			boolean isFacingLeft) {
		super(level, levelPos);
		this.speedX = speedX;
		this.speedY = speedY;
		this.width = 0.01F;
		this.height = 0.01F;

		this.lifeSpanLeft = lifeSpan;
		entityManager.addEntity(this);
		this.renderer = Renderers.bulletRenderer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void tick() {
		((ArrayList<AbstractEntityObject>) entityManager.getLoadedEntities().clone()).forEach(entity -> {
			if (entity instanceof VictimObject && this.lifeSpanLeft > 0) {
				VictimObject victim = (VictimObject) entity;
				float width = victim.width;
				float height = victim.height;
				if (getXPos() > victim.getXPos() - width / 2 && getXPos() < victim.getXPos() + width / 2
						&& getYPos() > victim.getYPos() - height / 2 && getYPos() < victim.getYPos() + height / 2) {
					///System.out.println("Victim killed!");
					entityManager.deleteEntity(victim);
					entityManager.addEntity(new DeadVictimObject(level, victim.levelPos, victim.direction == Direction.Left));

					this.lifeSpanLeft = 0;
				}
			}
		});
		// gameMain.getRendererManager().projectiles.tryAddToList(this);
		if (direction == Direction.Left) {
			if (!this.moveX(-speedX))
				entityManager.deleteEntity(this);
		} else if (!this.moveX(speedX))
			entityManager.deleteEntity(this);
		this.moveY(speedY);
		if (lifeSpanLeft != null) {
			lifeSpanLeft -= 1;
			if (lifeSpanLeft <= 0)
				entityManager.deleteEntity(this);
		}
	}
}
