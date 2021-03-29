package fox.smoodi.engine.visual.texture;

import java.util.HashMap;

public class TextureHandler {

	private static HashMap<String, Texture> data = new HashMap<>(); 
	
	public static Texture loadTexture(String name, String filename) {
		
		if(existsTexture(name)) return getTexture(name);
		Texture tex = new Texture(TextureFileHandler.loadTexture(name, filename));
		
		data.put(name, tex);
		
		return tex;
		
	}
	
	public static AnimatedTexture loadAnimatedTexture(int amount, int fps, String path, String name, String ext) {
		
		AnimatedTexture tex = new AnimatedTexture(amount, fps, path, name, ext);
		return tex;
	}
	
	public static void print() {
		System.out.println("Entries in the texture handler:\n");
		for(String str : data.keySet()) {
			System.out.println("- "+str+"\n");
		}
	}
	
	public static boolean existsTexture(String name) {
		return data.containsKey(name);
	}
	
	public static Texture getTexture(String name) {
		return data.get(name);
	}
	
}
