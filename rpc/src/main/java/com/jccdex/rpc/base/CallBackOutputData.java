package com.jccdex.rpc.base;

import java.io.Serializable;

public class CallBackOutputData<T> implements Serializable {

	private static final long serialVersionUID = -1639795610782067227L;

	private boolean result;
	private String statusCode;
	private T data;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getSatusCode() {
		return statusCode;
	}

	public void setStatusCode(String msg) {
		this.statusCode = msg;
	}

	/**
	 * {com.alibaba.fastjson.JSONObject | JSONArray}
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
