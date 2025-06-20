package mudkipboy7.game;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import mudkipboy7.game.input.InputChecker;
import mudkipboy7.game.level.Level;
import mudkipboy7.game.level.entity.VictimObject;
import mudkipboy7.game.render.AbstractRendererInstance.TextRendererInstance;
import mudkipboy7.game.render.Camera;
import mudkipboy7.game.render.RendererManager;
import mudkipboy7.game.render.Renderers;
import mudkipboy7.game.render.renderers.CharRenderer;

/**
 * The main class for the program
 */
public final class GameMain implements Runnable {
	// Game name and versioning stuff
	private static final String GAME_NAME = "Shoot Up The UN Headquarters";
	private static final String VERSION = "1.0";
	private static final boolean showVersion = true;

	// Window stuff
	private long glfwWindow = 0L;
	private static final int windowWidth = 1000;
	private static final int windowHeight = 1000;
	private Camera cameraPos;

	// Input stuff
	public InputChecker input;

	// Objects

	private RendererManager rendererManager;

	private Level level;
	boolean sandboxMode = true; // makes it where the game never ends
	boolean doIntro = true;
	boolean doOutro = false;
	public static final int numOfEnemies = 200;
	public int enemiesKilled = 0;

	@Override
	public void run() {
		init();

		// Makes the game do the loop to run it
		while (!GLFW.glfwWindowShouldClose(glfwWindow)) {

			if (doIntro) {
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.05F, 0.05F,
						-0.90F, 0.8F, "Shoot Up The UN Headquartes!"));
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.04F, 0.04F,
						-0.95F, 0.4F, "You feel like doing a little trolling.", "You break in to the UN headquarters.",
						"Kill all the agents of Satan!"));
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.03F, 0.03F,
						-0.95F, 0.08F, "Controls:", "WASD or arrow-keys to move", "Left-click or F to shoot",
						"Space-bar to jump", "Esc to exit"));
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.04F, 0.04F,
						-0.65F, -0.5F, "Press Space to continue..."));
				rendererManager.stuffBeingRendered.add(
						new TextRendererInstance(Renderers.charRenderer, 0.03F, 0.03F, -0.95F, -0.9F, "By:mudkipboy7"));
				if (showVersion)
					rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.03F,
							0.03F, 0.6F, -0.9F, "Ver:" + VERSION));
				if (input.queryIsKeyHeld(GLFW.GLFW_KEY_SPACE)) {

					initLevel();
					doIntro = false;
				}
			}
			if (doOutro && !sandboxMode) {
				this.level = null;
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.04F, 0.04F,
						-0.95F, 0.6F, "You killed all of the UN demons.", "You've made Satan very angry.", "Yay!"));
				rendererManager.stuffBeingRendered.add(new TextRendererInstance(Renderers.charRenderer, 0.04F, 0.04F,
						-0.5F, 0F, "Press esc to exit..."));
			}
			if (this.level != null && !doIntro && !doOutro) {
				if (!sandboxMode) {
					doOutro = true;
				}
				level.getEntityManager().getLoadedEntities().forEach(f -> {
					if (f instanceof VictimObject) {
						this.doOutro = false;
					}
				});

				tickStuff();
			}
			if (true && input.queryIsKeyHeld(GLFW.GLFW_KEY_ESCAPE)) {
				GLFW.glfwSetWindowShouldClose(glfwWindow, true);
			}
			tickRendering();
		}

	}

	private void initLevel() {
		this.level = new Level(this);
	}

	private void init() {
		this.createWindow();
		this.input = new InputChecker(glfwWindow);
		this.cameraPos = new Camera(0, 0, -0.09F);
		this.rendererManager = new RendererManager(this);
		CharRenderer.initChars();
	}

	private void tickStuff() {
		level.tickEntities();
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

	public InputChecker getInput() {
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
		// Creates the main game class.
		// new ArrayList<String>(Arrays.asList(args));

		new GameMain().run();
		System.out.println("Program Ended");
	}
}
