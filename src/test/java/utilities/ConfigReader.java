package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	// Load config file
	public static void loadConfig() {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
		}
	}

	// Get property value
	public static String getProperty(String key) {

		if (prop == null) {
			loadConfig();
		}
		return prop.getProperty(key);
	}
}
