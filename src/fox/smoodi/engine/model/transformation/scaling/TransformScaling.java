package fox.smoodi.engine.model.transformation.scaling;

import org.joml.Vector2f;
import org.joml.Vector3f;

import fox.smoodi.engine.projection.location.Location;

public interface TransformScaling {

	public float getX();
	public void setX(float x);
	public void setY(float y);
	public float[] toArray();
	public String toString();
	public Vector2f toVector2f();
	public Vector3f toVector3f();
	public void add(float add_xyz);
	public void add(float add_x, float add_y);
	public void add(float add_x, float add_y, float add_z);
	public void sub(float sub_xyz);
	public void sub(float sub_x, float sub_y);
	public void sub(float sub_x, float sub_y, float sub_z);
	public void mul(float mul_xyz);
	public void mul(float mul_x, float mul_y);
	public void mul(float mul_x, float mul_y, float mul_z);
	public void div(float div_xyz);
	public void div(float div_x, float div_y);
	public void div(float div_x, float div_y, float div_z);
	public float getZ();
	public void setZ(float z);
	public void set(float x, float y);
	public void set(float x, float y, float z);
	public void set(Location l);
	public void set(Vector3f vec3);
	public void set(Vector2f vec2);
	public void reset();
	
}
