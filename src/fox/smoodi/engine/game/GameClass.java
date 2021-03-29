package fox.smoodi.engine.game;

public interface GameClass {
	
	/**
	 * Called once when the game is being started.
	 */
	void init();

	
	/**
	 * This is the core main loop. It will be run constantly in a while loop. It is not affected by the frame rate and delta time.
	 */
	void loop();
	
	/**
	 * This is the synced main loop. It will be run every time a new frame is being drawn. Therefore it is affected by the frame rate and delta time.
	 */
	void syncedLoop();
	
	
	/*
	 * This is only used by the engine once to send the class the window it created in order for the class
	 * to be able to use other GLFW functions where a window is needed.
	 */
	void recieveWindow(long window);

	/*
	 * Every game should have a close method. This is fired when the game closes / should close.
	 */
	void close();
}
