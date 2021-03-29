package fox.smoodi.engine.game;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.input.InputManager;
import fox.smoodi.engine.input.modules.InputBinder;

public class GameWindow {

	private long id, frameTime, currentTime, accumulator;
	private double frameCap, deltaTime, frames_pastTime, time;
	private int width, height, frames_drawn, latest_fps;
	private boolean update, fullscreen, wasResized;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWVidMode videoMode;
	private String title;
	
	/**
	 * Creates a new game window.
	 * @param title The title of the window / game.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 * @param maxFPS The desired fps of the window (frame cap).
	 * @param fullscreen Whether it should be a fullscreen window or not. If true the window width and height will be the monitor's.
	 */
	public GameWindow(String title, int width, int height, int maxFPS, boolean fullscreen) {
		
		this.title = title;
		this.frameCap = (double) maxFPS;
		this.time = 0;
        this.deltaTime = 1000.0 / frameCap;
        this.frames_pastTime = 0;
        this.frames_drawn = 0;
        this.latest_fps = 0;
        this.currentTime = System.currentTimeMillis();
        this.accumulator = 0;
		this.fullscreen = fullscreen;

		this.wasResized = false;
        
        createWindow(title, width, height, fullscreen);
    
        createLocalCallbacks();
	}
	
	/**
	 * Currently not completely implemented.
	 */
	public void swapFullscreen() {
		
		windowFullscreen(!fullscreen);
	}
	
	/**
	 * Currently not completely implemented.
	 * @param fullscreen Whether the new window should be fullscreen or not.
	 */
	public void windowFullscreen(boolean fullscreen) {
		
		if(fullscreen && this.fullscreen == false)  {
			
			//Destroy our current window.
			GLFW.glfwDestroyWindow(id);
			cleanUp();
			
			//Generate a new one.
			SFoxEngine.setGameWindow(new GameWindow(title, width, height, (int) frameCap, true));
			
			//Send it to our Renderer and Input Manager.
			InputManager.reloadWindow();
			InputBinder.reloadWindow();
		}
		else if(!fullscreen && this.fullscreen == true) {
			
			//Destroy our current window.
			GLFW.glfwDestroyWindow(id);
			cleanUp();
			
			SFoxEngine.setGameWindow(new GameWindow(title, width, height, (int) frameCap, false));
			
			//Send it to our Renderer and Input Manager.
			InputManager.reloadWindow();
			InputBinder.reloadWindow();
			
			SFoxEngine.world.getRenderer().refreshWindow();
		}
	}
	
	/**
	 * Creates the local callbacks.
	 */
	private void createLocalCallbacks() {
		windowSizeCallback = new GLFWWindowSizeCallback() {
		
			@Override
			public void invoke(long argWindow, int winWidth, int winHeight) {
				width = winWidth;
				height = winHeight;
				wasResized = true;
			}
		};
		
		glfwSetWindowSizeCallback(id, windowSizeCallback);
	}

	/**
	 * Cleans up everything and frees the memory.
	 */
	public void cleanUp() {
		glfwFreeCallbacks(id);
	}
	
	/**
	 * Creates a new window. Sub method of the constructor.
	 * @param title Title of the window.
	 * @param width Width of the window.
	 * @param height Height of the window
	 * @param fullscreen Whether it should be a fullscreen window or not.
	 */
	private void createWindow(String title, int width, int height, boolean fullscreen ) {
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		//Now we create a window to display the game on.
		long window = glfwCreateWindow(fullscreen ? videoMode.width() : width, fullscreen ? videoMode.height() : height, title+" powered by SFox Engine", fullscreen ? glfwGetPrimaryMonitor() : 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		this.id = window;
		this.width = width;
		this.height = height;
		

		if(!fullscreen)
			glfwSetWindowPos(window, (videoMode.width() - width)/2, (videoMode.height() - height)/2);
		
		//Create all GL stuff
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
			
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//And now we display it to the user and send it to our game.
		glfwShowWindow(window);
		
		sendWindow(SFoxEngine.game);
	}
	
	/**
	 * Cast every time the while loop in the engine runs. It is unaffected by the frame rate and delta time.
	 */
	public void preUpdate() {
		
		if(wasResized)
			updateWindowSize();
		
		update = false;
		
		long newTime = System.currentTimeMillis();
		frameTime = newTime - currentTime;
        frames_pastTime += frameTime;
        
        currentTime = newTime;
        accumulator += frameTime;
	}
	
	/**
	 * Updates the window's size.
	 */
	private void updateWindowSize() {
		SFoxEngine.gameCamera.setProjection();
		glViewport(0,0, width, height);
	}

	/**
	 * Cast every time before the engine renders a new frame. Therefore it is affected by the delta time and frame rate. 
	 */
	public void update() {
        this.accumulator -= deltaTime;
        this.time += deltaTime;
    	this.update = true;
    	this.frames_drawn++;
    	this.wasResized = false;
    	InputManager.update();
		glfwPollEvents();
	}
	
	/**
	 * Cast every time at the end of the while loop. Unaffected by the frame rate and delta time.
	 */
	public void postUpdate() {
        if(this.frames_pastTime >= 1000.0) {
        	this.frames_pastTime = 0;
        	this.latest_fps = frames_drawn;
        	frames_drawn = 0;
        }
	}
	
	/**
	 * Sets the window's size. 
	 * @param width Width of the window.
	 * @param height Height of the window.
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * NOT COMPLETELY IMPLEMENTED:
	 * Returns the current delta time. This is the time how long the last frame took to render.
	 * @return Returns the delta time.
	 */
	public long getDeltaTime() {
		return frameTime;
	}
	
	/**
	 * NOT COMPLETELY IMPLEMENTED:
	 * Calculates a factor which can be used to sync speeds on differently fast computers. 
	 * @return Returns the delta factor.
	 */
	public double getDeltaFactor() {
		System.out.println(frameCap * frames_pastTime);
		return frameCap * frames_pastTime;
	}
	
	/**
	 * Clears the current color buffer.
	 */
	public void clearBuffer() { glClear(GL_COLOR_BUFFER_BIT); }
	
	/**
	 * Swaps the graphic buffers.
	 */
	public void swapBuffers() { glfwSwapBuffers(id); }
	
	/**
	 * Sends the window to a game class.
	 * @param game The game class the window should be sent to. 
	 */
	public void sendWindow(GameClass game) { game.recieveWindow(id); }
	
	/**
	 * Determines if the engine needs to render a new image in order to keep the fps rate. 
	 * @return True if a new frame needs to be drawn - otherwise false.
	 */
	public boolean shouldRender() { return accumulator >= deltaTime; }
	
	/**
	 * Returns the number of drawn frames in the last second.
	 * @return Number of Frames
	 */
	public int getFPS() { return latest_fps; }
	
	/**
	 * Determines if the window needs to update.
	 * @return True if the window should update. Otherwise false.
	 */
	public boolean needsUpdate() { return update; }
	
	/**
	 * Determines if the window should close.
	 * @return True if the window should close. Otherwise false.
	 */
	public boolean shouldClose() { return glfwWindowShouldClose(id); }
	
	/**
	 * Returns the window's internal id. This number is generated by glfw. For more information see the LWJGL documentation.
	 * @return The internal window id.
	 */
	public long getID() { return id; }
	
	/**
	 * Returns the window's width.
	 * @return Width of the window.
	 */
	public int getWidth() { return width; }
	
	/**
	 * Returns the window's height.
	 * @return Height of the window.
	 */
	public int getHeight() { return height; }
	
	/**
	 * Determines if the window is a fullscreen window.
	 * @return True if it is a fullscreen window - otherwise false.
	 */
	public boolean isFullscreen() { return fullscreen; }

	/**
	 * NOT IMPLEMENTED YET:
	 * Sets the internal fullscreen variable.
	 * @param fullscreen Value of the fullscreen variable.
	 */
	private void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	
}
