package com.jccdex.rpc.base;

import com.alibaba.fastjson.JSONObject;

public class JccCallBack implements JCallback {

	private static final String STATUS_CODE_OK = "200";

	private CallBackOutputData<Object> outPut = new CallBackOutputData<Object>();

	@SuppressWarnings("unchecked")
	@Override
	public void onResponse(String code, String response) {
		ResponseBean<Object> responseBean = JSONObject.parseObject(response, ResponseBean.class);
		if (responseBean == null) {
			responseBean = new ResponseBean<Object>();
		}
		if (STATUS_CODE_OK.equals(code)) {
			int dataCode = responseBean.getCode();
			if (dataCode == 0) {
				outPut.setResult(true);
			} else {
				outPut.setResult(false);
			}
		} else {
			outPut.setResult(false);
		}
		outPut.setStatusCode(String.valueOf(code));
		outPut.setData(responseBean.getData());
	}

	@Override
	public void onFail(Exception e) {
		outPut.setResult(false);
		outPut.setStatusCode(e.getMessage());
	}

	public CallBackOutputData<Object> getOutPut() {
		return outPut;
	}

	public void setOutPut(CallBackOutputData<Object> outPut) {
		this.outPut = outPut;
	}

}
