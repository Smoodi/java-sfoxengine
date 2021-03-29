package fox.smoodi.engine.audio;

import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;

import java.util.HashMap;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import fox.smoodi.engine.SFoxEngine;

public class AudioHandler {

	private String deviceName;
	private long device;
	private long context;
	
	private ALCCapabilities alc;
	private ALCapabilities al;
	
	private HashMap<String, Sound> sounds;
	private HashMap<String, AudioSource> sources;
	
	/**
	 * Creates a new AudioHandler object and instantly initializes it.
	 */
	public AudioHandler() {
		if(!init())
			throw new IllegalStateException("FAILED TO INIT OPENAL...");
	}
	
	/**
	 * Initializes the audio system, OpenAL and the AudioHandler.
	 * @return True if the initialization succeeded - otherwise false. 
	 */
	private boolean init() {
		alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
		device = alcOpenDevice(deviceName);
		
		//This is for the init of al
		int[] attributes = { 0 };
		context = alcCreateContext(device, attributes);
		alcMakeContextCurrent(context);
		
		alc = ALC.createCapabilities(device);
		al = AL.createCapabilities(alc);
		
		this.sounds = new HashMap<String, Sound>();
		this.sources = new HashMap<String, AudioSource>();
		
		return al.OpenAL10;
	}
	
	/**
	 * Frees all sounds and sources from the memory and turns off OpenAL, destroys the context and closes the device.
	 */
	public void free() {
		for(Sound sound : sounds.values()) {
			
			sound.free();
			
		}
		for(AudioSource source : sources.values()) {
			
			source.free();
			
		}
		
		alcDestroyContext(context);
		alcCloseDevice(device);
	}
	
	/**
	 * Adds a sound to the memory storage.
	*/
	public void addSound(String name, Sound sound) {
		if(name != "" && sound != null)
			this.sounds.put(name, sound);
	}
	
	/**
	 * Adds a source to the memory storage.
	*/
	public void addSource(String name, AudioSource source) {
		if(name != "" && source != null)
			this.sources.put(name, source);
	}
	
	
	/** Returns the current listener.
	    @return The AudioListener of the current camera.
	*/
	public AudioListener getListener() {
		return SFoxEngine.gameCamera.listener;
	}
	
	/**
	 * Checks if the sound is existing in the memory storage.
	 * @param name Name the sound is stored as.
	 * @return True if the sound exists otherwise false.
	 */
	public boolean soundExists(String name) {
		return sounds.containsKey(name);
	}
	
	/**
	 * Checks if the source is existing in the memory storage.
	 * @param name Name the source is stored as.
	 * @return True if the source exists otherwise false.
	 */
	public boolean sourceExists(String name) {
		return sources.containsKey(name);
	}
	
	/**
	 * Returns the sound object which has been stored. 
	 * @param name The name of the sound that has been stored.
	 * @return The sound object.
	 */
	public Sound getSound(String name) {
		return sounds.get(name);
	}
	
	/**
	 * Returns the source object which has been stored.
	 * @param name The name of the source t hat has been stored.
	 * @return The source object.
	 */
	public AudioSource getSource(String name) {
		return sources.get(name);
	}
	
	/**
	 * Creates a new Sound object and stores it in the memory storage.
	 * @param name The name of the sound it should be stored as.
	 * @param filename The path to the OGG Vorbis sound file. 
	 * @return Returns the sound object that has been created.
	 * @throws Exception if the Sound could not be loaded.
	 */
	public Sound loadSound(String name, String filename) throws Exception {
		
		if(soundExists(name)) return getSound(name);
		
		Sound s = new Sound(filename);
		
		sounds.put(name, s);
		
		return s;
	}
	
	/**
	 * Creates a new audio source - basically an emitter which is being stored in the memory storage. The source is being positioned at the origin.
	 * @param name The name the source is being stored as.
	 * @param loop Whether the source should loop the played sounds.
	 * @param relative Whether the source should be relative to the listener. For more information see the OpenAL documentation. 
	 * @return The AudioSource object which has been created.
	 */
	public AudioSource createSource( String name, boolean loop, boolean relative ) {
		
		if(sourceExists(name)) return getSource(name);
		
		AudioSource s = new AudioSource(loop, relative);
		
		sources.put(name, s);
		
		return s;
	}
	
	/**
	 * Creates a new audio source - basically an emitter which is being stored in the memory storage. The source's z position is 0.
	 * @param name The name the source is being stored as.
	 * @param x The x position of the source.
	 * @param y The y position of the source.
	 * @param loop Whether the source should loop the played sounds.
	 * @param relative Whether the source should be relative to the listener. For more information see the OpenAL documentation.
	 * @return The AudioSource object which has been created.
	 */
	public AudioSource createSource( String name, float x, float y, boolean loop, boolean relative ) {
		
		if(sourceExists(name)) return getSource(name);
		
		AudioSource s = new AudioSource(x,y, loop, relative);
		
		sources.put(name, s);
		
		return s;
	}

	/**
	 * Creates a new audio source - basically an emitter which is being stored in the memory storage.
	 * @param name The name the source is being stored as.
	 * @param x The x position of the source.
	 * @param y The y position of the source.
	 * @param z The z position of the source.
	 * @param loop Whether the source should loop the played sounds.
	 * @param relative Whether the source should be relative to the listener. For more information see the OpenAL documentation.
	 * @return The AudioSource object which has been created.
	 */
	public AudioSource createSource( String name, float x, float y, float z, boolean loop, boolean relative ) {
		
		if(sourceExists(name)) return getSource(name);
		
		AudioSource s = new AudioSource(x,y,z, loop, relative);
	
		sources.put(name, s);
		
		return s;
	}

	/**
	 * Plays a a sound at a specific source.
	 * @param sound The name of the sound that has been stored in the memory storage and should be played.
	 * @param source The name of the source that has been stored in the memory storage and where it should played from.
	 * @return True if the sound could be played otherwise false.
	 */
	public boolean playSound( String sound, String source ) {
		
		if(!soundExists(sound)) return false;
		if(!sourceExists(source)) return false;
		
		Sound a_sound = getSound(sound);
		AudioSource a_source = getSource(source);
		
		if(!a_source.isPlaying()) {
			a_source.setSound(a_sound);
			a_source.play();
			return true;
		}
		return false;
	}
	
	/**
	 * Plays a a sound at a specific source.
	 * @param sound The sound object of the sound that should be played.
	 * @param source The source object of the source the sound should be played from.
	 * @return True if the sound could be played otherwise false.
	 */
	public boolean playSound( Sound sound, AudioSource source ) {
		
		if(sound == null) return false;
		if(source == null) return false;
		
		
		if(!source.isPlaying()) {
			source.setSound(sound);
			source.play();
			return true;
		}
		
		return false;
	}
	/**
	 * Plays a a sound at a specific source.
	 * @param sound The name of the sound that has been stored in the memory storage and should be played.
	 * @param source The source object of the source the sound should be played from.
	 * @return True if the sound could be played otherwise false.
	 */
	public boolean playSound( String sound, AudioSource source ) {
		
		if(!soundExists(sound)) return false;
		if(source == null) return false;
		
		Sound a_sound = getSound(sound);		
		
		if(!source.isPlaying()) {
			source.setSound(a_sound);
			source.play();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Plays a a sound at a specific source.
	 * @param sound The sound object of the sound that should be played.
	 * @param source The name of the source that has been stored in the memory storage and where it should played from.
	 * @return True if the sound could be played otherwise false.
	 */
	public boolean playSound( Sound sound, String source ) {
		
		if(sound == null) return false;
		if(!sourceExists(source)) return false;		
		
		AudioSource a_source = getSource(source);
		
		if(!a_source.isPlaying()) {
			a_source.setSound(sound);
			a_source.play();
			return true;
		}
		
		return false;
	}
}

