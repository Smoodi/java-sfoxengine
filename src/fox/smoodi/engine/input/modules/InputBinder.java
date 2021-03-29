package fox.smoodi.engine.input.modules;
import static org.lwjgl.glfw.GLFW.*;

import fox.smoodi.engine.SFoxEngine;

public class InputBinder {

	private static long window = SFoxEngine.getWindow().getID();
	
	/**
	 * NOT IMPLEMENTED YET:
	 * Reloads the window. Should be cast every time the window is being swapped.
	 */
	public static void reloadWindow() {
		window = SFoxEngine.getWindow().getID();
	}
	
	/**
	 * Returns the GLFW MouseButton ID to check for. 
	 * @param button The MouseButton enum entry representing the button that should be converted.
	 * @return The GLFW MouseButton ID.
	 */
	public static int getMouseButton(MouseButton button) {
		switch (button) {
		case LEFT:
			return  0;
		case RIGHT:
			return  1;
		case WHEEL:
			return  2;
		}
		return GLFW_KEY_UNKNOWN;
	}
	
	/**
	 * Returns the GLFW KeyboardKey ID to check for. 
	 * @param button The KeyboardKey enum entry representing the button that should be converted.
	 * @return The GLFW KeyboardKey ID.
	 */
	public static int getKey(KeyboardKey key) {
		switch (key) {
		case LAST:
			return  GLFW_KEY_LAST;
		case SPACE:
			return  GLFW_KEY_SPACE;
		case APOSTROPHE:
			return  GLFW_KEY_APOSTROPHE;
		case COMMA:
			return  GLFW_KEY_COMMA;
		case MINUS:
			return  GLFW_KEY_MINUS;
		case PERIOD:
			return  GLFW_KEY_PERIOD;
		case SLASH:
			return  GLFW_KEY_SLASH;
		case NUM_1:
			return  GLFW_KEY_1;
		case NUM_2:
			return  GLFW_KEY_2;
		case NUM_3:
			return  GLFW_KEY_3;
		case NUM_4:
			return  GLFW_KEY_4;
		case NUM_5:
			return  GLFW_KEY_5;
		case NUM_6:
			return  GLFW_KEY_6;
		case NUM_7:
			return  GLFW_KEY_7;
		case NUM_8:
			return  GLFW_KEY_8;
		case NUM_9:
			return  GLFW_KEY_9;
		case NUM_0:
			return  GLFW_KEY_0;
		case SEMICOLON:
			return  GLFW_KEY_SEMICOLON;
		case EQUAL:
			return  GLFW_KEY_EQUAL;
		case A:
			return  GLFW_KEY_A;
		case B:
			return  GLFW_KEY_B;
		case C:
			return  GLFW_KEY_C;
		case D:
			return  GLFW_KEY_D;
		case E:
			return  GLFW_KEY_E;
		case F:
			return  GLFW_KEY_F;
		case G:
			return  GLFW_KEY_G;
		case H:
			return  GLFW_KEY_H;
		case I:
			return  GLFW_KEY_I;
		case J:
			return  GLFW_KEY_J;
		case K:
			return  GLFW_KEY_K;
		case L:
			return  GLFW_KEY_L;
		case M:
			return  GLFW_KEY_M;
		case N:
			return  GLFW_KEY_N;
		case O:
			return  GLFW_KEY_O;
		case P:
			return  GLFW_KEY_P;
		case Q:
			return  GLFW_KEY_Q;
		case R:
			return  GLFW_KEY_R;
		case S:
			return  GLFW_KEY_S;
		case T:
			return  GLFW_KEY_T;
		case U:
			return  GLFW_KEY_U;
		case V:
			return  GLFW_KEY_V;
		case W:
			return  GLFW_KEY_W;
		case X:
			return  GLFW_KEY_X;
		case Y:
			return  GLFW_KEY_Y;
		case Z:
			return  GLFW_KEY_Z;
		case LEFT_BRACKET:
			return  GLFW_KEY_LEFT_BRACKET;
		case RIGHT_BRACKET:
			return  GLFW_KEY_RIGHT_BRACKET;
		case BACKSLASH:
			return  GLFW_KEY_BACKSLASH;
		case GRAVE_ACCENT:
			return  GLFW_KEY_GRAVE_ACCENT;
		case WORLD_1:
			return  GLFW_KEY_WORLD_1;
		case WORLD_2:
			return  GLFW_KEY_WORLD_2;
		case ESCAPE:
			return  GLFW_KEY_ESCAPE;
		case ENTER:
			return  GLFW_KEY_ENTER;
		case TAB:
			return  GLFW_KEY_TAB;
		case INSERT:
			return  GLFW_KEY_INSERT;	
		case DELETE:
			return  GLFW_KEY_DELETE;	
		case RIGHT:
			return  GLFW_KEY_RIGHT;
		case LEFT:
			return  GLFW_KEY_LEFT;
		case DOWN:
			return  GLFW_KEY_DOWN;
		case UP:
			return  GLFW_KEY_UP;
		case PAGE_UP:
			return  GLFW_KEY_PAGE_UP;
		case PAGE_DOWN:
			return  GLFW_KEY_PAGE_DOWN;
		case HOME:
			return  GLFW_KEY_HOME;
		case BACKSPACE:
			return  GLFW_KEY_BACKSPACE;
		case END:
			return  GLFW_KEY_END;
		case CAPS_LOCK:
			return  GLFW_KEY_CAPS_LOCK;
		case SCROLL_LOCK:
			return  GLFW_KEY_SCROLL_LOCK;
		case NUM_LOCK:
			return  GLFW_KEY_NUM_LOCK;
		case PRINT:
			return  GLFW_KEY_PRINT_SCREEN;
		case PAUSE:
			return  GLFW_KEY_PAUSE;
		case F1:
			return  GLFW_KEY_F1;
		case F2:
			return  GLFW_KEY_F2;
		case F3:
			return  GLFW_KEY_F3;
		case F4:
			return  GLFW_KEY_F4;
		case F5:
			return  GLFW_KEY_F5;
		case F6:
			return  GLFW_KEY_F6;
		case F7:
			return  GLFW_KEY_F7;
		case F8:
			return  GLFW_KEY_F8;
		case F9:
			return  GLFW_KEY_F9;
		case F10:
			return  GLFW_KEY_F10;
		case F11:
			return  GLFW_KEY_F11;
		case F12:
			return  GLFW_KEY_F12;
		case F13:
			return  GLFW_KEY_F13;
		case F14:
			return  GLFW_KEY_F14;
		case F15:
			return  GLFW_KEY_F15;
		case F16:
			return  GLFW_KEY_F16;
		case F17:
			return  GLFW_KEY_F17;
		case F18:
			return  GLFW_KEY_F18;
		case F19:
			return  GLFW_KEY_F19;
		case F20:
			return  GLFW_KEY_F20;
		case F21:
			return  GLFW_KEY_F21;
		case F22:
			return  GLFW_KEY_F22;
		case F23:
			return  GLFW_KEY_F23;
		case F24:
			return  GLFW_KEY_F24;
		case F25:
			return  GLFW_KEY_F25;
		case KP_0:
			return  GLFW_KEY_KP_0;
		case KP_1:
			return  GLFW_KEY_KP_1;
		case KP_2:
			return  GLFW_KEY_KP_2;
		case KP_3:
			return  GLFW_KEY_KP_3;
		case KP_4:
			return  GLFW_KEY_KP_4;
		case KP_5:
			return  GLFW_KEY_KP_5;
		case KP_6:
			return  GLFW_KEY_KP_6;
		case KP_7:
			return  GLFW_KEY_KP_7;
		case KP_8:
			return  GLFW_KEY_KP_8;
		case KP_9:
			return  GLFW_KEY_KP_9;
		case KP_DEC:
			return  GLFW_KEY_KP_DECIMAL;
		case KP_DIV:
			return  GLFW_KEY_KP_DIVIDE;
		case KP_MUL:
			return  GLFW_KEY_KP_MULTIPLY;
		case KP_ADD:
			return  GLFW_KEY_KP_ADD;
		case KP_SUB:
			return  GLFW_KEY_KP_SUBTRACT;
		case KP_ENTER:
			return  GLFW_KEY_KP_ENTER;
		case KP_EQUAL:
			return  GLFW_KEY_KP_EQUAL;
		case LEFT_SHIFT:
			return  GLFW_KEY_LEFT_SHIFT;
		case LEFT_CONTROL:
			return  GLFW_KEY_LEFT_CONTROL;
		case LEFT_ALT:
			return  GLFW_KEY_LEFT_ALT;
		case LEFT_SUPER:
			return  GLFW_KEY_LEFT_SUPER;
		case RIGHT_SHIFT:
			return  GLFW_KEY_RIGHT_SHIFT;
		case RIGHT_CONTROL:
			return  GLFW_KEY_RIGHT_CONTROL;
		case RIGHT_ALT:
			return  GLFW_KEY_RIGHT_ALT;
		case RIGHT_SUPER:
			return  GLFW_KEY_RIGHT_SUPER;
		case MENU:
			return  GLFW_KEY_MENU;
		}
		return GLFW_KEY_UNKNOWN;
	}
	
}
