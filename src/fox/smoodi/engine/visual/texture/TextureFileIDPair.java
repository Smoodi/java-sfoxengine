package fox.smoodi.engine.visual.texture;

public class TextureFileIDPair {

	private int id;
	private String filename;
	
	
	/*
	 * A Texture File ID Pair cannot be changed after it has been initialized.
	 * It contains the storage position (filname) of the texture on the hard drive
	 * and the id it uses in the engine. 
	 */
	public TextureFileIDPair(int id, String filename) {
		this.id = id;
		this.filename = filename;
	}

	public long getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}
	
}
