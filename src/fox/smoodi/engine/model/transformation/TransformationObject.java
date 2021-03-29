package fox.smoodi.engine.model.transformation;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation3D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling3D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation3D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location3D;

public class TransformationObject {

	public Location position;
	public TransformTranslation translate;
	public TransformScaling scale;
	public TransformRotation rotation;
	
	public TransformationObject(Location position) {
		this.position = position;
	}
	
	public TransformationObject(Location position, TransformTranslation translation, TransformScaling scaling, TransformRotation rotation) {
		this.position = position;
		this.translate = translation;
		this.scale = scaling;
		this.rotation = rotation;
	}
	
	public TransformationObject() { 
	}
	
	public Matrix4f getTranformationMatrix() {

		
		/* ERROR CHECKING:
		 * Because there might not be a transformation element initialized yet it could result in a NullPointerException
		 * To prevent that we create fake transformation vectors in case there are none.
		 */
		
		//Create transformation vectors
		Vector3f t = (translate == null) ? new Vector3f(0,0,0) : translate.toVector3f();
		Vector3f s = (scale == null) ? new Vector3f(1,1,1) : scale.toVector3f();
		Vector3f r = (rotation == null) ? new Vector3f(0,0,0) : rotation.toVector3f();
		
		//Create a transformation matrix
		Matrix4f finalMatrix = new Matrix4f()
									.translation(t).translation(position.toVector3f())
									.scale(s)
									.rotateXYZ(r);
		return finalMatrix;
	}
}
