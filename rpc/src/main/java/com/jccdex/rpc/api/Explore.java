package com.jccdex.rpc.api;

import com.jccdex.rpc.base.JCallback;

public interface Explore {
	void requestTransDetails(String uuid, String hash, JCallback callback);

	void requestBalance(String uuid, String address, JCallback callback);

	void requestHistoricTransWithAddr(String uuid, int page, int size, String begin, String end, String type,
			String currency, String address, JCallback callback);

	void requestPaymentSummary(String uuid, String address, int dateTpye, String date, String type, String currency,
			JCallback callback);
}
