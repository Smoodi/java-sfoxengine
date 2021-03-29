package fox.smoodi.engine;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import fox.smoodi.engine.game.GameClass;
import fox.smoodi.engine.game.GameWindow;
import fox.smoodi.engine.input.InputManager;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling3D;
import fox.smoodi.engine.projection.camera.Camera2D;
import fox.smoodi.engine.world.World;
import fox.smoodi.engine.world.WorldMode;

public class SFoxEngine {
		
	
	/*
	 * This is the core part of the engine. You pass in the main loop of your game
	 * and the size of the window and it will automaticly create a window for you and
	 * update it permanently.
	 */
	public static String thisGameTitle;
	public static GameClass game; 
	public static Camera2D gameCamera;
	public static int maxFPS;
	private static GameWindow win;
	public static World world;
	
	public static void startGame(int width, int height, String gameTitle, GameClass gameClass, int fps, boolean fullscreen) {
		
		game = gameClass;
		CallbackHandler.setCallbacks();
		
		//Right here we initialize our libraries
		if(!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		
		win = new GameWindow( gameTitle, width, height, fps, fullscreen);
		
		//We set the video mode and the window position
		
		//Init our game.
		world = new World(WorldMode.BASIC3D, new TransformScaling3D(1,1,1), 32, null);
		
		InitCoreElements();
		gameClass.init();
		
		while(!win.shouldClose()){

			win.preUpdate();
			
			gameClass.loop();
			
			world.update();
			
            while ( win.shouldRender() ) {
            	
                win.update();
				
				gameClass.syncedLoop();
				
				world.snycedUpdate();
				
				win.swapBuffers();
            }
            
            win.postUpdate();
		}
		
		//If the game got closed we can terminate everything.
		world.audio.free();
		glfwTerminate();
	}
	
	private static void InitCoreElements() {
		InputManager.init();
		//glfwPollEvents();
	}

	//This function breaks out of the game loop.
	public static void endGame() {
		glfwSetWindowShouldClose(win.getID(), true);
	}
	
	public static void setGameCamera(Camera2D camera) {
		gameCamera = camera;
	}
	public static Camera2D getGameCamera() {
		return gameCamera;
	}
	
	public static long getDeltaTime() {
		return win.getDeltaTime();
	}
	
	public static double getDeltaFactor() {
		return win.getDeltaFactor();
	}
	
	public static double getTime() { return (double)System.nanoTime() / (double)1000000000L; }
	
	public static GameWindow getWindow() {
		return win;
	}
	
	public static void setGameWindow(GameWindow window) {
		win = window;
	}

}
