package com.jccdex.rpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.BaseUrl;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JccdexNodeRpc implements NodeRpc {

	private BaseUrl mBaseUrl;

	private JccdexNodeRpc() {
	}

	public static JccdexNodeRpc getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private JccdexNodeRpc singleton;

		private Singleton() {
			singleton = new JccdexNodeRpc();
		}

		public JccdexNodeRpc getInstance() {
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

	@Override
	public void requestSequence(String address, JCallback callback) {
		String url = mBaseUrl.getUrl();

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode data = mapper.createObjectNode();
		ObjectNode object = mapper.createObjectNode();

		object.put("account", address);
		ArrayList<ObjectNode> params = new ArrayList<>();
		params.add(object);

		ArrayNode array = mapper.valueToTree(params);

		data.put("method", "account_info");
		data.set("params", array);
		RequestBody formBody = RequestBody.create(MediaType.parse("application/json"), data.toString());

		Request request = new Request.Builder().url(url).post(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("result").get("status").toString();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	@Override
	public void transfer(String blob, JCallback callback) {
		String url = mBaseUrl.getUrl();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode data = mapper.createObjectNode();
		ObjectNode object = mapper.createObjectNode();
		object.put("tx_blob", blob);

		ArrayList<ObjectNode> params = new ArrayList<>();
		params.add(object);

		ArrayNode array = mapper.valueToTree(params);

		data.put("method", "submit");
		data.set("params", array);

		RequestBody formBody = RequestBody.create(MediaType.parse("application/json"), data.toString());

		Request request = new Request.Builder().url(url).post(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("result").get("engine_result").toString();
				body.close();
				callback.onResponse(code, res);
			} else {
				callback.onFail(new Exception(CommUtils.formatExceptionMessage(response)));
			}
		} catch (IOException e) {
			callback.onFail(e);
		}
	}

	@Override
	public void requestTx(String hash, JCallback callback) {
		String url = mBaseUrl.getUrl();

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode data = mapper.createObjectNode();
		ObjectNode object = mapper.createObjectNode();
		object.put("transaction", hash);
		object.put("binary", false);
		ArrayList<ObjectNode> params = new ArrayList<>();
		params.add(object);

		ArrayNode array = mapper.valueToTree(params);

		data.put("method", "tx");
		data.set("params", array);

		RequestBody formBody = RequestBody.create(MediaType.parse("application/json"), data.toString());

		Request request = new Request.Builder().url(url).post(formBody).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (CommUtils.isSuccessful(response.code())) {
				ResponseBody body = response.body();
				String res = body.string();
				JsonNode actualObj = mapper.readTree(res);
				String code = actualObj.get("result").get("status").toString();
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
