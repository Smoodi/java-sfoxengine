package fox.smoodi.engine.world.objects;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.transformation.TransformationObject;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation3D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling3D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation3D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location3D;

public class DynamicWorldObject extends TransformationObject{

	/*
	 * Nothing but a specification of a TransformationObject
	 */
	
	public DynamicWorldObject() {
		SFoxEngine.world.addObject(this);
	}
	
	public void update() { }
	
	public void syncedUpdate() { }
	
	public void init() { }
	
	public void destroy() { SFoxEngine.world.destroyObject(this); }
}
