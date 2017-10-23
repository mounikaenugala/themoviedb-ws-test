package themoviedb.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import themoviedb.utils.ConfigUtils;

/**
 * Testing web service to return valid response codes.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/getting-started
 * 
 * @author mounikaenugala
 * 
 *
 */
public class TestHTTPResponse {

	/*
	 * apiHTTPResponse200Status() method checks for response code 200 for a
	 * valid web service call.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void apiHTTPResponse200Status() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(
				"https://api.themoviedb.org/3/movie/76341?api_key=" + ConfigUtils.getAPIKey());
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	/*
	 * apiHTTPResponse401Status() method checks for response code 401 for a
	 * unauthorized web service call using invalid api key.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void apiHTTPResponse401Status() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(
				"https://api.themoviedb.org/3/movie/76341?api_key=" + ConfigUtils.getInvalidAPIKey());
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_UNAUTHORIZED);
	}

}
