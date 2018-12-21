package com.jccdex.rpc.api;

import java.text.ParseException;

import org.junit.Test;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

public class JccdexInfoTest {

//	public final String host = "192.168.66.253";
//	public JccdexUrl jccUrl = new JccdexUrl(host, false);
	public final String host = "ia111ecfd37.jccdex.cn";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	JccdexInfo info = JccdexInfo.getInstance();
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
	public void testReuestTicker() {
		System.out.println("------------testReuestTicker--------------");
		info.setmBaseUrl(jccUrl);
		info.requestTicker("swt", "cnt", callBack);
		info.requestTicker("swt", "jcnt", callBack);
		info.requestTicker("swt", "", callBack);
		info.requestTicker("", "cnt", callBack);
		info.requestTicker("", "", callBack);
	}

	@Test
	public void testReuestAllTickers() {
		System.out.println("------------testReuestAllTickers--------------");
		info.setmBaseUrl(jccUrl);
		info.requestAllTickers(callBack);
	}

	@Test
	public void testReuestDepth() {
		System.out.println("------------requestTicker--------------");
		info.setmBaseUrl(jccUrl);
		info.requestDepth("swt", "cnt", "normal", callBack);
		info.requestDepth("swt", "cnt", "more", callBack);
		info.requestDepth("swt", "cnt", "test", callBack);
		info.requestDepth("swt", "jcnt", "normal", callBack);
		info.requestDepth("", "cnt", "normal", callBack);
		info.requestDepth("swt", "", "normal", callBack);
		info.requestDepth("swt", "cnt", "", callBack);
		info.requestDepth("swt", "", "", callBack);
		info.requestDepth("", "", "normal", callBack);
		info.requestDepth("swt", "", "normal", callBack);
	}

	@Test
	public void testReuestKline() {
		System.out.println("------------testReuestKline--------------");
		info.setmBaseUrl(jccUrl);
		info.requestKline("swt", "cnt", "hour", callBack);
		info.requestKline("swt", "cnt", "day", callBack);
		info.requestKline("swt", "cnt", "week", callBack);
		info.requestKline("swt", "cnt", "month", callBack);
		info.requestKline("swt", "cnt", "test", callBack);
		info.requestKline("", "cnt", "month", callBack);
		info.requestKline("swt", "", "month", callBack);
		info.requestKline("swt", "cnt", "", callBack);
		info.requestKline("", "", "month", callBack);
	}

	@Test
	public void testReuestHistory() throws ParseException {
		System.out.println("------------testReuestHistory--------------");
		info.setmBaseUrl(jccUrl);
		String unixtime = String.valueOf(System.currentTimeMillis() / 1000);
		info.requestHistory("swt", "cnt", "newest", unixtime, callBack);
		info.requestHistory("swt", "cnt", "newest", "", callBack);
		info.requestHistory("swt", "cnt", "newest", "11111", callBack);
		info.requestHistory("swt", "cnt", "all", unixtime, callBack);
		info.requestHistory("swt", "cnt", "more", unixtime, callBack);
		info.requestHistory("swt", "cnt", "test", unixtime, callBack);
		info.requestHistory("", "cnt", "more", unixtime, callBack);
		info.requestHistory("swt", "", "more", unixtime, callBack);
		info.requestHistory("swt", "cnt", "", unixtime, callBack);
		info.requestHistory("", "", "more", unixtime, callBack);
		info.requestHistory("swt", "", "", unixtime, callBack);
		info.requestHistory("", "cnt", "", unixtime, callBack);
	}

	@Test
	public void testReuestTickerFromCMC() {
		System.out.println("------------testReuestTickerFromCMC--------------");
		String host = "weidex.vip";
		JccdexUrl jccUrl = new JccdexUrl(host, true);
		info.setmBaseUrl(jccUrl);
		info.requestTickerFromCMC("eth", "cny", callBack);
		info.requestTickerFromCMC("eth", "cnt", callBack);
		info.requestTickerFromCMC("", "cny", callBack);
		info.requestTickerFromCMC("eth", "", callBack);
		info.requestTickerFromCMC("", "", callBack);
	}

}
