package fox.smoodi.engine.rendering;

import java.util.LinkedList;
import java.util.List;

import fox.smoodi.engine.SFoxEngine;
import fox.smoodi.engine.projection.matrix.ProjectionMatrix;
import fox.smoodi.engine.projection.matrix.ProjectionMatrixMode;
import fox.smoodi.engine.visual.sprite.Sprite;
import fox.smoodi.engine.visual.sprite.SpriteRenderer;
import fox.smoodi.engine.world.tiles.TileRenderer;
import fox.smoodi.engine.world.tiles.TileSet;

public class Renderer {
	
	//Creating our sub renderers
	private TileRenderer t_render = new TileRenderer();
	private SpriteRenderer s_render = new SpriteRenderer();
	
	//Create sub lists
	private List<SubRenderer>renderers = new LinkedList<>();
	private List<RenderObject> renderList = new LinkedList<>(); 
	private List<Sprite> spriteList = new LinkedList<>();
	private List<TileSet> tileSetList = new LinkedList<>();
	
	public Renderer() {
		 renderers.add(t_render);
		 renderers.add(s_render);
	}
	
	private ProjectionMatrix matrix = new ProjectionMatrix(ProjectionMatrixMode.MAXTRIX2D);
	
	
	public void render() {
		//for(RenderObject item : renderList)
			
			
			
			/*boolean shd = item.usesShader();
			Shader usedShader = NoShaderShader.object;
			if(shd)
				usedShader = item.getShader();
			
			usedShader.bind();
			Matrix4f projectionMatrix = SFoxEngine.getGameCamera().getProjection().mul(item.getTranformationMatrix()).mul(SFoxEngine.world.getMatrix());
			usedShader.setUniform("projection", projectionMatrix);
			item.render();
			
			Shader.unbind();
		}*/
		for(TileSet t : tileSetList) {
			t_render.render(t, SFoxEngine.getGameCamera());
			
		}
		for(Sprite s : spriteList) {
			s_render.render(s, SFoxEngine.getGameCamera());	
		}

	}
	
	public void refreshWindow() {
		for(SubRenderer i : renderers)
		{
			i.refreshWindow();
		}
	}
	
	
	public int addToList(RenderObject obj) {
		renderList.add(obj);
		return renderList.lastIndexOf(obj);
	}
	public void removeFromList(RenderObject obj) {
		renderList.remove(obj);
	}

	public int addToTileList(TileSet obj) {
		tileSetList.add(obj);
		return tileSetList.lastIndexOf(obj);
	}
	public void removeFromTileList(TileSet obj) {
		tileSetList.remove(obj);
	}

	public int addToSpriteList(Sprite obj) {
		spriteList.add(obj);
		return spriteList.lastIndexOf(obj);
	}
	public void removeFromSpriteList(Sprite obj) {
		spriteList.remove(obj);
	}
	
	
	public boolean containsInList(RenderObject renderObject) {
		return renderList.contains(renderObject);
	}
	
	public ProjectionMatrix getMatrix() { return matrix; }
}
