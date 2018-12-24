package com.jccdex.rpc.url;

import javax.validation.constraints.NotNull;

public class JccdexUrl extends BaseUrl {
	private String host;
	private int port;
	private Boolean secure;

	/**
	 * url is empty.
	 */
	public JccdexUrl() {
		this.host = "";
	}

	/**
	 * if the secure is true, url is startwith https://,otherwise is startwith
	 * http://.
	 * 
	 * @param host
	 * @param secure
	 * @param port
	 */
	public JccdexUrl(@NotNull String host, @NotNull Boolean secure, @NotNull int port) {
		this.host = host;
		this.port = port;
		this.secure = secure;
	}

	/**
	 * if the secure is true, url is startwith https://,port is 443,otherwise is startwith
	 * http://,port is 80.
	 * 
	 * @param host
	 * @param secure
	 */
	public JccdexUrl(@NotNull String host, @NotNull Boolean secure) {
		this.host = host;
		this.secure = secure;
		this.port = secure ? 443 : 80;
	}

	/**
	 * @return https:// or http:// + host : port
	 */
	@Override
	public String getUrl() {
		String url = "";
		if (host == url) {
			return url;
		}
		String protocol = secure ? "https://" : "http://";
		return protocol + host + ":" + port;
	}

}
