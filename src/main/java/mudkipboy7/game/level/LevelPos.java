package mudkipboy7.game.level;

import mudkipboy7.game.level.tile.TilePos;

public class LevelPos extends AbstractPosTracker<Float> {
	public LevelPos(float x, float y, float z) {
		super(x, y, z);
	}

	public LevelPos(float x, float y) {
		this(x, y, 0);
	}

	public static LevelPos copyFrom(@SuppressWarnings("rawtypes") AbstractPosTracker pos) {
		return new LevelPos(pos.x.floatValue(), pos.y.floatValue(), pos.z.floatValue());
	}

	@Override
	public LevelPos getAsLevelPos() {
		return this;
	}

	public LevelPos setX(float x) {
		this.x = x;
		return this;
	}

	public LevelPos setY(float y) {
		this.y = y;
		return this;
	}

	public LevelPos setZ(float z) {
		this.z = z;
		return this;
	}

	public void moveX(float ammount) {
		this.x += ammount;
	}

	public void moveY(float ammount) {
		this.y += ammount;
	}

	public void moveZ(float ammount) {
		this.z += ammount;
	}

}
