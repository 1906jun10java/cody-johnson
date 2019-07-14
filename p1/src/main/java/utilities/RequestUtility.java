package utilities;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestUtility {
	public static String bodyToJson(HttpServletRequest req)
	throws IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String json = buffer.toString();

		return json;
	}
}
