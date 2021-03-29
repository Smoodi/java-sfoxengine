package fox.smoodi.engine.world.room;

import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.world.objects.StaticWorldObject;
import fox.smoodi.engine.world.objects.StaticWorldObjectCollection;

public class WorldRoom3D implements WorldRoom{

	private int width;
	private int height;
	private int depth;
	private int tileSize;
	private Matrix4f world;
	private StaticWorldObjectCollection[][][] objects;
	
	/*
	 * Tiles are defined by a square
	*/
	
	public WorldRoom3D ( int w, int h, int d, int tileSize ) {
		
		this.width = w;
		this.height = h;
		this.depth = d;
		this.tileSize = tileSize;
		this.objects = new StaticWorldObjectCollection[w][h][d];
		
		this.world = new Matrix4f().setTranslation(new Vector3f(0));
		this.world.scale(tileSize/2);
		
	}
	
	public StaticWorldObjectCollection getObjectCollection( int x, int y, int z ) { return objects[x][y][z]; }
	
	public void destroyObjects(int x, int y, int z) {
		objects[x][y][z].getObjects().clear();
		objects[x][y][z] = null;
	}
	
	public void place(StaticWorldObject object, int x, int y, int z) {
		StaticWorldObjectCollection collection = objects[x][y][z];
		collection.addObject(object);
	}
	public List<StaticWorldObject> getStaticObjects(int x, int y, int z) {
		return objects[x][y][z].getObjects();
	}

	public StaticWorldObjectCollection getObjectCollection(int x, int y) {
		return getObjectCollection(x,y,0);
	}

	public void place(StaticWorldObject object, int x, int y) {
		place(object, x, y, 0);
	}

	public void destroyObjects(int x, int y) {
		destroyObjects(x,y,0);
	}

	public List<StaticWorldObject> getStaticObjects(int x, int y) {
		return getStaticObjects(x,y,0);
	}
	
	public Matrix4f getMatrix() {
		return world;
	}
}
