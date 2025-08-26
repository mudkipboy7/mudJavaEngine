package com.mudkipboy7.mudJavaEngine.level.entity;

import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;

public abstract class CreatureEntity extends AbstractEntityObject {
	protected static final float mingravity = -0.3F;
	protected float jumpyness = -0.1F;
	public boolean isAffectedByGravity = true;

	public CreatureEntity(Level level, LevelPos levelPos) {
		super(level, levelPos);
		this.entityManager.addEntity(this);
	}

	public boolean tryDoGravity() {
		if (this.isAffectedByGravity) {
			if (this.moveY(jumpyness)) {
				jumpyness = Math.max(mingravity, jumpyness - 0.02F);
				return true;
			}
		}
		return false;
	}
}
