package fox.smoodi.engine.input;

import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;

import java.util.HashMap;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.input.modules.InputBinder;
import fox.smoodi.engine.input.modules.KeyboardKey;
import fox.smoodi.engine.input.modules.MouseButton;

public class InputManager {

	private static boolean keys[];
	///Let's get some inputs done.
	private static long window = SFoxEngine.getWindow().getID();
	private static HashMap<KeyboardKey, Integer> indexMap;
	
	/**
	 * Initializes the Input Manager.
	 */
	public static void init() {
		keys = new boolean[KeyboardKey.values().length];
		indexMap = new HashMap<KeyboardKey, Integer>();
		int i = 0;
		for(KeyboardKey key : KeyboardKey.values()) {
			indexMap.put(key, i);
			keys[i] = false;
			i++;
		}
	}
	
	/**
	 * Returns whether a key is currently pressed down.
	 * @param key The key to check for.
	 * @return True if the key is currently pressed down - false otherwise.
	 */
	public static boolean isKeyActive(KeyboardKey key) { return keys[indexMap.get(key)]; }
	
	//Internal check mechanism
	private static boolean checkKeyActive(int key) {
		return glfwGetKey(window, key) == 1;
	}
	
	/**
	 * Checks if a mouse button is currently pressed down.
	 * @param button The button to check for.
	 * @return True if the button is currently pressed down - false otherwise.
	 */
	public static boolean isMouseButtonActive(MouseButton button) {
		return glfwGetMouseButton(window, InputBinder.getMouseButton(button)) == 1;
	}
	
	/**
	 * Returns true right in that moment when the given key is being pressed. 
	 * @param key The key to check for.
	 * @return True if the key is being pressed right at that moment.
	 */
	public static boolean isKeyPressed(KeyboardKey key) {
		int i = indexMap.get(key);
		int ii = InputBinder.getKey(key);
		return (checkKeyActive(ii) && !keys[i]);
	}
	
	/**
	 * Returns true right in that moment when the given key is being released. 
	 * @param key The key to check for.
	 * @return True if the key is being released right at that moment.
	 */
	public static boolean isKeyReleased(KeyboardKey key) {
		int i = indexMap.get(key);
		int ii = InputBinder.getKey(key);
		return (!checkKeyActive(ii) && keys[i]);
	}
	
	/**
	 * Should be run every synced step in order to update the internal mechanism to check for pressed and released keys. 
	 */
	public static void update() {
		for(KeyboardKey key : KeyboardKey.values()) {
			keys[indexMap.get(key)] = checkKeyActive(InputBinder.getKey(key));
		}
	}
	
	/**
	 * NOT IMPLEMENTED YET:
	 * Reloads the window.
	 */
	public static void reloadWindow() {
		window = SFoxEngine.getWindow().getID(); 
	}
	
}
