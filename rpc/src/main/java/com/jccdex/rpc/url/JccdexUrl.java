package com.jccdex.rpc.url;

public class JccdexUrl extends BaseUrl {
	private String host;
	private int port;
	private Boolean secure;

	public JccdexUrl() {
		this.host = "";
	}

	public JccdexUrl(String host, Boolean secure, int port) {
		this.host = host;
		this.port = port;
		this.secure = secure;
	}

	public JccdexUrl(String host, Boolean secure) {
		this.host = host;
		this.secure = secure;
		this.port = secure ? 443 : 80;
	}

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
