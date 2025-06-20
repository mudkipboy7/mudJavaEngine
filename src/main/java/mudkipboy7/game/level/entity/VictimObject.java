package mudkipboy7.game.level.entity;

import mudkipboy7.game.level.Level;
import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.render.Renderers;

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
		float moveAmmount = this.facingLeft ? -0.02F : 0.02F;
		if (!this.moveX(moveAmmount)) {
			this.facingLeft = !facingLeft;
		}
		if (this.getYPos() < -1) {
			this.getEntityManager().deleteEntity(this);
		}

	}
}
