package com.jccdex.rpc.api;

import org.junit.Test;
import org.mockito.Mockito;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

public class JccConfigTest {

	public final String host = "uploadletsdex.swtc.top";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);
//	public final String host = "ektjsbdyfg.weidex.vip";
//	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	JccConfig config = JccConfig.getInstance();
	public JCallback mockCallBack;

	@Test
	public void test() {
		config.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		config.requestConfig(mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

	}

}
