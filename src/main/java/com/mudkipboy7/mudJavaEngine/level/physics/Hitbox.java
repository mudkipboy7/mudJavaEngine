package com.mudkipboy7.mudJavaEngine.level.physics;

import com.mudkipboy7.mudJavaEngine.level.LevelPos;

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

	public LevelPos getTop(LevelPos pos) {
		float y = pos.y + (this.height / 2.0F);
		return new LevelPos(pos.x, y, 0);
	}

	public LevelPos getBottom(LevelPos pos) {
		float y = pos.y - (this.height / 2.0F);
		return new LevelPos(pos.x, y, 0);
	}

	public LevelPos getRight(LevelPos pos) {
		float x = pos.x + (this.width / 2.0F);
		return new LevelPos(x, pos.y, 0);
	}

	public LevelPos getLeft(LevelPos pos) {
		float x = pos.x - (this.width / 2.0F);
		return new LevelPos(x, pos.y, 0);
	}

	public static boolean isTopCollidingWith(Hitbox hitbox1, LevelPos pos1, Hitbox hitbox2, LevelPos pos2) {
		if (hitbox1.getTop(pos1).y >= hitbox2.getBottom(pos2).y && pos1.y <= pos2.y)
			return true;
		return false;
	}

	public static boolean isBottomCollidingWith(Hitbox hitbox1, LevelPos pos1, Hitbox hitbox2, LevelPos pos2) {
		if (hitbox1.getBottom(pos1).y <= hitbox2.getTop(pos2).y && pos1.y >= pos2.y)
			return true;
		return false;
	}

	public static boolean isRightCollidingWith(Hitbox hitbox1, LevelPos pos1, Hitbox hitbox2, LevelPos pos2) {
		if (hitbox1.getRight(pos1).x <= hitbox2.getLeft(pos2).x && pos1.x <= pos2.x)
			return true;
		return false;
	}

	public static boolean isLeftCollidingWith(Hitbox hitbox1, LevelPos pos1, Hitbox hitbox2, LevelPos pos2) {
		if (hitbox1.getLeft(pos1).x <= hitbox2.getRight(pos2).x && pos1.x >= pos2.x)
			return true;
		return false;
	}
}
