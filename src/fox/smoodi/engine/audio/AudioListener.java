package fox.smoodi.engine.audio;

import static org.lwjgl.openal.AL10.AL_ORIENTATION;
import static org.lwjgl.openal.AL10.AL_POSITION;
import static org.lwjgl.openal.AL10.AL_VELOCITY;
import static org.lwjgl.openal.AL10.alListener3f;
import static org.lwjgl.openal.AL10.alListenerfv;

import org.joml.Vector3f;

import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location3D;

public class AudioListener {
	
	public Location position;

	/**
	 * Creates a listener at the origin.
	 */
	public AudioListener() {
		this(new Location3D(0,0,0));
	}

	/**
	 * Creates a listener at a given position.
	 * @param pos The position of the listener.
	 */
	public AudioListener(Location pos) {
		
		this.position = pos;

			alListener3f(AL_POSITION, position.getX(), position.getY(), position.getZ() );
			alListener3f(AL_VELOCITY, 0, 0, 0);
		
	}
	
	/**
	 * Sets the listener's speed. Can be used for a doppler effect for example. For more information see the OpenAL documentation.
	 * @param speed The speed vector.
	 */
	public void setSpeed(Vector3f speed) {
		
		alListener3f(AL_VELOCITY, speed.x, speed.y, speed.z);
		
	}
	
	/**
	 * Sets the position of the listener.
	 * @param position The position of the listener.
	 */
	public void setPosition(Vector3f position) {
        alListener3f(AL_POSITION, position.x, position.y, position.z);
    }
	
	/**
	 * Sets the position of the listener.
	 * @param position The position of the listener.
	 */
	public void setPosition(Location position) {
        alListener3f(AL_POSITION, position.getX(), position.getY(), position.getZ());
    }
	
	
	/**
	 * Sets the orientation of the listener. For more information see the OpenAL documentation.
	 * @param at The at vector of the listener. 
	 * @param up The up vector of the listener.
	 */
    public void setOrientation(Vector3f at, Vector3f up) {
        float[] data = new float[6];
        data[0] = at.x;
        data[1] = at.y;
        data[2] = at.z;
        data[3] = up.x;
        data[4] = up.y;
        data[5] = up.z;
        alListenerfv(AL_ORIENTATION, data);
    }
	

}
