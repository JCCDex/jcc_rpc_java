package com.jccdex.rpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.config.JConstant;
import com.jccdex.rpc.url.BaseUrl;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JccConfig implements Config {

	private BaseUrl mBaseUrl;

	private JccConfig() {
	}

	public static JccConfig getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccConfig singleton;

		private Singleton() {
			singleton = new JccConfig();
		}

		public JccConfig getInstance() {
			return singleton;
		}
	}

	public void setmBaseUrl(BaseUrl mBaseUrl) {
		this.mBaseUrl = mBaseUrl;
	}

	public final OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.MILLISECONDS)
			.cookieJar(new CookieJar() {
				private final Map<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

				public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
					cookieStore.put(url.host(), cookies);
				}

				public List<Cookie> loadForRequest(HttpUrl url) {
					List<Cookie> cookies = cookieStore.get(url.host());
					return cookies != null ? cookies : new ArrayList<Cookie>();
				}
			}).build();

	/**
	 * get config
	 * 
	 * @param callback
	 */
	@Override
	public void requestConfig(JCallback callBack) {
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_CONFIG_ROUTE + "?t=" + System.currentTimeMillis();
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
				body.close();
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}

	}

}
