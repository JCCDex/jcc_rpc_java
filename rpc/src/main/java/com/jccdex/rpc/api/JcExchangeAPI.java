package com.jccdex.rpc.api;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.base.JUtils;

public class JcExchangeAPI implements BaseExchangeAPI {

	private String host;

	private int port;

	private Boolean secure;

	private JcExchangeAPI() {
	}

	public static JcExchangeAPI getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JcExchangeAPI singleton;

		private Singleton() {
			singleton = new JcExchangeAPI();
		}

		public JcExchangeAPI getInstance() {
			return singleton;
		}
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void requestBalance(String address, JCallback callback) {
		// TODO Auto-generated method stub
		String url = JUtils.formatUrl(this.host, this.port, this.secure);
	}

	public void requestHistoricTransactions(String address, int page, int ledger, int seq, JCallback callback) {
		// TODO Auto-generated method stub

	}

	public void requestOrders(String address, int page, JCallback callback) {
		// TODO Auto-generated method stub

	}

	public void createOrder(String signature, JCallback callback) {
		// TODO Auto-generated method stub

	}

	public void cancelOrder(String signature, JCallback callback) {
		// TODO Auto-generated method stub

	}

	public int requestSequence(String address) {
		return -1;
		// TODO Auto-generated method stub
	}

	public void transferToken(String signature, JCallback callback) {
		// TODO Auto-generated method stub

	}

}
