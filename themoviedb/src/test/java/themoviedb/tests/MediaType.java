package themoviedb.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import themoviedb.utils.ConfigUtils;

/**
 * Testing web service media type. themoviedb web service only supports json and
 * this helps in checking all the response type.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/getting-started
 * 
 * @author mounikaenugala
 * 
 *
 */
public class MediaType {

	/*
	 * mediaTypeTest() method helps in checking the media type of the
	 * webservice.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void mediaTypeTest() throws ClientProtocolException, IOException {
		String jsonMimeType = "application/json";
		HttpUriRequest request = new HttpGet(
				"https://api.themoviedb.org/3/movie/76341?api_key=" + ConfigUtils.getAPIKey());
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
		assertEquals(jsonMimeType, mimeType);
	}

}
