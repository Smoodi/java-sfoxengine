package fox.smoodi.engine.world.tiles;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.model.Model;
import fox.smoodi.engine.model.ModelPlane;
import fox.smoodi.engine.projection.camera.Camera;
import fox.smoodi.engine.rendering.SubRenderer;
import fox.smoodi.engine.rendering.modules.VertexBufferObject;
import fox.smoodi.engine.shader.Shader;

public class TileRenderer implements SubRenderer {
	
	private Model model = new ModelPlane();
	private VertexBufferObject vbo = new VertexBufferObject(model.getVertices(), model.getIndices(), model.getTextureCoords(), null);
	
	public void render(TileSet set, Camera cam) {
		for(Tile tile : set.tiles ) {
			
			if(tile != null) {
				if(tile.getVisible()) {
					tile.getShader().bind();
					Matrix4f tile_pos = new Matrix4f().translate( new Vector3f(tile.getPos().getX()*2,tile.getPos().getY()*2, 0));
					Matrix4f target = new Matrix4f();
					
					cam.getProjection().mul( set.getTranformationMatrix(), target );
					target.mul(tile_pos);
					target.mul(SFoxEngine.world.getMatrix());
			
					tile.getShader().setShaderUniforms(target);
			
					vbo.setTexture(tile.getTexture());
					vbo.render();
					
					Shader.unbind();
				}
			}
		}
			
	}
	
	public void refreshWindow() {
		vbo = new VertexBufferObject(model.getVertices(), model.getIndices(), model.getTextureCoords(), null);
	}
}
