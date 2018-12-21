package com.jccdex.rpc.api;

import okhttp3.Response;

public class CommUtils {

	public static final int HTTP_STATUS_OK = 200;

	public static final int HTTP_STATUS_NOT_MODIFIED = 304;

	public static Boolean isSuccessful(int statusCode) {
		return statusCode == HTTP_STATUS_OK || statusCode == HTTP_STATUS_NOT_MODIFIED;
	}

	public static String formatExceptionMessage(Response response) {
		return response.code() + ": " + response.message();
	}
}
