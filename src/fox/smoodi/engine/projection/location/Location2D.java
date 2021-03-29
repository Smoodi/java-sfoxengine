package fox.smoodi.engine.projection.location;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Location2D implements Location {

	private float x;
	private float y;
	
	@Override
	public Vector3f toVector3f() {
		return new Vector3f(x,y,0);
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
		return 0;
	}
	@Override
	public void setX(float x_pos) {
		x = x_pos;
	}
	@Override
	public void setY(float y_pos) {
		y = y_pos;
	}
	@Override
	public void setZ(float z_pos) {
	}
	
	@Override
	public int getDimensions() {
		return 2;
	}
	
	@Override
	public void fromVector(Vector2f vec2) {
		x = vec2.x;
		y = vec2.y;
	}
	@Override
	public void fromVector(Vector3f vec3) {
		x = vec3.x;
		y = vec3.y;
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
	public void add(Vector3f vec3) {
		x += vec3.x;
		y += vec3.y;
	}
	@Override
	public void mul(Vector3f vec3) {
		x *= vec3.x;
		y *= vec3.y;
	}
	@Override
	public void div(Vector3f vec3) {
		x /= vec3.x;
		y /= vec3.y;
	}
	@Override
	public void sub(Vector3f vec3) {
		x -= vec3.x;
		y -= vec3.y;
	}
	
	@Override
	public void add(Location loc) {
		x += loc.getX();
		y += loc.getY();
	}
	@Override
	public void mul(Location loc) {
		x *= loc.getX();
		y *= loc.getY();
	}
	@Override
	public void div(Location loc) {
		x /= loc.getX();
		y /= loc.getY();
	}
	@Override
	public void sub(Location loc) {
		x -= loc.getX();
		y -= loc.getY();
	}
	
	@Override
	public Location clone() {
		return new Location2D(x,y);
	}
	
	public Location2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Location2D(Vector2f vec2) {
		x = vec2.x;
		y = vec2.y;
	}
}
