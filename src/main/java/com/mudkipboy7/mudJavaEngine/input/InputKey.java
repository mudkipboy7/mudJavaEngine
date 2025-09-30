package com.mudkipboy7.mudJavaEngine.input;

import org.lwjgl.glfw.GLFW;

public class InputKey {
	// These are all of the inputs that you should use
	public static final InputKey KEY_UP = new InputKey(GLFW.GLFW_KEY_UP, false);
	public static final InputKey KEY_DOWN = new InputKey(GLFW.GLFW_KEY_DOWN, false);
	public static final InputKey KEY_LEFT = new InputKey(GLFW.GLFW_KEY_LEFT, false);
	public static final InputKey KEY_RIGHT = new InputKey(GLFW.GLFW_KEY_RIGHT, false);
	public static final InputKey KEY_JUMP = new InputKey(GLFW.GLFW_KEY_SPACE, false);
	public static final InputKey KEY_CLOSE = new InputKey(GLFW.GLFW_KEY_ESCAPE, false);
	public static final InputKey KEY_RUN = new InputKey(GLFW.GLFW_KEY_Z, false);
	
	public static final InputKey KEY_ZOOM_IN = new InputKey(GLFW.GLFW_KEY_MINUS, false);
	public static final InputKey KEY_ZOOM_OUT = new InputKey(GLFW.GLFW_KEY_EQUAL, false);

	/**
	 * Defines a specific input value
	 */
	private InputKey(int glfwValue, boolean isMouseKey) {
		this.glfwValue = glfwValue;

		this.isMouseKey = isMouseKey;
	}

	private int glfwValue;
	private boolean isMouseKey = false;

	public int getGlfwValue() {
		return glfwValue;
	}

	public boolean getIsMouseKey() {
		return isMouseKey;
	}

}
