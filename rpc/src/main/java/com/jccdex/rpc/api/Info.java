
package com.jccdex.rpc.api;

import com.jccdex.rpc.base.JCallback;

public interface Info {

	void requestTicker(String base, String counter, JCallback callBack);

	void requestAllTickers(JCallback callBack);

	void requestDepth(String base, String counter, String type, JCallback callBack);

	void requestKline(String base, String counter, String type, JCallback callBack);

	void requestHistory(String base, String counter, String type, String time, JCallback callBack);

}
