package fox.smoodi.engine.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class Shader {

	private int programID;
	private int vertexShader;
	private int fragmentShader;
	
	public static final int UNIFORM_BUFFER_SIZE_SMALL = 4;
	public static final int UNIFORM_BUFFER_SIZE_NORMAL = 16;
	
	
	public Shader(String shadername) {
	
		programID = glCreateProgram();
		
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShader, ShaderLoader.readShader(shadername+".sfvs"));
		glCompileShader(vertexShader);
		
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) != 1)
		{
			//The shader creating errors.
			System.err.println("SmoodiFox Game Engine Shader Loader:\nError when compiling "+shadername+".sfvs.\n"+glGetShaderInfoLog(vertexShader));
			System.exit(1);
		}
		
		
		//FRAGMENT SHADER
		
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(fragmentShader, ShaderLoader.readShader(shadername+".sffs"));
		glCompileShader(fragmentShader);
		
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS)  != 1)
		{
			//The shader creating errors.
			System.err.println("SmoodiFox Game Engine Shader Loader:\nError when compiling "+shadername+".sffs.\n"+glGetShaderInfoLog(fragmentShader));
			System.exit(1);
		}
		
		
		//Attach it
		
		glAttachShader(programID, vertexShader);
		glAttachShader(programID, fragmentShader);
		
		glBindAttribLocation(programID, 0, "vertices");
		glBindAttribLocation(programID, 1, "textures");
		
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("SmoodiFox Game Engine Shader Loader:\nError when linking shader "+shadername+"\n"+glGetProgramInfoLog(programID));
			System.exit(1);
		}
		
		glValidateProgram(programID);
		if(glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
			System.err.println("SmoodiFox Game Engine Shader Loader:\nError when validating shader "+shadername+"\n"+glGetProgramInfoLog(programID));
			System.exit(1);
		}
	}
	
	public void setShaderUniforms(Matrix4f projectionMatrix) {
		
		setUniform("sampler", 0 );
		setUniform("projection", projectionMatrix );
		
	}
	
	
	protected void setUniform(String name, int value) {
		int loc = glGetUniformLocation(programID, name);
		if(loc != -1)
			glUniform1i(loc,value);
		
	}
	
	protected void setUniform(String name, Matrix4f value) {
		int loc = glGetUniformLocation(programID, name);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		if(loc != -1)
			glUniformMatrix4fv(loc, false, buffer);
		
	}
	
	
	public void bind() {
		glUseProgram(programID);
	}
	
	public static void unbind() {
		glUseProgram(0);
	}
	
}
