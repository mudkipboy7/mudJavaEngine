package com.mudkipboy7.mudJavaEngine.render;

import com.mudkipboy7.mudJavaEngine.level.LevelPos;

public class Camera {
	/*
	 * Remember, the true position of the camera is 0,0,0 facing towards negative.
	 * So if you want something to be drawn it must be drawn at <0 y
	 */
	public LevelPos levelPos;

	public Camera(LevelPos levelPos) {
		this.levelPos = levelPos;
	}

	public Camera(float x, float y, float z) {
		this(new LevelPos(x, y, z));
	}

	public void copyPosFrom(LevelPos levelPos, boolean copyZ) {
		this.levelPos.x = levelPos.x;
		this.levelPos.y = levelPos.y;
		if (copyZ)
			this.levelPos.z = levelPos.z;
	}

	public float getX() {
		return levelPos.x;
	}

	public float getY() {
		return levelPos.y;
	}

	public float getZ() {
		return levelPos.z;
	}

	public void moveX(float ammount) {
		this.levelPos.x += ammount;
	}

	public void moveY(float ammount) {
		this.levelPos.y += ammount;
	}

	public void moveZ(float ammount) {
		// this.levelPos.z += ammount;
		this.levelPos.z = Math.min(this.levelPos.getZ() + ammount, -0.01F);
	}
}
