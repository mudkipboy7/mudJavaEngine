package com.mudkipboy7.mudJavaEngine.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

import org.lwjgl.glfw.GLFW;

public class InputHandler {
	protected Long glfwWindow;
	long cursor = 0;
	double[] cursorX = { 1 };
	double[] cursorY = { 1 };

	public InputHandler(Long window) {
		this.glfwWindow = window;
		// Creates the cursor
		GLFW.glfwSetInputMode(glfwWindow, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
		cursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_ARROW_CURSOR);
		GLFW.glfwSetCursor(glfwWindow, cursor);
	}

	public void tickInput() {
		GLFW.glfwGetCursorPos(glfwWindow, cursorX, cursorY);
	}

	public Long getGlfwWindow() {
		return glfwWindow;
	}

	public void setGlfwWindow(Long glfwWindow) {
		this.glfwWindow = glfwWindow;
	}

	private boolean queryIsKeyInState(int state, int... keys) {
		ArrayList<Boolean> propertiesOfFound = new ArrayList<>();
		Arrays.stream(keys).forEach(key -> propertiesOfFound.add(GLFW.glfwGetKey(glfwWindow, key) == state));
		return propertiesOfFound.contains(true);
	}

	private boolean queryIsMouseButtonInState(int state, int... keys) {
		ArrayList<Boolean> propertiesOfFound = new ArrayList<>();
		Arrays.stream(keys).forEach(key -> propertiesOfFound.add(GLFW.glfwGetMouseButton(glfwWindow, key) == state));
		return propertiesOfFound.contains(true);
	}

	public boolean queryIsKeyHeld(int... keys) {
		return queryIsKeyInState(GLFW.GLFW_PRESS, keys);
	}

	public boolean queryIsMouseButtonHeld(int... keys) {
		return queryIsMouseButtonInState(GLFW.GLFW_PRESS, keys);
	}

	public long getCursor() {
		return cursor;
	}

	public double getCursorX() {
		return cursorX[0];
	}

	public double getCursorY() {
		return cursorY[0];
	}

}
