package mudkipboy7.game.level.tile;

import mudkipboy7.game.level.AbstractPosTracker;
import mudkipboy7.game.level.LevelPos;

/**
 * This stores a tile's position, it always stores positions as integers and not
 * floats. Also because a tile's z
 */
public class TilePos extends AbstractPosTracker<Integer> {

	public TilePos(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}

	public TilePos(Integer x, Integer y) {
		this(x, y, 0);
	}

	public TilePos(Float x, Float y) {
		this(Math.round(x), Math.round(y), 0);
	}

	public TilePos(Float x, Float y, Float z) {
		this(Math.round(x), Math.round(y), Math.round(z));
	}

	public static TilePos copyFrom(TilePos tilePos) {
		return new TilePos(tilePos.x, tilePos.y);
	}

	public static TilePos copyFrom(LevelPos levelPos) {
		return new TilePos(Math.round(levelPos.x), Math.round(levelPos.y));
	}

}
