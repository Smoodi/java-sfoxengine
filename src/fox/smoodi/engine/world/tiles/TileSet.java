package fox.smoodi.engine.world.tiles;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation2D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling2D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation2D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.rendering.Renderer;
import fox.smoodi.engine.shader.Shader;
import fox.smoodi.engine.visual.texture.TextureHandler;
import fox.smoodi.engine.world.World;
import fox.smoodi.engine.world.objects.DynamicWorldObject;

public class TileSet extends DynamicWorldObject {
	
	
	private final static int size = 16;
	public Tile tiles[] = new Tile[size];
	private int t_i = 0;
	
	
	public TileSet(Location position, float xscale, float yscale) {
		
		this.position = position;
		this.translate = new TransformTranslation2D(0,0);
		this.scale = new TransformScaling2D(xscale, yscale);
		this.rotation = new TransformRotation2D(0,0);
		SFoxEngine.world.getRenderer().addToTileList(this);
	}
	
	public void print() {
		System.out.println("Tileset entries:");
		for(int i=0;i<size;i++) {
			System.out.println("Entry "+i+": "+tiles[i]);
		}
	}
	
	public int add(String texture, String texturePath, boolean collision) {
		
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, texturePath);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		
		t_i++;
		return t_i-1;
	}
	
	public int add(String texture, String texturePath, Shader shd, boolean collision) {
		
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, texturePath, shd);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		t_i++;
		return t_i-1;
	}
	
	public int add(String texture, String texturePath, Shader shd, Location pos, boolean collision) {
		
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, texturePath, shd, pos);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		t_i++;
		return t_i-1;
	}
	
	public int add(String texture, Location pos, boolean collision) {
		if(!TextureHandler.existsTexture(texture)) return -1;
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, "", pos);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		t_i++;
		
		return t_i-1;
	}
	
	public int add(String texture, String texturePath, Location pos, boolean collision) {
		
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, texturePath, pos);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		t_i++;
		return t_i-1;
	}
	
	public int add(String texture, Shader shd, Location pos, boolean collision) {
		
		if(!TextureHandler.existsTexture(texture)) return -1;
		//Our tileset is full. We cannot pass anymore tiles in it.
		if(t_i >= size) return -1;
		
		tiles[t_i] = new Tile(t_i,texture, "", shd, pos);
		
		if(collision) {
			tiles[t_i].generateCollisionMap();
			tiles[t_i].collider.parent = this;
		}
		t_i++;
		return t_i-1;
	}
	
	
	public Tile getTile(int index) {
		return tiles[index];
	}
	
	public void destroy() {
		SFoxEngine.world.getRenderer().removeFromTileList(this);
	}
	
}
