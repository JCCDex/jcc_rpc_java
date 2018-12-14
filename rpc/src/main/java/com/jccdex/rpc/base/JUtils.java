package com.jccdex.rpc.base;

public class JUtils {

	public static String formatUrl(String host, int port, Boolean secure) {
		String protocol;
		if (secure) {
			protocol = "https://";
		} else {
			protocol = "http://";
		}
		return protocol + host + ":" + port;
	}
}
