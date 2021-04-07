package com.jccdex.rpc.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

@PrepareForTest({ CommUtils.class })
public class JccdexExchangeTest {
//	public final String host = "192.168.66.253";
//	public JccdexUrl jccUrl = new JccdexUrl(host, false);
	public final String host = "eji39bdbd155a03.swtc.top";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	JccdexExchange exchange = JccdexExchange.getInstance();
	public JCallback mockCallBack;

	@Test
	public void testReuestAll() {
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexExchange mockExchange = Mockito.mock(JccdexExchange.class);
		mockExchange.requestBalance("11", mockCallBack);
		Mockito.verify(mockExchange).requestBalance("11", mockCallBack);
		mockExchange.requestHistoricTransactions("11", 0, 0, mockCallBack);
		Mockito.verify(mockExchange).requestHistoricTransactions("11", 0, 0, mockCallBack);
		mockExchange.requestOrders("11", 0, mockCallBack);
		Mockito.verify(mockExchange).requestOrders("11", 0, mockCallBack);
		mockExchange.createOrder("11", mockCallBack);
		Mockito.verify(mockExchange).createOrder("11", mockCallBack);
		mockExchange.cancelOrder("11", mockCallBack);
		Mockito.verify(mockExchange).cancelOrder("11", mockCallBack);
		mockExchange.requestSequence("11");
		Mockito.verify(mockExchange).requestSequence("11");
		mockExchange.transferToken("11", mockCallBack);
		Mockito.verify(mockExchange).transferToken("11", mockCallBack);
		mockExchange.requestOrderDetail("11", mockCallBack);
		Mockito.verify(mockExchange).requestOrderDetail("11", mockCallBack);
	}

	@Test
	public void testRequestBalance() {
		exchange.setmBaseUrl(jccUrl);

		// correct address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// none address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestBalance("", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.requestBalance("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testRequestHistoricTransactions() {
		exchange.setmBaseUrl(jccUrl);
		// correct address at page 1
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, 0, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// correct address at page 2
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 11461562, 0, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// correct address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, 0, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// none address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestHistoricTransactions("", 0, 0, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// special address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestHistoricTransactions("?", 0, 0, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.requestHistoricTransactions("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, 0, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testRequestOrders() {
		exchange.setmBaseUrl(jccUrl);
		// correct address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN2", 0, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// none address
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrders("", 0, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.requestOrders("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 0, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testCreateOrder() {
		exchange.setmBaseUrl(jccUrl);
		// failed to create order
		mockCallBack = Mockito.mock(JCallback.class);
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.createOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.createOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect url
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexUrl mockUrl = Mockito.mock(JccdexUrl.class);
		Mockito.when(mockUrl.getUrl()).thenReturn("https://ektjsbdyfg.weidex.vip:443/11");
		exchange.setmBaseUrl(mockUrl);
		exchange.createOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testCancelOrder() {
		exchange.setmBaseUrl(jccUrl);
		// failed to cancel order
		mockCallBack = Mockito.mock(JCallback.class);
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.cancelOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.cancelOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect url
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexUrl mockUrl = Mockito.mock(JccdexUrl.class);
		Mockito.when(mockUrl.getUrl()).thenReturn("https://ektjsbdyfg.weidex.vip:443/11");
		exchange.setmBaseUrl(mockUrl);
		exchange.cancelOrder(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testRequestSequence() {
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

		// incorrect host
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.requestSequence("jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6");
		assertTrue(sequence == -1);
	}

	@Test
	public void testTransferToken() {
		exchange.setmBaseUrl(jccUrl);
		// failed to transfer token
		mockCallBack = Mockito.mock(JCallback.class);
		String signature = "1200072200000000240000000C6440000000000F424065D3038D7EA4C68000000000000000000000000000434E590000000000A582E432BFC48EEDEF852C814EC57F3CD2D4159668400000000000000A732102889E27E7A9CCE4AC7639A3B8D0B76802CD6DAA611DA9BB374BEDC425890A10DD7446304402205E5870C84A29E2CDA4A5EBCE0117CE3803F15CDCEE7B8081E3E934AA91CADD680220562254492372E040C208FD4B925A643540DFFB2E1210A6BB46F0DB7BECA75ADC811477DAE3BDFE78BB2C82F15CAC65E2472AC65C0C2A";
		exchange.transferToken(signature, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.transferToken(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect port
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexUrl mockUrl = Mockito.mock(JccdexUrl.class);
		Mockito.when(mockUrl.getUrl()).thenReturn("https://ektjsbdyfg.weidex.vip:443/11");
		exchange.setmBaseUrl(mockUrl);
		exchange.transferToken(signature, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}
	
	@Test
	public void testRequestOrderDetail() {
		exchange.setmBaseUrl(jccUrl);
		// correct hash
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrderDetail("DB1C9DF2224F3053746052E2790FB202B89E124EAD49356F6B8E3BDC4ACE6551", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// incorrect hash
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrderDetail("DB1C9DF2224F3053746052E2790FB202B89E124EAD49356F6B8E3BDC4ACE65511", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// none hash
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.requestOrderDetail("", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));

		// incorrect host
		mockCallBack = Mockito.mock(JCallback.class);
		exchange.setmBaseUrl(new JccdexUrl("11", true));
		exchange.requestOrderDetail("DB1C9DF2224F3053746052E2790FB202B89E124EAD49356F6B8E3BDC4ACE6551", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}
}
