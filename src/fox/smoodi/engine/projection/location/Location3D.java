package fox.smoodi.engine.projection.location;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Location3D implements Location {

	private float x;
	private float y;
	private float z;
	
	
	@Override
	public Vector3f toVector3f() {
		return new Vector3f(x,y,z);
	}
	@Override
	public Vector2f toVector2f() {
		return new Vector2f(x,y);
	}
	@Override
	public float getX() {
		return x;
	}
	@Override
	public float getY() {
		return y;
	}
	@Override
	public float getZ() {
		return z;
	}
	@Override
	public void setX(float x) {
		this.x = x;
	}
	@Override
	public void setY(float y) {
		this.y = y;
	}
	@Override
	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	public int getDimensions() {
		return 3;
	}
	
	@Override
	public void fromVector(Vector2f vec2) {
		x = vec2.x;
		y = vec2.y;
		z = 0;
	}
	@Override
	public void fromVector(Vector3f vec3) {
		x = vec3.x;
		y = vec3.y;
		z = vec3.z;
	}
	@Override
	public void add(Vector2f vec2) {
		x += vec2.x;
		y += vec2.y;
	}
	@Override
	public void mul(Vector2f vec2) {
		x *= vec2.x;
		y *= vec2.y;
	}
	@Override
	public void div(Vector2f vec2) {
		x /= vec2.x;
		y /= vec2.y;
	}
	@Override
	public void sub(Vector2f vec2) {
		x -= vec2.x;
		y -= vec2.y;
	}
	@Override
	public void add(Location loc) {
		x += loc.getX();
		y += loc.getY();
		z += loc.getZ();
	}
	@Override
	public void mul(Location loc) {
		x *= loc.getX();
		y *= loc.getY();
		if(loc.getDimensions() > 2)
			z *= loc.getZ();
	}
	@Override
	public void div(Location loc) {
		x /= loc.getX();
		y /= loc.getY();
		if(loc.getDimensions() > 2)
			z *= loc.getZ();
	}
	@Override
	public void sub(Location loc) {
		x -= loc.getX();
		y -= loc.getY();
		z -= loc.getZ();
	}
	
	@Override
	public void add(Vector3f vec3) {
		x += vec3.x;
		y += vec3.y;
		z += vec3.z;
	}
	@Override
	public void mul(Vector3f vec3) {
		x *= vec3.x;
		y *= vec3.y;
		z *= vec3.z;
	}
	@Override
	public void div(Vector3f vec3) {
		x /= vec3.x;
		y /= vec3.y;
		z /= vec3.z;
	}
	@Override
	public void sub(Vector3f vec3) {
		x -= vec3.x;
		y -= vec3.y;
		z -= vec3.z;
	}
	
	@Override
	public Location clone() {
		return new Location3D(x,y,z);
	}
	
	public Location3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location3D(Vector3f vec3) {
		this.x = vec3.x;
		this.y = vec3.y;
		this.z = vec3.z;
	}
}
