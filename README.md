# jcc_rpc_java
jcc rpc java version

[![Build Status](https://travis-ci.com/JCCDex/jcc_rpc_java.svg?branch=master)](https://travis-ci.com/JCCDex/jcc_rpc_java)
[![Coverage Status](https://coveralls.io/repos/github/JCCDex/jcc_rpc_java/badge.svg?branch=master)](https://coveralls.io/github/JCCDex/jcc_rpc_java?branch=master)

## Usage of jcc_rpc_java API

## JCallback interface
```javascript
/**
* 
* @param code     code from jingchang api response, if the code is equal to 0
*                 that means response result is correct, otherwise means wrong.
* @param response response result from jingchang api
*/
void onResponse(String code, String response);

void onFail(Exception e);
```

### Create JccdexUrl.
```javascript
JccdexUrl jccUrl = new JccdexUrl("xxx", true);// https://xxx:443
JccdexUrl jccUrl = new JccdexUrl("xxx", false);// http://xxx:80
or
JccdexUrl jccUrl = new JccdexUrl("xxx", true, 8081);// https://xxx:8081
```

## JccdexExchange API

### Create JccdexExchange.
```javascript
JccdexExchange exchange = JccdexExchange.getInstance();
exchange.setmBaseUrl(jccUrl);
```

### requestBalance
```javascript
/** get balance with jingtum address. */
exchange.requestBalance(address, callback);
```
Parameters

`address`- `string`
`callback`- `implements JCallback`

### requestHistoricTransactions
```javascript
/** get historic transactions with jingtum address. */
exchange.requestHistoricTransactions(address, page, ledger, seq, callback);
```

Parameters

`address`- `string`
`page`- `int`
`ledger`- `int`
`seq`- `int`
`callback`- `implements JCallback`

### requestOrders
```javascript
/** get current orders with jingtum address. */
exchange.requestOrders(address, page, callback);
```

Parameters

`address`- `string`
`page`- `int`
`callback`- `implements JCallback`

### createOrder
```javascript
exchange.createOrder(signature, callback);
```

Parameters

`signature`- `string`
`callback`- `implements JCallback`

### cancelOrder
```javascript
exchange.cancelOrder(signature, callback);
```

Parameters

`signature`- `string`
`callback`- `implements JCallback`

### requestSequence
```javascript
/** get sequence with jingtum address. */
exchange.requestSequence(address);
```

Parameters

`address`- `string`

### transferToken
```javascript
exchange.transferToken(signature, callback);
```

Parameters

`signature`- `string`
`callback`- `implements JCallback`

## JccdexInfo API
```javascript
JccdexInfo info = JccdexInfo.getInstance();
info.setmBaseUrl(jccUrl);
```

### requestTicker
```javascript
info.requestTicker(base, counter, callBack);
// info.requestTicker("swt", "cnt", mockCallBack);
```

Parameters

`signature`- `string`
`counter`- `string`
`callback`- `implements JCallback`

### requestAllTickers
```javascript
info.requestAllTickers(callBack);
```

Parameters

`callback`- `implements JCallback`

### requestDepth
```javascript
info.requestDepth(base, counter, type, callBack);
// info.requestDepth("swt", "cnt", "normal", mockCallBack);
```

Parameters

`signature`- `string`
`counter`- `string`
`type`- `string {more | normal}`
`callback`- `implements JCallback`

### requestKline
```javascript
info.requestKline(base, counter, type, callBack);
// info.requestDepth("swt", "cnt", "normal", mockCallBack);
```

Parameters

`signature`- `string`
`counter`- `string`
`type`- `string {hour | day | week | month}`
`callback`- `implements JCallback`

### requestHistory
```javascript
info.requestHistory(base, counter, type, time, callBack);
// String unixtime = String.valueOf(System.currentTimeMillis() / 1000);
// info.requestHistory("swt", "cnt", "newest", unixtime, mockCallBack);
```

Parameters

`signature`- `string`
`counter`- `string`
`type`- `string {all | more | newest}`
`time`- `string {Unix time}`
`callback`- `implements JCallback`

### requestTickerFromCMC
```javascript
/**the token value includes eth and btc, the currency value includes cny and rub so far.*/
info.requestTickerFromCMC(token, currency, callBack)
```

Parameters

`token`- `string`
`currency`- `string`
`callback`- `implements JCallback`