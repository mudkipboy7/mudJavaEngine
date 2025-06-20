package mudkipboy7.game.level.physics;

import mudkipboy7.game.level.AbstractPosTracker;
import mudkipboy7.game.level.LevelPos;

public class Hitbox {
	private float width = 1F;
	private float height = 1F;

	public Hitbox(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	private float getRightX(AbstractPosTracker<?> pos) {
		return pos.x.floatValue() + (this.width / 2.0F);
	}

	private float getLeftX(AbstractPosTracker<?> pos) {
		return pos.x.floatValue() - (this.width / 2.0F);
	}

	private float getTopY(AbstractPosTracker<?> pos) {
		return pos.y.floatValue() + (this.height / 2.0F);
	}

	private float getBottomY(AbstractPosTracker<?> pos) {
		return pos.y.floatValue() - (this.height / 2.0F);
	}

	public LevelPos getTopLeft(AbstractPosTracker<?> pos) {
		return new LevelPos(getLeftX(pos), getTopY(pos));
	}

	public LevelPos getTopRight(AbstractPosTracker<?> pos) {
		return new LevelPos(getRightX(pos), getTopY(pos));
	}

	public LevelPos getBottomLeft(AbstractPosTracker<?> pos) {
		return new LevelPos(getLeftX(pos), getBottomY(pos));
	}

	public LevelPos getBottomRight(AbstractPosTracker<?> pos) {
		return new LevelPos(getRightX(pos), getBottomY(pos));
	}

	/**
	 * 
	 * @param pointCoords
	 * @param hitboxCoords
	 * @return Its pos reletive to the center of the hitbox, 0 if it isn't inside at
	 *         all
	 */
	public float getPointInsideX(AbstractPosTracker<?> pointCoords, AbstractPosTracker<?> hitboxCoords) {
		float boxX = hitboxCoords.getX().floatValue();
		float boxRightX = this.getRightX(hitboxCoords);
		float boxLeftX = this.getLeftX(hitboxCoords);
		float pointX = pointCoords.getX().floatValue();
		if (pointX >= boxRightX && pointX <= boxLeftX) {
			return boxX - pointX;
		}
		return 0;

	}

	/**
	 * 
	 * @param pointCoords
	 * @param hitboxCoords
	 * @return Its pos reletive to the center of the hitbox, 0 if it isn't inside at
	 *         all
	 */
	public float getPointInsideY(AbstractPosTracker<?> pointCoords, AbstractPosTracker<?> hitboxCoords) {
		float boxY = hitboxCoords.getX().floatValue();
		float boxTopY = this.getTopY(hitboxCoords);
		float boxBottomY = this.getBottomY(hitboxCoords);
		float pointY = pointCoords.getY().floatValue();
		if (pointY >= boxBottomY && pointY <= boxTopY) {
			return boxY - pointY;
		}
		return 0;

	}

	/*
	 * Returns the how far it is in the other thing
	 */
	public LevelPos getPointColliding(AbstractPosTracker<?> pointCoords, AbstractPosTracker<?> hitboxCoords) {
		float pointX = (float) pointCoords.getX().floatValue();
		float pointY = (float) pointCoords.getY().floatValue();

		float ammountX = 0;
		float ammountY = 0;
		LevelPos hitCo = hitboxCoords.getAsLevelPos();

		// Gets the coords of the hitbox
		float right = this.getRightX(hitCo);
		float left = this.getLeftX(hitCo);
		float top = this.getTopY(hitCo);
		float bottom = this.getBottomY(hitCo);

		/*
		 * Checks how much it's intersecting
		 */

		// gets how much it is intersecting it

		return new LevelPos(ammountX, ammountY);

	}
}
