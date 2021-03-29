package fox.smoodi.engine.model;

public class ModelPlane extends Model {

	/**
	 * Overwrites the basic model vertices. The plane model is defined by two triangles and four points.
	 * The first line represents the top left corner, the second the top right corner, the third one the bottom right
	 * and the fourth one is representing the bottom right.
	 */
	private static float[] modelVertices = new float[] {
			-0.5f, 0.5f, 0, //TOP LEFT       0
			0.5f, 0.5f, 0, //TOP RIGHT       1
			0.5f, -0.5f, 0, //BOTTOM RIGHT   2
			-0.5f, -0.5f, 0, //BOTTOM RIGHT  3
	};
	
	/**
	 * Overwrites the basic model texture coordinates.
	 */
	private static float[] textureMapping = new float[] {
			0,0,
			1,0,
			1,1,
			0,1
	};
	
	/**
	 * Overwrites the basic model indices.
	 */
	private static int[] indices = new int[] {
			0,1,2,
			2,3,0
	};
	
}
