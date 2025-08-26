package com.mudkipboy7.mudJavaEngine;

import java.nio.IntBuffer;
import java.util.Date;
import java.util.Timer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.mudkipboy7.mudJavaEngine.input.InputHandler;
import com.mudkipboy7.mudJavaEngine.input.InputKey;
import com.mudkipboy7.mudJavaEngine.level.Level;
import com.mudkipboy7.mudJavaEngine.level.entity.VictimObject;
import com.mudkipboy7.mudJavaEngine.render.AbstractRendererInstance.TextRendererInstance;
import com.mudkipboy7.mudJavaEngine.render.Camera;
import com.mudkipboy7.mudJavaEngine.render.RendererManager;
import com.mudkipboy7.mudJavaEngine.render.Renderers;
import com.mudkipboy7.mudJavaEngine.render.renderers.CharRenderer;

/**
 * The main class for the program
 */
public final class Main implements Runnable {
	// Game name and versioning stuff
	private static final String GAME_NAME = "Shoot Up The UN Headquarters";
	private static String VERSION;
	private static final boolean showVersion = true;

	// Window stuff
	private long glfwWindow = 0L;
	private static final int windowWidth = 1000;
	private static final int windowHeight = 1000;
	private Camera cameraPos;

	// Input stuff
	public InputHandler input;

	// Objects

	private RendererManager rendererManager;
	private Level level;
	boolean doIntro = true;
	boolean doOutro = false;
	public static final int numOfEnemies = 0000;
	public int enemiesKilled = 0;

	@Override
	public void run() {
		init();

		int x = 0;
		initLevel();
		new Thread(this) {
			@Override
			public void run() {

				while (!GLFW.glfwWindowShouldClose(glfwWindow)) {
					input.tickInput();
					tickStuff();
					if (true && input.queryIsInputKeyPressed(InputKey.KEY_CLOSE)) {
						GLFW.glfwSetWindowShouldClose(glfwWindow, true);
					}
				}

			}
		}.start();

		// this.renderThread.start();
		// Makes the game do the loop to run it
		while (!GLFW.glfwWindowShouldClose(glfwWindow)) {
			tickRendering();
		}
	}

	private void initLevel() {
		this.level = new Level(this);
	}

	private void init() {
		this.createWindow();

		this.input = new InputHandler(glfwWindow);
		this.cameraPos = new Camera(0, 0, -0.09F);
		this.rendererManager = new RendererManager(this);
		CharRenderer.initChars();
	}

	private double secSinceLastGameTick = 1000;
	private double timeOfLastTick = 50;
	private double tps = 40D;
	public XPerSecondKeeperTracker TPStracker = new XPerSecondKeeperTracker();

	private void tickStuff() {
		// This is done to desync the physics from the framerate
		double currentTime = GLFW.glfwGetTime();
		secSinceLastGameTick += (currentTime - timeOfLastTick);
		timeOfLastTick = currentTime;

		if (secSinceLastGameTick >= 1D / tps) {
			if (this.level != null) {
				level.tickEntities();
				secSinceLastGameTick = 0;
				TPStracker.tick();
			}

			// System.out.println(TPStracker.getXps());
		}

	}

	private void tickRendering() {

		rendererManager.renderStuff();

		GLFW.glfwSwapBuffers(glfwWindow);
		GLFW.glfwPollEvents();

	}

	private void createWindow() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!GLFW.glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
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

	public InputHandler getInput() {
		return input;
	}

	public RendererManager getRendererManager() {
		return rendererManager;
	}

	public Camera getCamera() {
		return cameraPos;
	}

	public Level getLevel() {
		return level;
	}

	public static float getAspectRatio() {
		return ((float) windowWidth) / ((float) windowWidth);

	}

	/**
	 * The main() method for the program.
	 * 
	 * @param args The launch arguments for the program.
	 */
	public static void main(String[] args) {
		System.out.println("Program Started.");

		// Gets the build version and does a null check to prevent crashing
		String implementationVersion = Main.class.getPackage().getImplementationVersion();
		VERSION = implementationVersion == null ? "INVALID_VERSION" : implementationVersion;
		System.out.println("version: " + VERSION);

		// Starts the game
		final Main main = new Main();
		main.run();
		System.out.println("Program Ended");
	}

}
