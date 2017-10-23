package themoviedb.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utils class to convert string to valid class object.
 * 
 * @author mounikaenugala
 * 
 *
 */
public class StringToObjectUtils {
	public static <T> T responseStringToObject(String jsonResponse, Class<T> clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(jsonResponse, clazz);
	}
}
