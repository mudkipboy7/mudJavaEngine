package com.mudkipboy7.mudJavaEngine.level.entity;

import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.render.Renderers;

public class DeadVictimObject extends CreatureEntity {
	public DeadVictimObject(Level level, LevelPos levelPos, boolean facingLeft) {
		super(level, levelPos);
		this.renderer = Renderers.deadVictimRenderer;
		this.height = 1F;
		this.width = 2F;
		this.animationFrame = 0;
		this.facingLeft = facingLeft;
	}

	@Override
	public void tick() {
		tryDoGravity();
	}

}
