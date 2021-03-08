<!-- markdownlint-disable MD024 -->

# jcc_rpc_java

jcc rpc java version

[![Build Status](https://travis-ci.com/JCCDex/jcc_rpc_java.svg?branch=master)](https://travis-ci.com/JCCDex/jcc_rpc_java)
[![Coverage Status](https://coveralls.io/repos/github/JCCDex/jcc_rpc_java/badge.svg?branch=master)](https://coveralls.io/github/JCCDex/jcc_rpc_java?branch=master)
[![JitPack](https://jitpack.io/v/JCCDex/jcc_rpc_java.svg)](https://jitpack.io/#JCCDex/jcc_rpc_java)

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

### Create JccdexUrl

```javascript
JccdexUrl jccUrl = new JccdexUrl("xxx", true);// https://xxx:443
JccdexUrl jccUrl = new JccdexUrl("xxx", false);// http://xxx:80
or
JccdexUrl jccUrl = new JccdexUrl("xxx", true, 8081);// https://xxx:8081
```

## NodeRpc接口

### 转账

```java
// 需引入底层签名包 https://jitpack.io/#JCCDex/jingtum-lib-java/v1.0.4-alpha
// 转账地址
String account = "";
// 转账密钥
String secret = "";
// 转入地址
String to = "";
// 转账数量
String value = "";
// 转账token
String token = "";
// token发行地址
String issuer = "";
// token不为swt时，需要issuer和token名称
Amount amount = new Amount(new BigDecimal(value), token.toUpperCase(), issuer);

// token为swt时, 不需要issuer和token名称
// Amount amount = new Amount(new BigDecimal(1));
Payment payment = new Payment();
payment.as(AccountID.Account, account);
payment.as(AccountID.Destination, to);
payment.as(Amount.Amount, amount);
payment.as(Amount.Fee, "100");
payment.flags(new UInt32(0));

// 正式链的rpc节点可通过https://gateway.swtc.top/rpcservice获取
// 建议节点定期更新，防止因为节点变更导致不可用
JccdexUrl jccUrl = new JccdexUrl("124.93.26.68", false, 50333);
JccdexNodeRpc nodeRpc = JccdexNodeRpc.getInstance();
nodeRpc.setmBaseUrl(jccUrl);

// 获取交易sequence
nodeRpc.requestSequence(account, new JCallback() {

    @Override
    public void onResponse(String code, String response) {
        System.out.println(code);
        System.out.println(response);
        // code为success表示获取sequence成功
        if (code.equals("success")) {
            int sequence = JSONObject.parseObject(response).getJSONObject("result").getJSONObject("account_data").getIntValue("Sequence");
            payment.sequence(new UInt32(sequence));
            SignedTransaction tx = null;
            // 交易本地签名
            try {
                tx = payment.sign(secret);
            } catch (Exception e) {
                // 签名异常
                // 根据实际需求做下一步操作
                return;
            }
            // 交易hash, 和最后上链的hash一致。
            Hash256 hash = tx.hash;
            String blob = tx.tx_blob;
            System.out.println("交易blob: " + tx.tx_blob);
            System.out.println("交易hash: " + hash.toHex());
            // 提交签名内容发起转账
            nodeRpc.transfer(blob, new JCallback() {
                @Override
                public void onResponse(String code, String response) {
                    System.out.println(code);
                    System.out.println(response);
                    // code说明见http://developer.jingtum.com/error_code.html
                    // 一般情况下code为tesSUCCESS， 就表示交易成功
                    // 但是也存在在tesSUCCESS情况下，实际没有成功上链的情况，最好提交交易过后，根据hash做二次验证
                    // 节点每10s出块，建议异步验证交易详情，时间间隔建议最少10s之后
                    // 根据业务需求决定是否做二次验证

                    // 验证调用伪代码
                    // nodeRpc.requestTx(hash.toHex(), new JCallback())
            } else {
                // 获取sequence失败
                // 根据业务需求做下一步操作，建议10秒之后重新调用requestSequence获取sequence
                }
            }

    @Override
    public void onFail(Exception e) {
        // 异常状态，根据业务需求做下一步操作
        // 建议10秒之后重新调用requestSequence获取sequence
    }
});

```

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

## JccConfig API

```javascript
JccConfig config = JccConfig.getInstance();
config.setmBaseUrl(jccUrl);
```

### requestConfig

```javascript
config.requestConfig(callBack);
```

## JccExplore API

```javascript
JccExplore explore = JccExplore.getInstance();
explore.setmBaseUrl(jccUrl);
```

### requestBalance

通过浏览器接口获取地址余额

```java

// https://uploadletsdex.swtc.top/static/config/jc_hosts.json
// scanHosts为浏览器节点

JccdexUrl jccUrl = new JccdexUrl("expji39bdbdba1e1.swtc.top", true, 443);
JccExplore explore = JccExplore.getInstance();
explore.setmBaseUrl(jccUrl);
// 钱包地址
String address = "";
String uuid = address;
explore.requestBalance(uuid, address, callBack);


```

Parameters

`uuid`- `string`

`address`- `string`

`callback`- `implements JCallback`

### requestTransDetails

```javascript
explore.requestTransDetails(uuid, hash, callBack);
```

Parameters

`uuid`- `string`

`hash`- `string`

`callback`- `implements JCallback`

### requestHistoricTransWithAddr

```javascript
explore.requestHistoricTransWithAddr(uuid, page, size, begin, end, type, currency, address, callBack);
```

Parameters

`uuid`- `string`

`page`- `int`

`size`- `int`

`begin`- `string {xxxx-xx-xx}`

`end`- `string {xxxx-xx-xx}`

`type`- `string {Send、Receive}`

`currency`- `string`

`address`- `string`

`callback`- `implements JCallback`

### requestPaymentSummary

```javascript
explore.requestPaymentSummary(uuid, address, dateTpye, begin, end, type, token, callBack);
```

Parameters

`uuid`- `string`

`address`- `int`

`dateTpye`- `int`

`begin`- `string {xxxx-xx-xx}`

`end`- `string {xxxx-xx-xx}`

`type`- `string {Send、Receive}`

`token`-  `string`

`callback`- `implements JCallback`

### requestHistoricTransWithCur

```javascript
explore.requestHistoricTransWithCur(uuid, page, size, begin, end, type, currency, callBack);
```

Parameters

`uuid`- `string`

`page`- `int`

`size`- `int {10/20/50/100}`

`begin`- `string {xxxx-xx-xx}`

`end`- `string {xxxx-xx-xx}`

`type`- `string`

`currency`- `string`

`callback`- `implements JCallback`
