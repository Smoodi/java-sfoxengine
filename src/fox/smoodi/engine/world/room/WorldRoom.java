package fox.smoodi.engine.world.room;

import java.util.List;

import org.joml.Matrix4f;

import fox.smoodi.engine.world.objects.StaticWorldObject;
import fox.smoodi.engine.world.objects.StaticWorldObjectCollection;

public interface WorldRoom {

	StaticWorldObjectCollection getObjectCollection(int x, int y);
	StaticWorldObjectCollection getObjectCollection(int x, int y, int z);
	
	List<StaticWorldObject> getStaticObjects(int x, int y);
	List<StaticWorldObject> getStaticObjects(int x, int y, int z);
	
	void place(StaticWorldObject object, int x, int y);
	void place(StaticWorldObject object, int x, int y, int z);
	
	void destroyObjects(int x, int y);
	void destroyObjects(int x, int y, int z);

	Matrix4f getMatrix();
}
