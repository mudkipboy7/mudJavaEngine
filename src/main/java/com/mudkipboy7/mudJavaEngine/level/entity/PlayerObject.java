package com.mudkipboy7.mudJavaEngine.level.entity;

import org.lwjgl.glfw.GLFW;

import com.mudkipboy7.mudJavaEngine.input.InputKey;
import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.render.Renderers;

public class PlayerObject extends CreatureEntity {
	public boolean cameraTrackPlayer = true;

	public PlayerObject(Level level) {
		super(level, new LevelPos(2, 4, -0.1F));
		this.width = 1.0F;
		this.height = 2.0F;
		this.renderer = Renderers.playerRenderer;
	}

	private int ticksSinceLastFire = 10000000;

	@Override
	public void tick() {
		this.ticksSinceLastFire += 1;
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_RIGHT)) {
			this.moveX(0.1F);
			this.facingLeft = false;
		}
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_LEFT)) {
			this.moveX(-0.1F);
			this.facingLeft = true;
		}
		if (!this.tryDoGravity() && level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_JUMP)) {
			jumpyness = 0.3F;

		}
		if (this.cameraTrackPlayer) {
			level.getGameMain().getCamera().copyPosFrom(levelPos, false);
		}
		if (ticksSinceLastFire >= 10 && (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_FIRE))) {
			float speedX = 0.3F;
			float speedY = 0;
			float startingPosAdder = this.facingLeft ? -(this.width / 2) : (this.width / 2);
			new ProjectileObject(level,
					LevelPos.copyFrom(levelPos).setZ(this.getZPos() - 0.1F).setX(this.getXPos() + startingPosAdder),
					speedX, speedY, 30, this.facingLeft);
			this.ticksSinceLastFire = 0;
		}
		 if (this.level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_ZOOM_OUT))
		  level.getGameMain().getCamera().moveZ(-0.01F);
		  
		  else if (this.level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_ZOOM_IN))
		  level.getGameMain().getCamera().moveZ(0.01F);

	}
}
