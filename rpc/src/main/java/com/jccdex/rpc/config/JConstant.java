package com.jccdex.rpc.config;

public class JConstant {

	// jingchang api success code
	public static final String REQUEST_JC_SUCCESS_CODE = "0";

	// jingchang api route
	public static final String JC_REQUEST_BALANCE_ROUTE = "/exchange/balances/";

	public static final String JC_REQUEST_HISTORY_ROUTE = "/exchange/tx/";

	public static final String JC_REQUEST_ORDER_ROUTE = "/exchange/orders/";

	public static final String JC_CREATE_ORDER_ROUTE = "/exchange/sign_order/";

	public static final String JC_CANCEL_ORDER_ROUTE = "/exchange/sign_cancel_order/";

	public static final String JC_REQUEST_SEQUENCE_ROUTE = "/exchange/sequence/";

	public static final String JC_TRNSFER_TOKEN_ROUTE = "/exchange/sign_payment/";

	/**
	 * return -1 if request sequence in error
	 */
	public static final int ERROR_SEQUENCE = -1;
}