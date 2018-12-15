package com.jccdex.rpc.api;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.BaseUrl;

public class JccdexExchange implements Exchange {

	private BaseUrl mBaseUrl;

	private JccdexExchange() {
	}

	public static JccdexExchange getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccdexExchange singleton;

		private Singleton() {
			singleton = new JccdexExchange();
		}

		public JccdexExchange getInstance() {
			return singleton;
		}
	}

	public void setmBaseUrl(BaseUrl mBaseUrl) {
		this.mBaseUrl = mBaseUrl;
	}

	public void requestBalance(String address, JCallback callback) {
		// TODO Auto-generated method stub
		String url = mBaseUrl.getUrl();
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
