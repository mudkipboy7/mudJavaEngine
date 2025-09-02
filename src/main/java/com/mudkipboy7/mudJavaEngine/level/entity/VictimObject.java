package com.mudkipboy7.mudJavaEngine.level.entity;

import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.render.Renderers;

public class VictimObject extends CreatureEntity {

	public VictimObject(Level level, LevelPos levelPos) {
		super(level, levelPos);
		this.width = 1.0F;
		this.height = 2.0F;
		this.renderer = Renderers.victimRenderer;
	}

	@Override
	public void tick() {
		this.tryDoGravity();
		//float moveAmmount = this.facingLeft ? -0.02F : 0.02F;
		//if (!this.moveX(moveAmmount)) {
			//this.facingLeft = !facingLeft;
		//}
		//if (this.getYPos() < -1) {
		//	this.getEntityManager().deleteEntity(this);
		//}

	}
}
