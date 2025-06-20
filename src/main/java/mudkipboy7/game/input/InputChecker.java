package mudkipboy7.game.input;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

public class InputChecker extends AbstractInputChecker {

	public InputChecker(Long window) {
		super(window);
	}

	public boolean queryIsKeyHeld(int... keysChecking) {
		ArrayList<Boolean> propertiesOfFound = new ArrayList<>();
		Arrays.stream(keysChecking)
				.forEach(key -> propertiesOfFound.add(GLFW.glfwGetKey(glfwWindow, key) == GLFW.GLFW_PRESS));
		return propertiesOfFound.contains(true);
	}

	public long queryMousePosition(int keyChecking) {

		return GLFW.glfwGetMonitorUserPointer(this.glfwWindow);
	}

	public boolean queryIsMouseButtonHeld(int... keysChecking) {
		ArrayList<Boolean> propertiesOfFound = new ArrayList<>();
		Arrays.stream(keysChecking)
				.forEach(key -> propertiesOfFound.add(GLFW.glfwGetMouseButton(glfwWindow, key) == GLFW.GLFW_PRESS));
		return propertiesOfFound.contains(true);
	}


}
