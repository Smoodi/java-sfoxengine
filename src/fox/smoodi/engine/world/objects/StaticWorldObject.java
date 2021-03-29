package fox.smoodi.engine.world.objects;

import fox.smoodi.engine.model.transformation.rotation.TransformRotation;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location3D;

public class StaticWorldObject {

	/*
	 * A static world object is basically a point in the room. It's null-dimensional and only defined by a location in
	 * the world. This position cannot be changed later on. This kind of object can be used to generate HandlerObjects
	 * or similar objects which are resting in the world. Because they don't have any transformations at all they cannot
	 * be drawn to the screen or rendered in any other way.
	 */
	
	private Location position;
	
	public StaticWorldObject(Location position) {
		this.position = position;
	}
	
	public StaticWorldObject(Location position, TransformTranslation translation, TransformScaling scaling, TransformRotation rotation) {
		this.position = position;
	}
	
	public StaticWorldObject() { 
		this.position = new Location3D(0,0,0);
	}
	
	public Location getLocation() {
		return position;
	}
	
}
