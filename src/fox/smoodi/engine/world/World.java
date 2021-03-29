package fox.smoodi.engine.world;

import java.util.LinkedList;
import java.util.List;

import org.joml.Matrix4f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.audio.AudioHandler;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation2D;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation3D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling2D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling3D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation2D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation3D;
import fox.smoodi.engine.rendering.Renderer;
import fox.smoodi.engine.visual.texture.Texture;
import fox.smoodi.engine.world.objects.DynamicWorldObject;
import fox.smoodi.engine.world.room.WorldRoom;
import fox.smoodi.engine.world.room.WorldRoom2D;
import fox.smoodi.engine.world.room.WorldRoom3D;

public class World {

	private List<DynamicWorldObject> objects;
	private Renderer worldRenderer;
	private WorldRoom room;
	public WorldBackground2D bgd;
	private WorldMode mode;
	private Matrix4f matrix;
	public TransformTranslation translate;
	public TransformScaling scale;
	public TransformRotation rotation;
	public AudioHandler audio;
	private List<DynamicWorldObject> junkList;
	private int tileSize;
	/*
	 * A world is basically just a collection of all non-static objects and a WorldRoom
	 */
	
	public World(WorldMode mode, TransformScaling scale, int tileSize, Texture bgdImg) {
		this.objects = new LinkedList<DynamicWorldObject>();
		this.junkList = new LinkedList<DynamicWorldObject>();
		if(mode == WorldMode.BASIC2D) {
			
			this.room = new WorldRoom2D(255, 255, tileSize);
			this.translate = new TransformTranslation2D(0,0);
			this.scale = new TransformScaling2D(1,1);
			this.rotation = new TransformRotation2D(0,0);
			
		} else if(mode == WorldMode.BASIC3D) {
			
			this.room = new WorldRoom3D(255, 255, 255, tileSize);
			this.translate = new TransformTranslation3D(0,0,0);
			this.scale = new TransformScaling3D(1,1,1);
			this.rotation = new TransformRotation3D(0,0,0);
			
		} else {
			throw new IllegalStateException("Undefined world mode.");
		}
		this.matrix = new Matrix4f().setTranslation(translate.toVector3f()).scale(scale.toVector3f()).rotateXYZ(rotation.toVector3f());
		this.tileSize = tileSize;
		this.scale = scale;
		this.mode = mode;
		this.bgd = new WorldBackground2D(bgdImg);

		//Creating a world renderer
		this.worldRenderer = new Renderer();
		
		//Creating a Audio Handler
		this.audio = new AudioHandler();
	}
	
	public void addObject(DynamicWorldObject obj) {
		objects.add(obj);
	}
	
	public void destroyObject(DynamicWorldObject obj) {
		junkList.add(obj);
	}
	
	public boolean existsObject(DynamicWorldObject obj) {
		return objects.contains(obj);
	}
	
	public boolean containsObjectType(DynamicWorldObject obj) {
		for(DynamicWorldObject o : objects) {
			if(o.getClass() == obj.getClass()) {
				return true;
			}
		}
		return false;
	}
	
	public int getObjectsCount() {
		return objects.size();
	}
	
	public Matrix4f getMatrix() {
		return matrix;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		String type = (mode == WorldMode.BASIC2D) ? "2D" : "3D";
		str.append("WorldType: "+type+"\n");
		str.append("Scale: "+scale.toString()+"\n");
		str.append("TileSize: "+tileSize+"\n");
		str.append("Objects in world ("+getObjectsCount()+"):\n");
		for(DynamicWorldObject obj : objects) {
			str.append("Object: "+obj.toString()+"\n");
		}
		return str.toString();
	}
	
	public void update() {
		for(DynamicWorldObject obj : junkList) {
			objects.remove(obj);
		}
		junkList.clear();
		
		for (DynamicWorldObject obj : objects) {
			obj.update();
		}
	}
	
	public void snycedUpdate() {
		for (DynamicWorldObject obj : objects) {
			obj.syncedUpdate();
		}
		
		SFoxEngine.getWindow().clearBuffer();
		
		//We also have to render our screen
		bgd.render();
		
		worldRenderer.render();
	}

	public Renderer getRenderer() {
		return worldRenderer;
	}
	
}
