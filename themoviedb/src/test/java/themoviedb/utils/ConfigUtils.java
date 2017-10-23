package themoviedb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utils class to get the valid api key from the test.properties file.
 * 
 * @author mounikaenugala
 * 
 *
 */
public class ConfigUtils {
	public static String getAPIKey() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("test.properties");
			prop.load(input);
			return prop.getProperty("MOVIE_DB_API_KEY");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String getInvalidAPIKey() {
		return getAPIKey() + getAPIKey();
	}
}
