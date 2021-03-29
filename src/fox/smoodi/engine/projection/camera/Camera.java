package fox.smoodi.engine.projection.camera;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import fox.smoodi.engine.projection.location.Location2D;
import fox.smoodi.engine.projection.matrix.ProjectionMatrix;

public interface Camera {

	void setPosition(Location2D position);

	void setPosition(Vector2f vec2);

	Location2D getPosition();
	
	void addPosition(Location2D position);
	
	void addPosition(Vector2f vec2);
	
	void setProjection();
	
	ProjectionMatrix getProjectionMatrix();
	
	Matrix4f getProjection();
	
}
