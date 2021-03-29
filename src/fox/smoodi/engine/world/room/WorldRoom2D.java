package fox.smoodi.engine.world.room;

import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.world.objects.StaticWorldObject;
import fox.smoodi.engine.world.objects.StaticWorldObjectCollection;

public class WorldRoom2D implements WorldRoom {

	private int width;
	private int height;
	private int tileSize;
	private Matrix4f world;
	private StaticWorldObjectCollection[][] objects;
	
	/*
	 * Tiles are defined by a square
	*/
	
	public WorldRoom2D ( int w, int h, int tileSize ) {
		
		this.width = w;
		this.height = h;
		this.tileSize = tileSize;
		this.objects = new StaticWorldObjectCollection[w][h];
		
		this.world = new Matrix4f().setTranslation(new Vector3f(0));
		this.world.scale(tileSize/2);
		
	}
	
	public StaticWorldObjectCollection getObjectCollection( int x, int y ) { return objects[x][y]; }

	public void destroyObjects(int x, int y) {
		objects[x][y].getObjects().clear();
		objects[x][y] = null;
	}
	
	public void place(StaticWorldObject object, int x, int y) {
		StaticWorldObjectCollection collection = objects[x][y];
		collection.addObject(object);
	}
	
	public List<StaticWorldObject> getStaticObjects(int x, int y) {
		return objects[x][y].getObjects();
	}

	public StaticWorldObjectCollection getObjectCollection(int x, int y, int z) {
		return getObjectCollection(x,y);
	}

	public List<StaticWorldObject> getStaticObjects(int x, int y, int z) {
		return getStaticObjects(x,y);
	}

	public void place(StaticWorldObject object, int x, int y, int z) {
		place(object, x, y);
	}

	public void destroyObjects(int x, int y, int z) {
		destroyObjects(x,y);
	}

	public Matrix4f getMatrix() {
		return world;
	}
}
