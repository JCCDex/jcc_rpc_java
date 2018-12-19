package com.jccdex.rpc.api;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jccdex.rpc.base.JccCallBack;
import com.jccdex.rpc.url.JccdexUrl;

public class JccdexExchangeTest {
	public final String host = "192.168.66.253";
	public JccdexUrl jccUrl = new JccdexUrl(host, false);
//	public final String host = "ektjsbdyfg.weidex.vip";
//	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	@Test
	public void testRequestBalance() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);

		// correct address
		JccCallBack callBack = new JccCallBack();
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", callBack);
		assertTrue(callBack.getOutPut() != null);
		assertTrue(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONArray responseData = (JSONArray) callBack.getOutPut().getData();
		assertTrue(responseData.size() > 0);

		// incorrect address
		callBack = new JccCallBack();
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData1 = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData1.isEmpty());

		// none address
		callBack = new JccCallBack();
		exchange.requestBalance("", callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "404" }, new String[] { callBack.getOutPut().getSatusCode() });
		responseData1 = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData1 == null);
	}

	@Test
	public void testRequestHistoricTransactions() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// correct address at page 1
		JccCallBack callBack = new JccCallBack();
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, 0, 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertTrue(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData.size() == 2);
		JSONObject marker = responseData.getJSONObject("marker");
		JSONArray transactions = responseData.getJSONArray("transactions");
		assertTrue(marker.size() > 0);
		assertTrue(transactions.size() > 0);

		// correct address at page 2
		callBack = new JccCallBack();
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 2, 11461562, 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertTrue(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		responseData = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData.size() == 2);
		marker = responseData.getJSONObject("marker");
		transactions = responseData.getJSONArray("transactions");
		assertTrue(marker.size() > 0);
		assertTrue(transactions.size() > 0);

		// correct address
		callBack = new JccCallBack();
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, 0, 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		responseData = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData.size() == 0);
		marker = responseData.getJSONObject("marker");
		transactions = responseData.getJSONArray("transactions");
		assertTrue(marker == null);
		assertTrue(transactions == null);

		// none address
		callBack = new JccCallBack();
		exchange.requestHistoricTransactions("", 0, 0, 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		responseData = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData.size() == 0);
		marker = responseData.getJSONObject("marker");
		transactions = responseData.getJSONArray("transactions");
		assertTrue(marker == null);
		assertTrue(transactions == null);
	}

	@Test
	public void testRequestOrders() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// correct address
		JccCallBack callBack = new JccCallBack();
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertTrue(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONArray responseData = (JSONArray) callBack.getOutPut().getData();
		assertTrue(responseData.size() > 0);

		// incorrect address
		callBack = new JccCallBack();
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData1 = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData1.isEmpty());

		// none address
		callBack = new JccCallBack();
		exchange.requestOrders("", 0, callBack);
		assertTrue(callBack.getOutPut() != null);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "404" }, new String[] { callBack.getOutPut().getSatusCode() });
		responseData1 = (JSONObject) callBack.getOutPut().getData();
		assertTrue(responseData1 == null);
	}

	@Test
	public void testCreateOrder() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to create order
		JccCallBack callBack = new JccCallBack();
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.createOrder(signature, callBack);
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData = (JSONObject) callBack.getOutPut().getData();
		assertArrayEquals(new String[] { "tefPAST_SEQ" }, new String[] { responseData.getString("result") });
		assertArrayEquals(new String[] { "This sequence number has already past." },
				new String[] { responseData.getString("result_message") });
	}

	@Test
	public void testCancelOrder() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to cancel order
		JccCallBack callBack = new JccCallBack();
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.cancelOrder(signature, callBack);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData = (JSONObject) callBack.getOutPut().getData();
		assertArrayEquals(new String[] { "tefPAST_SEQ" }, new String[] { responseData.getString("result") });
		assertArrayEquals(new String[] { "This sequence number has already past." },
				new String[] { responseData.getString("result_message") });
	}

	@Test
	public void testRequestSequence() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		int sequence;
		// correct address
		sequence = exchange.requestSequence("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6");
		assertTrue(sequence != -1);

		// incorrect address
		sequence = exchange.requestSequence("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2");
		assertTrue(sequence == -1);

		// none address
		sequence = exchange.requestSequence("");
		assertTrue(sequence == -1);
	}

	@Test
	public void testTransferToken() {
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to transfer token
		JccCallBack callBack = new JccCallBack();
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.transferToken(signature, callBack);
		assertFalse(callBack.getOutPut().isResult());
		assertArrayEquals(new String[] { "200" }, new String[] { callBack.getOutPut().getSatusCode() });
		JSONObject responseData = (JSONObject) callBack.getOutPut().getData();
		assertArrayEquals(new String[] { "tefPAST_SEQ" }, new String[] { responseData.getString("result") });
		assertArrayEquals(new String[] { "This sequence number has already past." },
				new String[] { responseData.getString("result_message") });
	}

}
