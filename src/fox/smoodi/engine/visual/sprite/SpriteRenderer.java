package fox.smoodi.engine.visual.sprite;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.Model;
import fox.smoodi.engine.model.ModelPlane;
import fox.smoodi.engine.projection.camera.Camera;
import fox.smoodi.engine.rendering.SubRenderer;
import fox.smoodi.engine.rendering.modules.VertexBufferObject;
import fox.smoodi.engine.shader.Shader;

public class SpriteRenderer implements SubRenderer{
	
	private Model model = new ModelPlane();
	private VertexBufferObject vbo = new VertexBufferObject(model.getVertices(), model.getIndices(), model.getTextureCoords(), null);
	
	public void render(Sprite spr, Camera cam) {
			
			if(spr.getTexture() != null) {
				if(spr.getVisible()) {
					spr.getShader().bind();
					Matrix4f tile_pos = new Matrix4f().translate( new Vector3f(spr.position.getX()*2,spr.position.getY()*2, 0));
					Matrix4f target = new Matrix4f();
			
					cam.getProjection().mul( spr.getTranformationMatrix(), target );
					target.mul(tile_pos);
					target.mul(SFoxEngine.world.getMatrix());
			
					spr.getShader().setShaderUniforms(target);
					
					vbo.setTexture(spr.getTexture());
					vbo.render();
					
					Shader.unbind();
				}
			}
	}
	
	public void refreshWindow() {
		vbo = new VertexBufferObject(model.getVertices(), model.getIndices(), model.getTextureCoords(), null);
	}
}
