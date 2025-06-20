package mudkipboy7.game.level.entity;

import org.lwjgl.glfw.GLFW;

import mudkipboy7.game.level.Level;
import mudkipboy7.game.level.LevelPos;
import mudkipboy7.game.render.Renderers;

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
		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_RIGHT)) {
			this.moveX(0.1F);
			this.facingLeft = false;
		}
		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_LEFT)) {
			this.moveX(-0.1F);
			this.facingLeft = true;
		}
		if (!this.tryDoGravity() && level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_SPACE)) {
			jumpyness = 0.3F;

		} else if (!isAffectedByGravity) {
			if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_UP))
				this.moveY(0.1F);
			if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_DOWN))
				this.moveY(-0.1F);
		}

		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_F))
			this.isAffectedByGravity = false;
		else if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_C))
			this.isAffectedByGravity = true;

		if (ticksSinceLastFire >= 10 && (level.getGameMain().input.queryIsMouseButtonHeld(GLFW.GLFW_MOUSE_BUTTON_1)
				|| level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_F, GLFW.GLFW_KEY_DOWN))) {
			float speedX = 0.3F;
			float speedY = 0;
			float startingPosAdder = this.facingLeft ? -(this.width / 2) : (this.width / 2);
			new ProjectileObject(level,
					LevelPos.copyFrom(levelPos).setZ(this.getZPos() - 0.1F).setX(this.getXPos() + startingPosAdder),
					speedX, speedY, 30, this.facingLeft);
			this.ticksSinceLastFire = 0;
		}
		if (this.cameraTrackPlayer) {
			level.getGameMain().getCamera().copyPosFrom(levelPos, false);
		}

		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_L))
			this.cameraTrackPlayer = false;

		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_U))
			this.cameraTrackPlayer = true;
		if (this.cameraTrackPlayer || level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_C)) {
			level.getGameMain().getCamera().copyPosFrom(levelPos, false);
		}
		if (level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_V))
			entityManager.addEntity(new VictimObject(level, LevelPos.copyFrom(levelPos).setZ(0.05F)));

		if (this.level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_EQUAL))
			level.getGameMain().getCamera().moveZ(-0.01F);

		else if (this.level.getGameMain().input.queryIsKeyHeld(GLFW.GLFW_KEY_MINUS))
			level.getGameMain().getCamera().moveZ(0.01F);

	}
}
