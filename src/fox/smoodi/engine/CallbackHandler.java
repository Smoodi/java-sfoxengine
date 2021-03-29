package fox.smoodi.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import static org.lwjgl.glfw.GLFW.*;

public class CallbackHandler {
	
	public static void setCallbacks() {
		glfwSetErrorCallback( new GLFWErrorCallback() {

			@Override
			public void invoke(int err_code, long err_desc) {
					throw new IllegalStateException(GLFWErrorCallback.getDescription(err_desc));
			}
			
		} );
	}

}
