package mudkipboy7.game.input;

import org.lwjgl.glfw.GLFW;

public class MouseInputChecker extends AbstractInputChecker {

	public MouseInputChecker(Long window) {
		super(window);
	}

	public long queryMousePosition(int keyChecking) {
		return GLFW.glfwGetMonitorUserPointer(this.glfwWindow);
	}

	public boolean queryIsMouseButtonPressed(int keyChecking) {
		return GLFW.glfwGetMouseButton(glfwWindow, keyChecking) == GLFW.GLFW_PRESS;
	}
}
