package fox.smoodi.engine.shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShaderLoader {

	public static String readShader(String filename) {
		String result = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result += buffer + "\n";
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
