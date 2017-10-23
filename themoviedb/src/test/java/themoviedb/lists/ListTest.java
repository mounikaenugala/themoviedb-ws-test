package themoviedb.lists;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import constants.APIStatusCodes;
import model.Status;
import themoviedb.utils.ConfigUtils;
import themoviedb.utils.StringToObjectUtils;

/**
 * Testing web service URL endpoint for creating & deleting list, lists can be
 * created for movies to be added for future reference by users.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/lists/get-list-details
 * 
 * @author mounikaenugala
 * 
 *
 */
public class ListTest {

	/*
	 * Create list method tests whether a new list can be created using api key
	 * which doesn't have create list permission.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void createList() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,
				"{\"name\":\"This is my awesome test list.\",\"description\":\"Just an awesome list dawg.\",\"language\":\"en\"}");
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/list?api_key=" + ConfigUtils.getAPIKey()).post(body)
				.addHeader("content-type", "application/json;charset=utf-8").build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Status status = StringToObjectUtils.responseStringToObject(jsonData, Status.class);
		assertEquals(status.getStatus_code(), 3);
		assertEquals(status.getStatus_message(), APIStatusCodes.getAPIStatusCodes(3));
	}

	/*
	 * Delete list method tests whether a list can be deleted using api key
	 * which doesn't have delete list permission.
	 * 
	 * @ throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * 
	 * @ throws IOException - Signals that an I/O exception of some sort has
	 * occurred. This class is the general class of exceptions produced by
	 * failed or interrupted I/O operations.
	 */
	@Test
	public void deleteList() throws ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody body = RequestBody.create(mediaType, "{}");
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/list/36621?api_key=" + ConfigUtils.getAPIKey()).delete(body).build();
		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
		Status status = StringToObjectUtils.responseStringToObject(jsonData, Status.class);
		assertEquals(status.getStatus_code(), 3);
		assertEquals(status.getStatus_message(), APIStatusCodes.getAPIStatusCodes(3));
	}
}
