package edu.vt.mba.alumni.database;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import android.util.Log;

public class HttpResponseUtils {
	
	private static final String TAG = HttpResponseUtils.class.getName();
	
	public static String convertHttpResponseToString(HttpResponse serverResponse) {
		InputStream contentStream = null;
		String contentString = null;
		try {
			contentStream = serverResponse.getEntity().getContent();
		} catch (IllegalStateException e) {
			Log.e(TAG, "IllegalStateException " + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "IOException " + e.getMessage());
		}
		try {
			contentString = IOUtils.toString(contentStream);
		} catch (IOException e) {
			Log.e(TAG, "IOException " + e.getMessage());
		}
		return contentString;
	}

}
