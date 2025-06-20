package mudkipboy7.game.level;

import mudkipboy7.game.level.tile.TilePos;

public abstract class AbstractPosTracker<numType extends Number> {
	public numType x;
	public numType y;
	public numType z;

	public AbstractPosTracker(numType x, numType y, numType z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public numType getX() {
		return x;
	}

	public numType getY() {
		return y;
	}

	public numType getZ() {
		return z;
	}

	public LevelPos getAsLevelPos() {
		return new LevelPos(this.getX().floatValue(), this.getY().floatValue(), this.getZ().floatValue());
	}

	public TilePos getAsTilePos() {
		return new TilePos(this.getX().intValue(), this.getY().intValue(), this.getZ().intValue());
	}
}
