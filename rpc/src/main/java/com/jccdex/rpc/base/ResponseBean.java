package com.jccdex.rpc.base;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable {

	private static final long serialVersionUID = 8512859271135752240L;

	private int code;
	private T data;
	private boolean isActive;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
