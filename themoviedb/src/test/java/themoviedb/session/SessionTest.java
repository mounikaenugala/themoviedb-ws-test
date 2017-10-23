package themoviedb.session;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import constants.APIStatusCodes;
import model.Session;
import model.Status;
import themoviedb.utils.ConfigUtils;
import themoviedb.utils.StringToObjectUtils;

/**
 * Testing web service URL for authentication For session & guest session
 * 
 * For Session - This is the session that can now be used to write user data.
 * 
 * Reference Doc:
 * https://developers.themoviedb.org/3/authentication/create-session
 * 
 * For Guest session - Guest sessions are a type of session that will let a user
 * rate movies and TV shows but not require them to have a TMDb user account.
 * 
 * Reference Doc:
 * https://developers.themoviedb.org/3/authentication/create-guest-session
 * 
 * @author mounikaenugala
 * 
 *
 */
public class SessionTest {

	/*
	 * testGuestSession() method checks creation of guest session using a valid
	 * api key. It returns session id and expiration date.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void testGuestSession() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/authentication/guest_session/new?api_key=" + ConfigUtils.getAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Session session = StringToObjectUtils.responseStringToObject(jsonData, Session.class);
		assertEquals(session.isSuccess(), true);
		assertNotNull(session.getGuest_session_id());
		assertNotNull(session.getExpires_at());
	}

	/*
	 * testInvalidGuestSession() method checks for error in creating session
	 * because of invalid api key.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void testInvalidGuestSession() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/authentication/guest_session/new?api_key="
						+ ConfigUtils.getInvalidAPIKey())
				.get().build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Session session = StringToObjectUtils.responseStringToObject(jsonData, Session.class);
		assertEquals(session.isSuccess(), false);
		assertNull(session.getGuest_session_id());
		assertNull(session.getExpires_at());
	}

	/*
	 * createSession() - checks for creating session using api key but it
	 * requires user authentication using url. So failure of this create session
	 * is expected.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void createSession() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/authentication/session/new?api_key=" + ConfigUtils.getAPIKey()).get()
				.build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Status status = StringToObjectUtils.responseStringToObject(jsonData, Status.class);
		assertEquals(status.getStatus_code(), 6);
		assertEquals(status.getStatus_message(), APIStatusCodes.getAPIStatusCodes(6));
	}
}
