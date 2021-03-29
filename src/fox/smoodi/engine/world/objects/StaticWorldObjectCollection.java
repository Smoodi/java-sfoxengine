package fox.smoodi.engine.world.objects;

import java.util.LinkedList;
import java.util.List;

public class StaticWorldObjectCollection {

	/*
	 * A WorldObjectCollection is a object defined in a world room which is containing every object on a current 
	 */
	private List<StaticWorldObject> objects = new LinkedList<StaticWorldObject>();
	
	public void addObject(StaticWorldObject obj) {
		if(!containsObject(obj))
			objects.add(obj);
	}
	public void destroyObject(StaticWorldObject obj) {
		objects.remove(obj);
	}
	public boolean containsObject(StaticWorldObject obj) {
		return objects.contains(obj);
	}
	public List<StaticWorldObject> getObjects() {
		return objects;
	}
	
}
