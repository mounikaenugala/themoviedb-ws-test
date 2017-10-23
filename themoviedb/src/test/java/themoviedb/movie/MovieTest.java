package themoviedb.movie;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import constants.APIStatusCodes;
import model.Movie;
import model.Status;
import themoviedb.utils.ConfigUtils;
import themoviedb.utils.StringToObjectUtils;

/**
 * Testing web service URL /movie/{movie_id} endpoint for testing the primary
 * information about a movie.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/movies/get-movie-details
 * 
 * @author mounikaenugala
 * 
 *
 */
public class MovieTest {

	/*
	 * searchMoviesTest() method checks for valid movie id and validates
	 * information like movie id & title. There are many information like number
	 * of votes and ratings which are bound to change during each web service
	 * calls so those fields are ignored.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void searchMoviesTest() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/movie/278?language=en-US&api_key=" + ConfigUtils.getAPIKey()).get()
				.build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Movie movie = StringToObjectUtils.responseStringToObject(jsonData, Movie.class);
		assertEquals(movie.getId(), 278);
		assertEquals(movie.getTitle(), "The Shawshank Redemption");
	}

	/*
	 * searchMoviesNotFoundTest() method checks for invalid movie id, and the
	 * assumption is it will not be a valid id in the near feature. It expects
	 * return status code of 34.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void searchMoviesNotFoundTest() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/movie/999999999?language=en-US&api_key=" + ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Status status = StringToObjectUtils.responseStringToObject(jsonData, Status.class);
		assertEquals(status.getStatus_code(), 34);
		assertEquals(status.getStatus_message(), APIStatusCodes.getAPIStatusCodes(34));
	}
}
