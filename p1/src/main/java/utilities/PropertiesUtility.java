package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesUtility {
	private static Properties properties;

	static Properties getProperties() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (PropertiesUtility.properties == null) {
			properties = new Properties();
			try {
				String dbProps = "database.properties";
				InputStream resource = loader.getResourceAsStream(dbProps);
				properties.load(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
}
