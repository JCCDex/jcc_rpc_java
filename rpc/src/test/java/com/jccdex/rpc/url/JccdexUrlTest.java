package com.jccdex.rpc.url;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class JccdexUrlTest {
	JccdexUrl url;

	@Test
	public void testGetUrl() {
		url = new JccdexUrl();
		assertThat("", CoreMatchers.equalTo(url.getUrl()));
	}

	@Test
	public void testJccdexUrl() {
		url = new JccdexUrl("192.168.63.253", false, 90);
		assertThat("http://192.168.63.253:90", CoreMatchers.equalTo(url.getUrl()));
		url = new JccdexUrl("192.168.63.253", false, 90);
		assertThat("https://192.168.63.253:90", CoreMatchers.equalTo(url.getUrl()));
	}

	@Test
	public void testJccdexUrlStringBooleanInt() {
		url = new JccdexUrl("192.168.63.253", false);
		assertThat("http://192.168.63.253:80", CoreMatchers.equalTo(url.getUrl()));
		url = new JccdexUrl("192.168.63.253", true);
		assertThat("https://192.168.63.253:80", CoreMatchers.equalTo(url.getUrl()));
	}
}
