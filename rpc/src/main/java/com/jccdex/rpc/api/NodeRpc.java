package com.jccdex.rpc.api;

import com.jccdex.rpc.base.JCallback;

public interface NodeRpc {
	void requestSequence(String address, JCallback callback);

	void transfer(String blob, JCallback callback);

	void requestTx(String hash, JCallback callback);
}
