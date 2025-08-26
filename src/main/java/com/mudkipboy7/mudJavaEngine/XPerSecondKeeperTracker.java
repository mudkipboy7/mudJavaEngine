package com.mudkipboy7.mudJavaEngine;

import org.lwjgl.glfw.GLFW;

public class XPerSecondKeeperTracker {

	private double lastXTime = GLFW.glfwGetTime();
	private double XSinceLastSecond = 0;
	public double xps;

	public void tick() {
		// Used to calculate FPS
		double currentFrameTime = GLFW.glfwGetTime();
		this.XSinceLastSecond++;
		if (currentFrameTime - lastXTime >= 1.0) {
			this.xps = XSinceLastSecond;
			XSinceLastSecond = 0;
			lastXTime = currentFrameTime;
		}
	}

	public double getXps() {
		return xps;
	}
}
