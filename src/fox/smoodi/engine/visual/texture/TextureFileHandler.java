package fox.smoodi.engine.visual.texture;

import java.util.HashMap;

public class TextureFileHandler {

	private static HashMap<String, TextureFileIDPair> data = new HashMap<>();
	private static int nextID = 0;
	
	
	//We add a new texture to our texture map.
	//It returns the name you stored the texture in in order to make it possible to use 
	//following syntax: new Texture(TextureFileLoader.loadTexture("player_tex", "C:\..."));
	public static String loadTexture(String name, String filename) {
		data.put(name, new TextureFileIDPair(nextID, filename));
		nextID++;
		return name;
	}
	
	//We release the file out of our memory.
	public static void freeTexture(String name) {
		data.remove(name);
	}
	
	//We get the storage path of the file
	public static String getFileName(String name) {
		return data.get(name).getFilename();
	}
}
