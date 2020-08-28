package com.jccdex.rpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

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

public class JccdexInfo implements Info {

	private BaseUrl mBaseUrl;

	private JccdexInfo() {
	}

	public static JccdexInfo getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccdexInfo singleton;

		private Singleton() {
			singleton = new JccdexInfo();
		}

		public JccdexInfo getInstance() {
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
	 * get ticker info
	 * 
	 * @param base
	 * @param counter
	 * @param callBack
	 */
	@Override
	public void requestTicker(@NotNull String base, @NotNull String counter, @NotNull JCallback callBack) {
		String pair = base.toUpperCase() + "-" + counter.toUpperCase().replaceAll("^CNT$", "CNY");
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_TICKER_ROUTE + pair;
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
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

	/**
	 * get all tickers info
	 * 
	 * @param callBack
	 */
	@Override
	public void requestAllTickers(JCallback callBack) {
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_ALLTICKERS_ROUTE;
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
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

	/**
	 * 
	 * @param base
	 * @param counter
	 * @param type     {normal | more}
	 * @param callBack
	 */
	@Override
	public void requestDepth(@NotNull String base, @NotNull String counter, @NotNull String type,
			@NotNull JCallback callBack) {
		String pair = base.toUpperCase() + "-" + counter.toUpperCase().replaceAll("^CNT$", "CNY");
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_DEPTH_ROUTE + pair + "/" + type;
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
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

	/**
	 * @param base
	 * @param counter
	 * @param type     {hour | day | week | month}
	 * @param callBack
	 */
	@Override
	public void requestKline(@NotNull String base, @NotNull String counter, @NotNull String type,
			@NotNull JCallback callBack) {
		String pair = base.toUpperCase() + "-" + counter.toUpperCase().replaceAll("^CNT$", "CNY");
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_KLINE_ROUTE + pair + "/" + type;
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
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

	/**
	 * @param base
	 * @param counter
	 * @param type     {all | more | newest}
	 * @param time     {Unix time}
	 * @param callBack
	 */
	@Override
	public void requestHistory(@NotNull String base, @NotNull String counter, @NotNull String type,
			@NotNull String time, @NotNull JCallback callBack) {
		String pair = base.toUpperCase() + "-" + counter.toUpperCase().replaceAll("^CNT$", "CNY");
		String url = mBaseUrl.getUrl() + JConstant.JC_REQUEST_INFO_HISTORY_ROUTE + pair + "/" + type;
		final Request.Builder reqBuild = new Request.Builder();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		if ("newest".equals(type)) {
			urlBuilder.addQueryParameter("time", time);
		}
		reqBuild.url(urlBuilder.build());
		Request request = reqBuild.build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				body.close();
				callBack.onResponse(code, res);
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

	/**
	 * request token info from coinmarketdata
	 * 
	 * @param token
	 * @param currency
	 * @param callBack
	 */
	@Override
	public void requestTickerFromCMC(@NotNull String token, @NotNull String currency, @NotNull JCallback callBack) {
		String url = mBaseUrl.getUrl() + "/" + token.toLowerCase() + "_" + currency.toLowerCase() + ".json";
		String t = String.valueOf(new Date().getTime());
		final Request.Builder reBuild = new Request.Builder();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("t", t);
		reBuild.url(urlBuilder.build());
		Request request = reBuild.build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("code").asText();
				callBack.onResponse(code, res);
				body.close();
			} else {
				callBack.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callBack.onFail(e);
		}
	}

}
