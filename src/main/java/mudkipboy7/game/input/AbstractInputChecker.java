package mudkipboy7.game.input;

/**
 * Used to create the KeyboardInput and MouseInput classes
 */
abstract class AbstractInputChecker {
	protected Long glfwWindow;

	public AbstractInputChecker(Long window) {
		this.glfwWindow = window;
	}

	public Long getGlfwWindow() {
		return glfwWindow;
	}

	public void setGlfwWindow(Long glfwWindow) {
		this.glfwWindow = glfwWindow;
	}
}
