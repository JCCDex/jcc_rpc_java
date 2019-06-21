package com.jccdex.rpc.config;

public class JConstant {

	/**
	 * jingchang api success code.
	 * <P>
	 * {@value}
	 */
	public static final String REQUEST_JC_SUCCESS_CODE = "0";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_BALANCE_ROUTE = "/exchange/balances/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_HISTORY_ROUTE = "/exchange/tx/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_ORDER_ROUTE = "/exchange/orders/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_CREATE_ORDER_ROUTE = "/exchange/sign_order/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_CANCEL_ORDER_ROUTE = "/exchange/sign_cancel_order/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_SEQUENCE_ROUTE = "/exchange/sequence/";

	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_TRNSFER_TOKEN_ROUTE = "/exchange/sign_payment/";
	
	/**
	 * Exchange API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_ORDER_DETAIL_ROUTE = "/exchange/detail/";

	/**
	 * Info API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_TICKER_ROUTE = "/info/ticker/";

	/**
	 * Info API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_ALLTICKERS_ROUTE = "/info/alltickers";

	/**
	 * Info API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_DEPTH_ROUTE = "/info/depth/";

	/**
	 * Info API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_KLINE_ROUTE = "/info/kline/";

	/**
	 * Info API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_INFO_HISTORY_ROUTE = "/info/history/";

	/**
	 * return -1 if request sequence in error
	 */
	public static final int ERROR_SEQUENCE = -1;
	
	/**
	 * Config API route.
	 * <P>
	 * {@value}
	 */
	public static final String JC_REQUEST_CONFIG_ROUTE = "/static/config/jc_config.json";
}