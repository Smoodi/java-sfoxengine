package fox.smoodi.engine.model;

public class Model {

	/**
	 * Basic vertices representing a 2 dimensional plane.
	 * Those are the standard vertices used if a model does not have their own.
	 */
	private static float[] modelVertices = new float[] {
			-1f, 1f, 0, //TOP LEFT       0
			1f, 1f, 0, //TOP RIGHT       1
			1f, -1f, 0, //BOTTOM RIGHT   2
			-1f, -1f, 0, //BOTTOM RIGHT  3
	};
	
	/**
	 * Basic texture mapping representing a 2 dimensional plane.
	 * Those are the standard values used if a model does not have their own.
	 */
	private static float[] textureMapping = new float[] {
			0,0,
			1,0,
			1,1,
			0,1
	};
	
	/**
	 * Basic indices representing the vertices in the GPU's memory (basically pointer) in order to save calculation time.
	 * Using indices allows us not to send the entire vertices of a model every time we render it.
	 * Those are the standard values used if a model does not have their own.
	 */
	private static int[] indices = new int[] {
			0,1,2,
			2,3,0
	};
	
	/**
	 * Returns the vertices stored in the model with a scaling of 1.0f
	 * @return The vertices array.
	 */
	public static float[] getVertices() {
		return modelVertices;
	}
	
	/**
	 * Returns the vertices stored in the model with a specific scaling.
	 * @return The vertices array.
	 */
	public static float[] getVertices(float scale) {
		float[] output = modelVertices;
		for(int i=0; i<output.length; i++) {
			output[i] = output[i] * scale;
		}
		return output;
	}
	/**
	 * Returns the texture coordinates stored in the model with a scaling of 1.0f.
	 * @return The texture coordinates array.
	 */
	public static float[] getTextureCoords() {
		return textureMapping;
	}
	/**
	 * Returns the texture coordinates stored in the model with a specific scaling.
	 * @return The texture coordinates array.
	 */
	public static float[] getTextureCoords(float scale) {
		float[] output = textureMapping;
		for(int i=0; i<output.length; i++) {
			output[i] = output[i] * scale;
		}
		return output;
	}
	/**
	 * Returns the Indices stored in the model.
	 * @return The indices array.
	 */
	public static int[] getIndices() {
		return indices;
	}
}
