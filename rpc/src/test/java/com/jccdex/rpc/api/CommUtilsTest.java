package com.jccdex.rpc.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.Response;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Response.class })
public class CommUtilsTest {

	@Test
	public void testIsSuccessful() {
		boolean res = CommUtils.isSuccessful(200);
		assertTrue(res);
		res = CommUtils.isSuccessful(304);
		assertFalse(res);
		res = CommUtils.isSuccessful(404);
		assertFalse(res);
	}

	@Test
	public void testFormatExceptionMessage() {
		Response mockResponse = PowerMockito.mock(Response.class);
		PowerMockito.when(mockResponse.code()).thenReturn(404);
		PowerMockito.when(mockResponse.message()).thenReturn("not found");
		String res = CommUtils.formatExceptionMessage(mockResponse);
		assertThat("404: not found", CoreMatchers.equalTo(res));
	}
}
