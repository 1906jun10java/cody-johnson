package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtility {
	private static Properties properties;

	public static Properties getProperties() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		if (PropertiesUtility.properties == null) {
			properties = new Properties();
			try {
				String dbProps = "database.properties";
				InputStream resource = classLoader.getResourceAsStream(dbProps);
				properties.load(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
}
