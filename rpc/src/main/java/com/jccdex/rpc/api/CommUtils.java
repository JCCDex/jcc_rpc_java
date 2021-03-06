package com.jccdex.rpc.api;

import okhttp3.Response;

public class CommUtils {

	public static final int HTTP_STATUS_OK = 200;

	public static Boolean isSuccessful(int statusCode) {
		return statusCode == HTTP_STATUS_OK;
	}

	public static String formatExceptionMessage(Response response) {
		return response.code() + ": " + response.message();
	}

	public static Boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
}
