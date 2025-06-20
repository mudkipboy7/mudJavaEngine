package mudkipboy7.game.level.entity;

import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.level.Level;
import mudkipboy7.game.render.Renderers;

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
