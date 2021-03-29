package fox.smoodi.engine.world.tiles;

import org.joml.Vector2f;
import fox.smoodi.engine.collision.colliders.AABBCollider2D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location2D;
import fox.smoodi.engine.shader.NoShaderShader;
import fox.smoodi.engine.shader.Shader;
import fox.smoodi.engine.visual.texture.Texture;
import fox.smoodi.engine.visual.texture.TextureHandler;

public class Tile {

	private byte id;
	private String texture;
	private Shader shd;
	private Texture tex;
	private Location pos;
	private boolean visible; 
	public AABBCollider2D collider;
	
	public Tile(int id, String texture, String texturePath) {
		this.id = (byte)id;
		this.texture = texture;
		
		if(!TextureHandler.existsTexture(texture))
			tex = TextureHandler.loadTexture(texture, texturePath);
		else
			tex = TextureHandler.getTexture(texture);
		
		this.shd = NoShaderShader.object;
		this.pos = new Location2D(0,0);
		this.visible = true;
	}
	
	public Tile(int id, String texture, String texturePath, Shader shd, Location pos) {
		this.id = (byte)id;
		this.texture = texture;
		
		if(!TextureHandler.existsTexture(texture))
			tex = TextureHandler.loadTexture(texture, texturePath);
		else
			tex = TextureHandler.getTexture(texture);
		
		this.shd = shd;
		this.pos = pos;
		this.visible = true;
	}

	public Tile(int id, String texture, String texturePath, Shader shd) {
		this.id = (byte)id;
		this.texture = texture;
		
		if(!TextureHandler.existsTexture(texture))
			tex = TextureHandler.loadTexture(texture, texturePath);
		else
			tex = TextureHandler.getTexture(texture);
		
		this.shd = shd;
		this.visible = true;
	}

	public Tile(int id, String texture, String texturePath, Location pos) {
		this.id = (byte)id;
		this.texture = texture;
		
		if(!TextureHandler.existsTexture(texture))
			tex = TextureHandler.loadTexture(texture, texturePath);
		else
			tex = TextureHandler.getTexture(texture);
		
		this.shd = NoShaderShader.object;
		this.pos = pos;
		this.visible = true;
	}
	

	public byte getId() { return id; }
	
	public Shader getShader() { return shd; }
	public void setShader(Shader shd) { this.shd = shd; } 

	public String getTextureName() {
		return texture;
	}
	
	public Texture getTexture() {
		return tex;
	}
	
	public void generateCollisionMap() {
		collider = new AABBCollider2D(pos, new Vector2f(0.5f, 0.5f));
	}
	
	public boolean usesCollision() { return (collider != null); } 
	
	public boolean getVisible() { return visible; }
	
	public void setVisible(boolean visible) { this.visible = visible; }
	
	public void setTexture(String texture) {
		this.texture = texture;
	}

	public Location getPos() {
		return pos;
	}

	public void setPos(Location pos) {
		this.pos = pos;
	}
}
