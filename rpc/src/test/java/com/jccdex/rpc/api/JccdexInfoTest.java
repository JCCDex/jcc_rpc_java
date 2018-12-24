package com.jccdex.rpc.api;

import java.text.ParseException;

import org.junit.Test;
import org.mockito.Mockito;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

public class JccdexInfoTest {

//	public final String host = "192.168.66.253";
//	public JccdexUrl jccUrl = new JccdexUrl(host, false);
	public final String host = "ia111ecfd37.jccdex.cn";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	public JccdexInfo info = JccdexInfo.getInstance();
	public JCallback mockCallBack;

	@Test
	public void testReuestAll() {
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexInfo mockInfo = Mockito.mock(JccdexInfo.class);
		mockInfo.requestTicker("swt", "cnt", mockCallBack);
		Mockito.verify(mockInfo).requestTicker("swt", "cnt", mockCallBack);
		mockInfo.requestAllTickers(mockCallBack);
		Mockito.verify(mockInfo).requestAllTickers(mockCallBack);
		mockInfo.requestDepth("swt", "cnt", "normal", mockCallBack);
		Mockito.verify(mockInfo).requestDepth("swt", "cnt", "normal", mockCallBack);
		mockInfo.requestKline("swt", "cnt", "hour", mockCallBack);
		Mockito.verify(mockInfo).requestKline("swt", "cnt", "hour", mockCallBack);
		mockInfo.requestHistory("swt", "cnt", "newest", "1111", mockCallBack);
		Mockito.verify(mockInfo).requestHistory("swt", "cnt", "newest", "1111", mockCallBack);
		mockInfo.requestTickerFromCMC("eth", "cny", mockCallBack);
		Mockito.verify(mockInfo).requestTickerFromCMC("eth", "cny", mockCallBack);
	}

	@Test
	public void testReuestTicker() {
		info.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("swt", "cnt", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("swt", "jcnt", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("swt", "", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("", "cnt", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("", "", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTicker("?/", "cnt", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestTicker("swt", "cnt", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testReuestAllTickers() {
		info.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestAllTickers(mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestAllTickers(mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		JccdexUrl mockUrl = Mockito.mock(JccdexUrl.class);
		Mockito.when(mockUrl.getUrl()).thenReturn("https://ia111ecfd37.jccdex.cn:443/11");
		info.setmBaseUrl(mockUrl);
		info.requestAllTickers(mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testReuestDepth() {
		info.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "cnt", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "cnt", "more", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "cnt", "test", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "jcnt", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("", "cnt", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "cnt", "", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "", "", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("", "", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestDepth("swt", "", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestDepth("swt", "cnt", "normal", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testReuestKline() {
		info.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "hour", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "day", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "week", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "month", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "test", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("", "cnt", "month", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "", "month", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("swt", "cnt", "", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestKline("", "", "month", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestKline("swt", "cnt", "hour", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testReuestHistory() throws ParseException {
		info.setmBaseUrl(jccUrl);
		String unixtime = String.valueOf(System.currentTimeMillis() / 1000);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "newest", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "newest", "", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "newest", "11111", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "all", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "more", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "test", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("", "cnt", "more", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "", "more", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "cnt", "", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("", "", "more", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("swt", "", "", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestHistory("", "cnt", "", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestHistory("", "cnt", "", unixtime, mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}

	@Test
	public void testReuestTickerFromCMC() {
		String host = "weidex.vip";
		JccdexUrl jccUrl = new JccdexUrl(host, true);
		info.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTickerFromCMC("eth", "cny", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTickerFromCMC("eth", "cnt", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTickerFromCMC("", "cny", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTickerFromCMC("eth", "", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.requestTickerFromCMC("", "", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
		mockCallBack = Mockito.mock(JCallback.class);
		info.setmBaseUrl(new JccdexUrl("11", true));
		info.requestTickerFromCMC("eth", "cny", mockCallBack);
		Mockito.verify(mockCallBack).onFail(Mockito.any(Exception.class));
	}
}
