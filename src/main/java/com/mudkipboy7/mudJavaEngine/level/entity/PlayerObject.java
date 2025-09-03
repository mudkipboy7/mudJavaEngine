package com.mudkipboy7.mudJavaEngine.level.entity;

import org.lwjgl.glfw.GLFW;

import com.mudkipboy7.mudJavaEngine.input.InputKey;
import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.LevelPos;
import com.mudkipboy7.mudJavaEngine.level.physics.Direction;
import com.mudkipboy7.mudJavaEngine.render.Renderers;

public class PlayerObject extends CreatureEntity {
	public boolean cameraTrackPlayer = true;
	private int runFrame = 0;
	private int animationTimer = 0;

	public PlayerObject(Level level) {
		super(level, new LevelPos(50, 50, -0.1F));
		this.width = 1.0F;
		this.height = 1.0F;
		this.renderer = Renderers.playerRenderer;
	}


	@Override
	public void tick() {
		boolean isRunning = level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_FIRE);
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_DOWN)) {
			this.moveY(isRunning ? -0.2F : -0.1F);
			this.direction = Direction.Down;
		}
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_UP)) {

			this.moveY(isRunning ? 0.2F : 0.1F);
			this.direction = Direction.Up;

		}
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_RIGHT)) {
			this.moveX(isRunning ? 0.2F : 0.1F);
			this.direction = Direction.Right;

		}
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_LEFT)) {
			this.moveX(isRunning ? -0.2F : -0.1F);
			this.direction = Direction.Left;
		}

		//if (!this.tryDoGravity() && level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_JUMP)) {
		//	jumpyness = 0.3F;

		//}
		if (this.cameraTrackPlayer) {
			level.getGameMain().getCamera().copyPosFrom(levelPos, false);
		}
		// if (ticksSinceLastFire >= 10 &&
		// (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_FIRE))) {
		// float speedX = 0.3F;
		// float speedY = 0;
		// float startingPosAdder = this.facingLeft ? -(this.width / 2) : (this.width /
		// 2);
		// new ProjectileObject(level,
		// LevelPos.copyFrom(levelPos).setZ(this.getZPos() - 0.1F).setX(this.getXPos() +
		// startingPosAdder),
		// speedX, speedY, 30, this.facingLeft);
		// this.ticksSinceLastFire = 0;
		// }
		if (this.level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_ZOOM_OUT))
			level.getGameMain().getCamera().moveZ(-0.01F);

		else if (this.level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_ZOOM_IN))
			level.getGameMain().getCamera().moveZ(0.01F);

	}

	@Override
	public int getAnimationFrame() {
		boolean isRunning = level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_FIRE);
		switch (this.direction) {
		case Down:
			return getRun(1, isRunning);
		case Up:
			return getRun(7, isRunning);
		case Left:
			return getRun(13, isRunning);
		case Right:
			return getRun(19, isRunning);
		}
		return 1;
	}

	private int getRun(int nonMove, boolean isRunning) {

		animationTimer++;
		if (animationTimer >= 15) {
			this.runFrame += 1;
			if (this.runFrame == 3) {
				this.runFrame = -1;
			}
			animationTimer = 0;
		}
		if (level.getGameMain().input.queryIsInputKeyPressed(InputKey.KEY_LEFT, InputKey.KEY_RIGHT, InputKey.KEY_DOWN,
				InputKey.KEY_UP)) {
			if (isRunning) {
				this.animationTimer += 1;
				nonMove += 3;
			}

			switch (runFrame) {
			case 0:
				return nonMove - 1;
			case 1:
				return nonMove;
			case 2:
				return nonMove + 1;
			}
		}
		return nonMove;

	}

}
