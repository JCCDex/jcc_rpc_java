package com.jccdex.rpc.base;

public interface JCallback {

	/**
	 * 
	 * @param code     code from jingchang api response, if the code is equal to 0
	 *                 that means response result is correct, otherwise means wrong.
	 * @param response response result from jingchang api
	 */
	void onResponse(String code, String response);

	void onFail(Exception e);
}
