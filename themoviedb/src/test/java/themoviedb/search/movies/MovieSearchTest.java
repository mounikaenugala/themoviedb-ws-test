package themoviedb.search.movies;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import model.Movie;
import model.Result;
import themoviedb.utils.ConfigUtils;
import themoviedb.utils.StringToObjectUtils;

/**
 * Testing web service URL for movie search api. this api helps in searching for
 * movies. Using different parameters like language, query string, page, include
 * adult, region, year, primary release year. We test here for two scenarios
 * like search for a valid movie and invalid movie.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/search/search-movies
 * 
 * @author mounikaenugala
 * 
 *
 */
public class MovieSearchTest {

	/*
	 * searchMoviesTest() - method checks for valid movie search results where
	 * it returns exactly one movie result. Which is an assumption that there is
	 * only one movie named 'The Shawshank Redemption' released in the year
	 * 1994. There are many fields returned by movie are ignored for testing due
	 * to the nature of data bound for changing frequently.
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
				.url("https://api.themoviedb.org/3/search/movie?year=1994&include_adult=false&page=1&query=The%20Shawshank%20Redemption&language=en-US&api_key="
						+ ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Result resource = StringToObjectUtils.responseStringToObject(jsonData, Result.class);
		assertEquals(resource.getPage(), 1);
		assertEquals(resource.getTotal_pages(), 1);
		assertEquals(resource.getTotal_results(), 1);
		assertEquals(resource.getResults().length, 1);
		Movie movie = resource.getResults()[0];
		assertEquals(movie.getId(), 278);
		assertEquals(movie.getTitle(), "The Shawshank Redemption");
	}

	/*
	 * searchMoviesNotFoundTest() - method checks for movie which is not
	 * present. This works under the assumption that there is no movie called
	 * 'The Shawshank Redemption' released in year 2200.
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
				.url("https://api.themoviedb.org/3/search/movie?year=2200&include_adult=false&page=1&query=The%20Shawshank%20Redemption&language=en-US&api_key="
						+ ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Result resource = StringToObjectUtils.responseStringToObject(jsonData, Result.class);
		assertEquals(resource.getPage(), 1);
		assertEquals(resource.getTotal_pages(), 1);
		assertEquals(resource.getTotal_results(), 0);
		assertEquals(resource.getResults().length, 0);
	}
}
