package com.mudkipboy7.mudJavaEngine;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.mudkipboy7.mudJavaEngine.input.InputHandler;
import com.mudkipboy7.mudJavaEngine.input.InputKey;

public class Main {
	// Game name and versioning stuff
	public static final String GAME_NAME = "Mud Java Game Engine";
	public static String VERSION;
	private static final boolean showVersion = true;

	// Input stuff
	public InputHandler input;

	public long cursor = 0;

	// Window stuff
	private long glfwWindow = 0L;
	private static final int windowWidth = 800;
	private static final int windowHeight = 800;

	public void run() {
		init();

		while (!GLFW.glfwWindowShouldClose(glfwWindow)) {
			tickGameLogic();
			tickRendering();
			//System.out.println(InputKey.KEY_DOWN.getIsMouseKey());
			//System.out.println(input.getCursorX());
			if (input.queryIsInputKeyPressed(InputKey.KEY_JUMP)) {
				GLFW.glfwSetWindowShouldClose(glfwWindow, true);

			}

		}

	}

	private void init() {
		this.createWindow();
		this.input = new InputHandler(glfwWindow);
		this.cursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_ARROW_CURSOR);
	}

	private void tickGameLogic() {
		input.tickInput();
	}

	private void tickRendering() {
		GLFW.glfwSwapBuffers(glfwWindow);
		GLFW.glfwPollEvents();
	}

	private void createWindow() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!GLFW.glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		String windowName = showVersion ? GAME_NAME + "-" + VERSION : GAME_NAME;
		glfwWindow = GLFW.glfwCreateWindow(windowWidth, windowHeight, windowName, MemoryUtil.NULL, MemoryUtil.NULL);
		if (glfwWindow == MemoryUtil.NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		// Makes it appear centered on the screen
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			GLFW.glfwGetWindowSize(glfwWindow, pWidth, pHeight);
			GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			GLFW.glfwSetWindowPos(glfwWindow, (vidmode.width() - pWidth.get(0)) / 2,
					(vidmode.height() - pHeight.get(0)) / 2);
		}
		GLFW.glfwMakeContextCurrent(glfwWindow);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(glfwWindow);
		GL.createCapabilities();
	}

	/**
	 * Starts up the program, parses launch args and gets build and debug info
	 * 
	 * @param args The launch arguments for the program
	 */
	public static void main(String[] args) {
		System.out.println("Program Started");

		// Gets the build version and does a null check to prevent crashing
		String implementationVersion = Main.class.getPackage().getImplementationVersion();
		VERSION = implementationVersion == null ? "INVALID_VERSION" : implementationVersion;

		System.out.println("version: " + VERSION);
		// System.out.println("args: " + String.join("", args));

		// Starts the game
		final Main main = new Main();
		main.run();
		System.out.println("Program Ended");

	}

}
