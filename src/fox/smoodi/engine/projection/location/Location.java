package fox.smoodi.engine.projection.location;

import org.joml.Vector2f;
import org.joml.Vector3f;

public interface Location {

	Vector3f toVector3f();
	
	Vector2f toVector2f();
	
	float getX();
	float getY();
	float getZ();
	
	Location clone();
	
	int getDimensions();
	
	void setX(float x);
	void setY(float y);
	void setZ(float z);
	
	void fromVector(Vector2f vec2);
	void fromVector(Vector3f vec3);
	
	void add(Vector2f vec2);
	void sub(Vector2f vec2);
	void mul(Vector2f vec2);
	void div(Vector2f vec2);
	
	void add(Vector3f vec3);
	void sub(Vector3f vec3);
	void mul(Vector3f vec3);
	void div(Vector3f vec3);
	
	
	void add(Location loc);
	void sub(Location loc);
	void mul(Location loc);
	void div(Location loc);
}
