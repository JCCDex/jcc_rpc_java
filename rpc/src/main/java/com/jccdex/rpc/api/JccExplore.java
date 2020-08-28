package com.jccdex.rpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class JccExplore implements Explore {
	private BaseUrl mBaseUrl;

	private JccExplore() {
	}

	public static JccExplore getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccExplore singleton;

		private Singleton() {
			singleton = new JccExplore();
		}

		public JccExplore getInstance() {
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
	 * 
	 * @param uuid
	 * @param hash     {hex string}
	 * @param callback
	 */
	@Override
	public void requestTransDetails(String uuid, String hash, JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_EXPLORE_REQUEST_DETAIL_ROUTE + uuid + "?h=" + hash;
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	/**
	 * 
	 * @param uuid
	 * @param address  {hex string}
	 * @param callback
	 */
	@Override
	public void requestBalance(String uuid, String address, JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_EXPLORE_REQUEST_BALANCE_ROUTE + uuid + "?w=" + address;
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	/**
	 * 
	 * @param uuid
	 * @param page
	 * @param size     {10/20/50/100}
	 * @param begin    {xxxx-xx-xx}
	 * @param end      {xxxx-xx-xx}
	 * @param type
	 * @param currency
	 * @param address  {hex string}
	 * @param callback
	 */
	@Override
	public void requestHistoricTransWithAddr(String uuid, int page, int size, String begin, String end, String type,
			String currency, String address, JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_EXPLORE_REQUEST_TRANS_ROUTE + uuid + "?p=" + String.valueOf(page)
				+ "&s=" + String.valueOf(size);
		if (!CommUtils.isEmpty(begin)) {
			url = url + "&b=" + begin;
		}
		if (!CommUtils.isEmpty(end)) {
			url = url + "&e=" + end;
		}
		if (!CommUtils.isEmpty(type)) {
			url = url + "&t=" + type;
		}
		if (!CommUtils.isEmpty(currency)) {
			url = url + "&c=" + currency;
		}
		url = url + "&w=" + address;

		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	/**
	 * @param uuid
	 * @param address  {hex string}
	 * @param dateTpye
	 * @param begin    {xxxx-xx-xx}
	 * @param end      {xxxx-xx-xx}
	 * @param type     {Send|Receive}
	 * @param currency
	 * @param callback
	 */
	@Override
	public void requestPaymentSummary(String uuid, String address, int dateTpye, String begin, String end, String type,
			String currency, JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_EXPLORE_REQUEST_PAYMENT_SUMMARY + uuid + "?w=" + address + "&b="
				+ begin + "&dt=" + dateTpye;
		if (!CommUtils.isEmpty(end)) {
			url = url + "&e=" + end;
		}
		if (!CommUtils.isEmpty(type)) {
			url = url + "&t=" + type;
		}
		if (!CommUtils.isEmpty(currency)) {
			url = url + "&c=" + currency;
		}

		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	/**
	 * 
	 * @param uuid
	 * @param page
	 * @param size     {10/20/50/100}
	 * @param begin    {xxxx-xx-xx}
	 * @param end      {xxxx-xx-xx}
	 * @param type
	 * @param currency
	 * @param callback
	 */
	@Override
	public void requestHistoricTransWithCur(String uuid, int page, int size, String begin, String end, String type,
			String currency, JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_EXPLORE_REQUEST_PAYMENT_TRANS + uuid + "?p="
				+ String.valueOf(page) + "&s=" + String.valueOf(size);
		if (!CommUtils.isEmpty(begin)) {
			url = url + "&b=" + begin;
		}
		if (!CommUtils.isEmpty(end)) {
			url = url + "&e=" + end;
		}
		url = url + "&t=" + type;
		if (!CommUtils.isEmpty(currency)) {
			url = url + "&c=" + currency;
		}

		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

}
