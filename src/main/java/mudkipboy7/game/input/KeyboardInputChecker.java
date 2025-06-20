package mudkipboy7.game.input;

import org.lwjgl.glfw.GLFW;

public class KeyboardInputChecker extends AbstractInputChecker {
	public KeyboardInputChecker(Long window) {
		super(window);
	}

	public boolean queryIsKeyPressed(int keyChecking) {
		return GLFW.glfwGetKey(glfwWindow, keyChecking) == GLFW.GLFW_PRESS;
	}
	
}
