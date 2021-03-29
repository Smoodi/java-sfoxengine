package fox.smoodi.engine.visual.texture;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import fox.smoodi.engine.SFoxEngine;

public class AnimatedTexture extends Texture{

	private Texture[] frames;
	private int pointer;
	private double elapsed;
	private double current;
	private double last;
	private double fps;
	
	public AnimatedTexture ( int amount, int fps, String path, String name, String extension) {
		
		this.pointer = 0;
		this.elapsed = 0;
		this.current = 0;
		this.last = SFoxEngine.getTime();
		this.fps = 1.0 / (double) fps;
		
		this.frames = new Texture[amount];
		for(int i=0;i<amount; i++) {
			String counterString = "000";
			String c = Integer.toString(i);
			if(c.length() == 1)
				counterString = "00"+c;
			else if(c.length() == 2)
				counterString = "0"+c;
			else
				counterString = c;
			this.frames[i] = TextureHandler.loadTexture(name+counterString, (path + name + "_"+ counterString + "."+extension));
		}
		
	}
	
	public void bind() { bind(0); }
	
	public void bind( int sampler ) {
		
		this.current = SFoxEngine.getTime();
		this.elapsed += current - last;
		
		if(elapsed >= fps) {
			elapsed -= fps;
			pointer ++;
		}

		if(pointer >= frames.length) pointer = 0;
		
		this.last = current;
		
		
		
		frames[pointer].bind(sampler);
		
	}
	
	public void unbind() {
		glActiveTexture(0);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
}
