package com.jccdex.rpc.url;

public class JUrl {
	private String host;
	private int port;
	private Boolean secure;

	public JUrl() {
		this.host = "";
	}

	public JUrl(String host, Boolean secure, int port) {
		this.host = host;
		this.port = port;
		this.secure = secure;
	}

	public JUrl(String host, Boolean secure) {
		this.host = host;
		this.secure = secure;
		this.port = secure ? 443 : 80;
	}

	public String getUrl() {
		String url = "";
		if (host == url) {
			return url;
		}
		String protocol = secure ? "https://" : "http://";
		return protocol + host + ":" + port;
	}
}
