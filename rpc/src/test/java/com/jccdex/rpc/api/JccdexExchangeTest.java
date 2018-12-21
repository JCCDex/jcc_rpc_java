package com.jccdex.rpc.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

public class JccdexExchangeTest {
//	public final String host = "192.168.66.253";
//	public JccdexUrl jccUrl = new JccdexUrl(host, false);
	public final String host = "ektjsbdyfg.weidex.vip";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	public JCallback callBack = new JCallback() {

		@Override
		public void onResponse(String code, String response) {
			System.out.println(code);
			System.out.println(response);
		}

		@Override
		public void onFail(Exception e) {
			System.out.println(e.getMessage());
		}

	};

	@Test
	public void testRequestBalance() {
		System.out.println("------------testRequestBalance--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);

		// correct address
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", callBack);

		// incorrect address
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", callBack);

		// none address
		exchange.requestBalance("", callBack);
	}

	@Test
	public void testRequestHistoricTransactions() {
		System.out.println("------------testRequestHistoricTransactions--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// correct address at page 1
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, 0, 0, callBack);

		// correct address at page 2
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 2, 11461562, 0, callBack);

		// correct address
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, 0, 0, callBack);

		// none address
		exchange.requestHistoricTransactions("", 0, 0, 0, callBack);
	}

	@Test
	public void testRequestOrders() {
		System.out.println("------------testRequestOrders--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// correct address
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, callBack);

		// incorrect address
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, callBack);

		// none address
		exchange.requestOrders("", 0, callBack);
	}

	@Test
	public void testCreateOrder() {
		System.out.println("------------testCreateOrder--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to create order
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.createOrder(signature, callBack);
	}

	@Test
	public void testCancelOrder() {
		System.out.println("------------testCancelOrder--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to cancel order
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.cancelOrder(signature, callBack);
	}

	@Test
	public void testRequestSequence() {
		System.out.println("------------testRequestSequence--------------");
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
		System.out.println("------------testTransferToken--------------");
		JccdexExchange exchange = JccdexExchange.getInstance();
		exchange.setmBaseUrl(jccUrl);
		// failed to transfer token
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.transferToken(signature, callBack);
	}

}
