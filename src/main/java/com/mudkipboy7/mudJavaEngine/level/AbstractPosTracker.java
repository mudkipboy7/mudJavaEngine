package com.mudkipboy7.mudJavaEngine.level;

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

}
