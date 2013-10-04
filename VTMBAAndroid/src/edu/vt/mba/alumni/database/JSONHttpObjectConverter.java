package edu.vt.mba.alumni.database;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHttpObjectConverter {
	
	public static final String TAG = JSONHttpObjectConverter.class.getName();

	public static <T> T handleResponse(HttpResponse serverResponse, Class<T> typeOfResponse) {
		String contentString = HttpResponseUtils.convertHttpResponseToString(serverResponse);
		return handleResponse(contentString,typeOfResponse);
	}

	public static <T> T handleResponse(String contentString, Class<T> typeOfResponse) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T responseObject = null;
		try {
			responseObject = mapper.readValue(contentString,typeOfResponse);
		} catch (JsonParseException e) {
			Log.e(TAG,"JsonParseException " + e.getMessage());
		} catch (JsonMappingException e) {
			Log.e(TAG,"JsonMappingException " + e.getMessage());
		} catch (IllegalStateException e) {
			Log.e(TAG,"IllegalStateException " + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG,"IOException " + e.getMessage());
		}
		return responseObject;
	}
	
	public static <T> T handleResponse(JsonParser jp, Class<T> typeOfResponse) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T responseObject = null;
		try {
			responseObject = mapper.readValue(jp,typeOfResponse);
		} catch (JsonParseException e) {
			Log.e(TAG,"JsonParseException " + e.getMessage());
		} catch (JsonMappingException e) {
			Log.e(TAG,"JsonMappingException " + e.getMessage());
		} catch (IllegalStateException e) {
			Log.e(TAG,"IllegalStateException " + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG,"IOException " + e.getMessage());
		}
		return responseObject;
	}

}
