package fox.smoodi.engine.projection.camera;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.audio.AudioListener;
import fox.smoodi.engine.projection.location.Location2D;
import fox.smoodi.engine.projection.matrix.ProjectionMatrix;
import fox.smoodi.engine.projection.matrix.ProjectionMatrixMode;

public class Camera2D implements Camera {
	
	private Location2D position;
	private ProjectionMatrix projection;
	
	private float xscale;
	private float yscale;
	
	public AudioListener listener;
	
	
	public Camera2D(int width, int height) {
		
		xscale = width/SFoxEngine.getWindow().getWidth();
		yscale = height/SFoxEngine.getWindow().getHeight();
		
		position = new Location2D(0,0);
		projection = new ProjectionMatrix(ProjectionMatrixMode.MAXTRIX2D, width, height);
		
		listener = new AudioListener();
		
	}
	
	public Camera2D() {
		
		xscale = 1;
		yscale = 1;
		
		position = new Location2D(0,0);
		projection = new ProjectionMatrix(ProjectionMatrixMode.MAXTRIX2D);
		
		listener = new AudioListener();
		
	}
	
	public void update() {
		
		listener.setPosition(position.toVector3f());
		
	}
	
	public void setAspects(float hor, float ver) {
		xscale = hor;
		yscale = ver;
	}
	
	public void setPosition(Location2D position) {
		this.position = position;
	}

	public void setPosition(Vector2f vec2) {
		position.fromVector(vec2);
	}

	public Location2D getPosition() {
		return position;
	}
	
	public void addPosition(Location2D position) {
		this.position.add(position);
	}
	
	public void addPosition(Vector2f vec2) {
		this.position.add(vec2);
	}
	
	public Matrix4f getProjection() {
		
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position.toVector3f());
		
		target = projection.getMatrix().mul(pos, target);
		
		return target;
		
	}
	
	public ProjectionMatrix getProjectionMatrix() {
		return projection;
	}
	
	public void setProjection() {
		
		int width = (int) (SFoxEngine.getWindow().getWidth() * xscale);
		int height = (int) (SFoxEngine.getWindow().getHeight() * yscale);
		
		this.projection = new ProjectionMatrix(ProjectionMatrixMode.MAXTRIX2D, width, height);
	}
	
}
