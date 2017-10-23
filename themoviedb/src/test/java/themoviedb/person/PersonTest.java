package themoviedb.person;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import constants.APIStatusCodes;
import model.Errors;
import model.People;
import model.Status;
import themoviedb.utils.ConfigUtils;
import themoviedb.utils.StringToObjectUtils;

/**
 * Testing web service URL for people api. This gets the primary person details
 * by id.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/people
 * 
 * @author mounikaenugala
 * 
 *
 */
public class PersonTest {

	/*
	 * invalidPersonIDType() - Checks the api for invalid person id type. It
	 * expects integer and we pass a random string. It is expected to return
	 * error message the 'id is not a valid integer'
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void invalidPersonIDType() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(
				"https://api.themoviedb.org/3/person/SomePersonID?language=en-US&api_key=" + ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Errors errors = StringToObjectUtils.responseStringToObject(jsonData, Errors.class);
		assertEquals(errors.getErrors().length, 1);
		assertEquals(errors.getErrors()[0], "id is not a valid integer");
	}

	/*
	 * invalidPersonID() - Checks the api for invalid person id. It expects
	 * valid person id and we pass a random id which doesn't exist now. It is
	 * expected to return error code and message.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void invalidPersonID() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/person/999999999?language=en-US&api_key=" + ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Status status = StringToObjectUtils.responseStringToObject(jsonData, Status.class);
		assertEquals(status.getStatus_code(), 34);
		assertEquals(status.getStatus_message(), APIStatusCodes.getAPIStatusCodes(34));
	}

	/*
	 * validPersonID() - checks person information like name, gender and place
	 * of birth for a valid person id. Many fields which returned are bound to
	 * change over time and cannot be tested. Even name, place of birth can be
	 * changed in event of update or deletion. Since we don't have access over
	 * the create, update api's we are limited to testing with these
	 * assumptions.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void validPersonID() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/person/3223?language=en-US&api_key=" + ConfigUtils.getAPIKey()).get()
				.build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		People people = StringToObjectUtils.responseStringToObject(jsonData, People.class);
		assertEquals(people.getGender(), 2);
		assertEquals(people.getName(), "Robert Downey Jr.");
		assertEquals(people.getPlace_of_birth(), "Manhattan, New York, USA");
	}
}
