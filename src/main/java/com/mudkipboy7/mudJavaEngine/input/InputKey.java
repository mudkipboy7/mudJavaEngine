package com.mudkipboy7.mudJavaEngine.input;

import org.lwjgl.glfw.GLFW;

public class InputKey {
	// These are all of the inputs that you should use
	public static final InputKey KEY_UP = new InputKey(GLFW.GLFW_KEY_W, false);
	public static final InputKey KEY_DOWN = new InputKey(GLFW.GLFW_KEY_S, false);
	public static final InputKey KEY_LEFT = new InputKey(GLFW.GLFW_KEY_A, false);
	public static final InputKey KEY_RIGHT = new InputKey(GLFW.GLFW_KEY_D, false);
	public static final InputKey KEY_JUMP = new InputKey(GLFW.GLFW_KEY_SPACE, false);

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
