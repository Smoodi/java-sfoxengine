package fox.smoodi.engine.visual.sprite;

import java.util.HashMap;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.rendering.Renderer;
import fox.smoodi.engine.world.tiles.TileSet;

public class SpriteHandler {

	private static HashMap<String, Sprite> data = new HashMap<>();
	private static int index = -1;
	
	public static void addSprite(String name, Sprite spr) {
		data.put(name, spr);
	}
	
	public static void setSpriteActive(String name, boolean active) {
			if(active) {
				if(index == -1)
					index = SFoxEngine.world.getRenderer().addToSpriteList(data.get(name));
			} else {
				SFoxEngine.world.getRenderer().removeFromSpriteList(data.get(name));
			}
		}
	
	public static boolean existsSprite(String name) {
		return data.containsKey(name);
	}
	
	public static Sprite getSprite(String name) {
		return data.get(name);
	}
	
}
