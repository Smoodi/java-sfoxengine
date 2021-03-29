package fox.smoodi.engine.world.tiles;

import java.util.HashMap;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.rendering.Renderer;


public class TileSetsHandler {
	
	private static HashMap<String, TileSet> data = new HashMap<>();
	private static int index = -1;
	
	public static void addTileSet(String name, TileSet tileSet) {
		data.put(name, tileSet);
	}
	
	public static void setTileSetActive(String name, boolean active) {
			if(active) {
				if(index == -1)
					index = SFoxEngine.world.getRenderer().addToTileList(data.get(name));
			} else {
				SFoxEngine.world.getRenderer().removeFromTileList(data.get(name));
			}
		}
	
	public static boolean existsTileSet(String name) {
		return data.containsKey(name);
	}
	
	public static TileSet getTileSet(String name) {
		return data.get(name);
	}
}
