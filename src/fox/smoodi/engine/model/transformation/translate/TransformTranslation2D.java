package fox.smoodi.engine.model.transformation.translate;

import org.joml.Vector2f;
import org.joml.Vector3f;

import fox.smoodi.engine.projection.location.Location;

public class TransformTranslation2D implements TransformTranslation {

	private float x;
	private float y;
	
	public TransformTranslation2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
		
	public TransformTranslation2D(Location l) {
		this.x = l.getX();
		this.y = l.getY();
	}
	public TransformTranslation2D() {
		this.x = 0;
		this.y = 0;
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

	public float[] toArray() {
		return new float[] { x, y } ;
	}
	
	public String toString() {
		return Float.toString(x) + ", "+Float.toString(y);
	}
	
	public Vector2f toVector2f() {
		return new Vector2f(x,y);
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Location l) {
		this.x = l.getX();
		this.y = l.getY();
	}
	
	public void set(Vector3f vec3) {
		this.x = vec3.x;
		this.y = vec3.y;
	}
	
	public void set(Vector2f vec2) {
		this.x = vec2.x;
		this.y = vec2.y;
	}

	public void reset() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public Vector3f toVector3f() {
		return new Vector3f(x,y,0);
	}

	@Override
	public float getZ() {
		return 0;
	}

	@Override
	public void setZ(float z) {
	}

	@Override
	public void set(float x, float y, float z) {
		set(x,y);
	}
	
	@Override
	public void add(float add_xyz) {
		x += add_xyz;
		y += add_xyz;
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
	}

	@Override
	public void sub(float sub_xyz) {
		x -= sub_xyz;
		y -= sub_xyz;
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
	}

	@Override
	public void mul(float mul_xyz) {
		x *= mul_xyz;
		y *= mul_xyz;
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
	}

	@Override
	public void div(float div_xyz) {
		x /= div_xyz;
		y /= div_xyz;
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
	}
}
