package fox.smoodi.engine.	world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.Model;
import fox.smoodi.engine.model.ModelPlane;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation;
import fox.smoodi.engine.model.transformation.rotation.TransformRotation2D;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling;
import fox.smoodi.engine.model.transformation.scaling.TransformScaling2D;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation;
import fox.smoodi.engine.model.transformation.translate.TransformTranslation2D;
import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location3D;
import fox.smoodi.engine.rendering.modules.VertexBufferObject;
import fox.smoodi.engine.shader.NoShaderShader;
import fox.smoodi.engine.shader.Shader;
import fox.smoodi.engine.visual.texture.Texture;

public class WorldBackground2D {

	
	private TransformTranslation translate;
	private TransformScaling scale;
	private TransformRotation rotation;
	private Texture tex;
	private Location p;
	private Shader s;
	private VertexBufferObject vbo;
	private Model model = new ModelPlane();
	private boolean inited = false;
	private TransformScaling2D b_scale;
	private Shader b_shd;
	
	public WorldBackground2D(Texture texture) {
		
		this.tex = texture;
		this.p = new Location3D(0,0,0);
		this.vbo = new VertexBufferObject(model.getVertices(), model.getIndices(), model.getTextureCoords(), null);
		this.s = NoShaderShader.object;
		this.translate = new TransformTranslation2D(0,0);
		this.scale = new TransformScaling2D(64, 64);
		this.rotation = new TransformRotation2D(0,0);
		this.p = new Location3D(0,0,0);
		
		if(texture != null && SFoxEngine.gameCamera != null) {
			
			
			
		}
	}
	
	public void addInitSettings(TransformScaling2D scale, Shader shd) {
		this.b_scale = scale;
		this.b_shd = shd;
	}
	
	public void render() {
		if(tex != null && SFoxEngine.gameCamera != null)
			if(!inited) {
				if(b_scale == null) {
					this.scale = new TransformScaling2D(SFoxEngine.gameCamera.getProjectionMatrix().getWidth(), SFoxEngine.gameCamera.getProjectionMatrix().getHeight());
				} else { this.scale = b_scale; }
				if(b_shd != null) {
					s = b_shd;
				}
				inited = true;
			}
		s.bind();
		Matrix4f tile_pos = new Matrix4f().translate( new Vector3f(p.getX()*2,p.getY()*2, 0));
		Matrix4f target = new Matrix4f();

		SFoxEngine.gameCamera.getProjection().mul( getTransformationMatrix(), target );
		target.mul(tile_pos);
		target.mul(SFoxEngine.world.getMatrix());

		s.setShaderUniforms(target);
		
		vbo.setTexture(tex);
		vbo.render();
		
		Shader.unbind();
	}
	
	private Matrix4f getTransformationMatrix() {
		Vector3f t = (translate == null) ? new Vector3f(0,0,0) : translate.toVector3f();
		Vector3f s = (scale == null) ? new Vector3f(1,1,1) : scale.toVector3f();
		Vector3f r = (rotation == null) ? new Vector3f(0,0,0) : rotation.toVector3f();
		
		//Create a transformation matrix
		Matrix4f finalMatrix = new Matrix4f()
									.translation(t).translation(p.toVector3f())
									.scale(s)
									.rotateXYZ(r);
		return finalMatrix;
	}

}
