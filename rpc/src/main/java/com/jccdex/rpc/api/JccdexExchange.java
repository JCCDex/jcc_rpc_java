package com.jccdex.rpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.config.JConstant;
import com.jccdex.rpc.url.BaseUrl;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JccdexExchange implements Exchange {

	private BaseUrl mBaseUrl;

	private JccdexExchange() {
	}

	public static JccdexExchange getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccdexExchange singleton;

		private Singleton() {
			singleton = new JccdexExchange();
		}

		public JccdexExchange getInstance() {
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
	 * get balance with jingtum address
	 * 
	 * @param address  {hex string}
	 * @param callback
	 */
	public void requestBalance(String address, @NotNull JCallback callback) {
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_BALANCE_ROUTE + address;
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * get historic transactions with jingtum address
	 * 
	 * @param address  {hex string}
	 * @param ledger
	 * @param seq
	 * @param callback
	 */
	public void requestHistoricTransactions(String address, int ledger, int seq,
			@NotNull JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_REQUEST_HISTORY_ROUTE + address;
		final Request.Builder reqBuild = new Request.Builder();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("ledger", String.valueOf(ledger));
		urlBuilder.addQueryParameter("seq", String.valueOf(seq));
		reqBuild.url(urlBuilder.build());
		Request request = reqBuild.build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * get current orders with jingtum address
	 * 
	 * @param address {hex string}
	 * @param page
	 */
	public void requestOrders(String address, int page, @NotNull JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_REQUEST_ORDER_ROUTE + address + "/" + String.valueOf(page);
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * create order
	 * 
	 * @param signature {hex string}
	 * @param callback
	 */
	public void createOrder(String signature, @NotNull JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_CREATE_ORDER_ROUTE;
		FormBody.Builder builder = new FormBody.Builder();
		builder.add("sign", signature);
		final RequestBody formBody = builder.build();
		Request request = new Request.Builder().url(url).post(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * cancel an order
	 * 
	 * @param signature {hex string}
	 * @param callback
	 */
	public void cancelOrder(String signature, @NotNull JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_CANCEL_ORDER_ROUTE;
		FormBody.Builder builder = new FormBody.Builder();
		builder.add("sign", signature);
		final RequestBody formBody = builder.build();
		Request request = new Request.Builder().url(url).delete(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * get sequence with jingtum address
	 * 
	 * @param address {hex string}
	 */
	public int requestSequence(String address) {
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_SEQUENCE_ROUTE + address;
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				body.close();
				JSONObject jsonResponse = JSONObject.parseObject(res);
				String code = jsonResponse.getString("code");
				if ((JConstant.REQUEST_JC_SUCCESS_CODE).equals(code)) {
					return JSONObject.parseObject(jsonResponse.getString("data")).getIntValue("sequence");
				}
				return JConstant.ERROR_SEQUENCE;
			}
			return JConstant.ERROR_SEQUENCE;
		} catch (IOException e) {
			return JConstant.ERROR_SEQUENCE;
		}
	}

	/**
	 * transfer token
	 * 
	 * @param signature {hex string}
	 * @param callback
	 */
	public void transferToken(String signature, @NotNull JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_TRNSFER_TOKEN_ROUTE;
		FormBody.Builder builder = new FormBody.Builder();
		builder.add("sign", signature);
		final RequestBody formBody = builder.build();
		Request request = new Request.Builder().url(url).post(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
	 * order detail
	 * 
	 * @param hash     {hex string}
	 * @param callback
	 */
	@Override
	public void requestOrderDetail(String hash, JCallback callback) {
		String url = mBaseUrl.getUrl();
		url = url + JConstant.JC_ORDER_DETAIL_ROUTE + hash;
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				String code = JSONObject.parseObject(res).getString("code");
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
