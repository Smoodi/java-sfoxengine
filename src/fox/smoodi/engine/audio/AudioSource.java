package fox.smoodi.engine.audio;

import fox.smoodi.engine.projection.location.Location;
import fox.smoodi.engine.projection.location.Location2D;
import fox.smoodi.engine.projection.location.Location3D;
import static org.lwjgl.openal.AL10.*;

import org.joml.Vector3f;

public class AudioSource {

	private Location position;
	private final int pointer;
	
	/**
	 * Creates a new AudioSource at a given position.
	 * @param x The x coordinate of the position.
	 * @param y The y coordinate of the position.
	 * @param z The z coordinate of the position.
	 * @param loop Whether the sounds playing from this source should loop or not.
	 * @param relative Wheter the source should be relative to the listener.
	 */
	public AudioSource( float x, float y, float z , boolean loop, boolean relative ) {
		
		this.position = new Location3D( x, y, z );
		pointer = alGenSources();
		
		if(loop) {
			alSourcei(pointer, AL_LOOPING, AL_TRUE);
		}
		if(relative) {
			alSourcei(pointer, AL_SOURCE_RELATIVE, AL_TRUE);
		}
		
	}
	
	/**
	 * Creates a new AudioSource at a given position. The z coordinate will be 0.
	 * @param x The x coordinate of the position.
	 * @param y The y coordinate of the position.
	 * @param loop Whether the sounds playing from this source should loop or not.
	 * @param relative Wheter the source should be relative to the listener.
	 */
	public AudioSource( float x, float y , boolean loop, boolean relative ) {
		
		this.position = new Location2D( x, y );
		pointer = alGenSources();
		
	}
	
	/**
	 * Creates a new AudioSource at the origin.
	 * @param loop Whether the sounds playing from this source should loop or not.
	 * @param relative Wheter the source should be relative to the listener.
	 */
	public AudioSource (boolean loop, boolean relative) {
		
		pointer = alGenSources();
		
		if(loop) {
			alSourcei(pointer, AL_LOOPING, AL_TRUE);
		}
		if(relative) {
			alSourcei(pointer, AL_SOURCE_RELATIVE, AL_TRUE);
		}
		
	}
	
	/**
	 * Stops the currently played sound and sets the sources sound.
	 * @param sound The sound object of the sound that should be played.
	 */
    public void setSound(Sound sound) {
        stop();
        alSourcei(pointer, AL_BUFFER, sound.getBufferId());
    }

    /**
     * Sets the position of the source.
     * @param position The position vector.
     */
    public void setPosition(Vector3f position) {
        alSource3f(pointer, AL_POSITION, position.x, position.y, position.z);
    }

    /**
     * Sets the speed of the source. Can be used for a doppler effect for example. For more information read the OpenAL documentation.
     * @param speed The speed vector.
     */
    public void setSpeed(Vector3f speed) {
        alSource3f(pointer, AL_VELOCITY, speed.x, speed.y, speed.z);
    }

    /**
     * Sets the "sound" gain of the source.
     * @param gain The gain of the source.
     */
    public void setGain(float gain) {
        alSourcef(pointer, AL_GAIN, gain);
    }

    /**
     * Sets a custom source property. For more information and possible parameters and values see the OpenAL or LWJGL documentation. 
     * @param param The parameter that should be set.
     * @param value The value that should be set.
     */
    public void setProperty(int param, float value) {
        alSourcef(pointer, param, value);
    }

    /**
     * Plays or resumes the sources sound.
     */
    public void play() {
        alSourcePlay(pointer);
    }

    /**
     * Determines if the source if currently playing a sound.
     * @return True if the source is playing otherwise false.
     */
    public boolean isPlaying() {
        return alGetSourcei(pointer, AL_SOURCE_STATE) == AL_PLAYING;
    }

    /**
     * Pauses the sources sound.
     */
    public void pause() {
        alSourcePause(pointer);
    }


    /**
     * Stops the sources sound.
     */
    public void stop() {
        alSourceStop(pointer);
    }

    /**
     * Stops the source, deletes the source and therefore frees the memory.
     */
    public void free() {
        stop();
        alDeleteSources(pointer);
    }
	
    /**
     * Returns the OpenAL source pointer.
     * @return The pointer value.
     */
	int getPointer() { return pointer; }
	
}
