package com.mudkipboy7.mudJavaEngine.level;

import com.mudkipboy7.mudJavaEngine.level.tile.TilePos;

public class LevelPos extends AbstractPosTracker<Float> {
	public LevelPos(float x, float y, float z) {
		super(x, y, z);
	}

	public static LevelPos copyFrom(@SuppressWarnings("rawtypes") AbstractPosTracker pos) {
		return new LevelPos(pos.x.floatValue(), pos.y.floatValue(), pos.z.floatValue());
	}

	public TilePos getAsTilePos() {
		return new TilePos(this.getX(), this.getY(), this.getZ());
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
