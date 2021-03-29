package fox.smoodi.engine.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.transformation.TransformationObject;
import fox.smoodi.engine.shader.Shader;
import fox.smoodi.engine.world.objects.DynamicWorldObject;

public class RenderObject extends TransformationObject{
	
	private int index = -1;
	private Shader shader = null;
	
	/*
	 * This are 
	 */
	
	public void setActive(boolean active) {
		if(active) {
			if(index == -1)
				index = SFoxEngine.world.getRenderer().addToList(this);
		} else {
			SFoxEngine.world.getRenderer().removeFromList(this);
		}
	}
	
	public boolean isActive() {
		return SFoxEngine.world.getRenderer().containsInList(this);
	}
	
	public void render() {
		
	}
	
	public boolean usesShader() {
		return shader != null;
	}
	
	public Shader getShader() {
		return shader;
	}
	
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	public Matrix4f getTranformationMatrix() {

		
		/* ERROR CHECKING:
		 * Because there might not be a transformation element initialized yet it could result in a NullPointerException
		 * To prevent that we create fake transformation vectors in case there are noone.
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
