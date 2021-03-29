package fox.smoodi.engine.model.transformation.rotation;

import org.joml.Vector2f;
import org.joml.Vector3f;

import fox.smoodi.engine.projection.location.Location;

public class TransformRotation3D implements TransformRotation {

	private float x;
	private float y;
	private float z; 
	
	public TransformRotation3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
		
	public TransformRotation3D(Location l) {
		this.x = l.getX();
		this.y = l.getY();
		this.z = l.getZ();
	}
	public TransformRotation3D() {
		this.x = 1;
		this.y = 1;
		this.z = 1;
	}
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float[] toArray() {
		return new float[] { x, y, z } ;
	}
	
	public String toString() {
		return Float.toString(x) + ", "+Float.toString(y) + ", "+Float.toString(z);
	}
	
	public Vector3f toVector3f() {
		return new Vector3f(x,y,z);
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(Location l) {
		this.x = l.getX();
		this.y = l.getY();
		this.z = l.getZ();
	}
	
	public void set(Vector3f vec3) {
		this.x = vec3.x;
		this.y = vec3.y;
		this.z = vec3.z;
	}
	
	public void set(Vector2f vec2) {
		this.x = vec2.x;
		this.y = vec2.y;
	}

	public void reset() {
		this.x = 1;
		this.y = 1;
		this.z = 1;
	}

	@Override
	public Vector2f toVector2f() {
		// TODO Auto-generated method stub
		return new Vector2f(x,y);
	}

	@Override
	public void set(float x, float y) {
		setX(x);
		setY(y);
	}

	@Override
	public void add(float add_xyz) {
		x += add_xyz;
		y += add_xyz;
		z += add_xyz;
	}

	@Override
	public void add(float add_x, float add_y) {
		x += add_x;
		y += add_y;
	}

	@Override
	public void add(float add_x, float add_y, float add_z) {
		x += add_x;
		y += add_y;
		z += add_z;
	}

	@Override
	public void sub(float sub_xyz) {
		x -= sub_xyz;
		y -= sub_xyz;
		z -= sub_xyz;
	}

	@Override
	public void sub(float sub_x, float sub_y) {
		x -= sub_x;
		y -= sub_y;		
	}

	@Override
	public void sub(float sub_x, float sub_y, float sub_z) {
		x -= sub_x;
		y -= sub_y;
		z -= sub_z;
	}

	@Override
	public void mul(float mul_xyz) {
		x *= mul_xyz;
		y *= mul_xyz;
		z *= mul_xyz;
	}

	@Override
	public void mul(float mul_x, float mul_y) {
		x *= mul_x;
		y *= mul_y;
	}

	@Override
	public void mul(float mul_x, float mul_y, float mul_z) {
		x *= mul_x;
		y *= mul_y;
		z *= mul_z;
	}

	@Override
	public void div(float div_xyz) {
		x /= div_xyz;
		y /= div_xyz;
		z /= div_xyz;
	}

	@Override
	public void div(float div_x, float div_y) {
		x /= div_x;
		y /= div_y;
	}

	@Override
	public void div(float div_x, float div_y, float div_z) {
		x /= div_x;
		y /= div_y;
		z /= div_z;
	}
}
