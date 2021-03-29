package fox.smoodi.engine.visual.sprite;

import org.joml.Vector2f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.collision.colliders.AABBCollider2D;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation2D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling2D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation2D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.rendering.Renderer;
import fox.smoodi.engine.shader.NoShaderShader;
import fox.smoodi.engine.shader.Shader;
import fox.smoodi.engine.visual.texture.AnimatedTexture;
import fox.smoodi.engine.visual.texture.Texture;
import fox.smoodi.engine.world.objects.DynamicWorldObject;

public class Sprite extends DynamicWorldObject {
	
	private float w;
	private float h;
	private Texture tex;
	private Shader shd;
	private boolean animated;
	public AABBCollider2D collider;
	private boolean visible;
	
	public Sprite(Location position, float width, float height, Texture texture, boolean collision) {

		this.tex = texture;
		
		this.position = position;
		this.translate = new TransformTranslation2D(0,0);
		this.scale = new TransformScaling2D(width, height);
		this.rotation = new TransformRotation2D(0,0);
		
		this.shd = NoShaderShader.object;
		this.visible = true;
		
		if(texture instanceof AnimatedTexture)
			this.animated = true;
		
		if(collision) {
			this.collider = new AABBCollider2D(position, new Vector2f(0.5f,0.5f));
			this.collider.parent = this;
		}
		
		SFoxEngine.world.getRenderer().addToSpriteList(this);
	}
	
	public float getWidth() {
		return w;
	}
	
	public boolean usesCollision() { return collider != null; }
	
	public void setShader(Shader shader) {
		shd = shader;
	}
	
	public Shader getShader() {
		return shd;
	}
	
	public boolean usesShader() {
		return shd != null;
	}

	public void setWidth(float w) {
		this.w = w;
	}

	public Texture getTexture() {
		return tex;
	}

	public void setTexture(Texture tex) {
		this.tex = tex;
	}

	public float getHeight() {
		return h;
	}

	public void setHeight(float h) {
		this.h = h;
	}

	public boolean isAnimated() {
		return animated;
	}

	public boolean getVisible() {
		return visible;
	}
	
	public void destroy() {
		SFoxEngine.world.getRenderer().removeFromSpriteList(this);
	}
	
	public void setVisible(boolean visible) { this.visible = visible; }

}
