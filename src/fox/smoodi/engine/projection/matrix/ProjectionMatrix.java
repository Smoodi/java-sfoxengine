package fox.smoodi.engine.projection.matrix;

import org.joml.Matrix4f;

import fox.smoodi.engine.SFoxEngine;

public class ProjectionMatrix {
	
	private Matrix4f matrix;
	private boolean isDefined = false;
	private ProjectionMatrixMode mode;
	private int w;
	private int h;
	
	public ProjectionMatrix (ProjectionMatrixMode mode) {
		
		//Get some variables
		w = SFoxEngine.getWindow().getWidth();
		h = SFoxEngine.getWindow().getHeight();
		
		if(mode == ProjectionMatrixMode.MAXTRIX2D) {
			matrix = new Matrix4f().setOrtho2D(-w/2, w/2, -h/2, h/2);
			isDefined = true;
		}
		
	}
	
public ProjectionMatrix (ProjectionMatrixMode mode, int width, int height) {
		
		//Get some variables
		w = width;
		h = height;
		
		if(mode == ProjectionMatrixMode.MAXTRIX2D) {
			matrix = new Matrix4f().setOrtho2D(-w/2, w/2, -h/2, h/2);
			isDefined = true;
		}
		
	}
	
	public boolean isDefined() { return isDefined; } //Basic Getter
	public Matrix4f getMatrix() { return matrix; }
	public void setMatrix(Matrix4f m4) { this.matrix = m4; }
	public int getWidth() { return this.w; }
	public int getHeight() { return this.h; }
	public ProjectionMatrixMode getMode() { return mode; }

}
