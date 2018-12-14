package com.jccdex.rpc.base;

public interface JCallback {

	void onResponse(String code, String response);

	void onFail(Exception e);
}
