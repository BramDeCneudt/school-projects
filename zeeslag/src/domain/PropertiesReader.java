package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public Properties getProperties() {
		File file = new File("properties.properties");
		InputStream fileInput;

		Properties properties = new Properties();
		try {
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}