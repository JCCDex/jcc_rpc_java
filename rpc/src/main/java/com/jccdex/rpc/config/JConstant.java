package com.jccdex.rpc.config;

public class JConstant {

	// jingchang api success code
	public static final String REQUEST_JC_SUCCESS_CODE = "0";

	// jingchang api url
	public static final String JC_REQUEST_BALANCE_URL = "/exchange/balances/";

	public static final String JC_REQUEST_HISTORY_URL = "/exchange/tx/";

	public static final String JC_REQUEST_ORDER_URL = "/exchange/orders/";

	public static final String JC_CREATE_ORDER_URL = "/exchange/sign_order";

	public static final String JC_CANCEL_ORDER_URL = "/exchange/sign_cancel_order";

	public static final String JC_REQUEST_SEQUENCE = "/exchange/sequence/";

	public static final String JC_TRNSFER_TOKEN_URL = "/exchange/sign_payment";

	/**
	 * return -1 if request sequence in error
	 */
	public static final int ERROR_SEQUENCE = -1;
}