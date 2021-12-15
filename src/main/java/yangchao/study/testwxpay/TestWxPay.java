package yangchao.study.testwxpay;

import yangchao.study.testwxpay.sdk.WXPay;
import yangchao.study.testwxpay.sdk.WXPayConstants;

import java.util.HashMap;
import java.util.Map;

public class TestWxPay {
    public static void main(String[] args) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值"); // 商品描述，必填
        data.put("out_trade_no", "2016090910595900000012");// 商品订单号，项目内部使用，必填
        data.put("device_info", ""); // 设备号，选填
        data.put("fee_type", "CNY"); // 货币，默认人民币
        data.put("total_fee", "1");// 订单总金额，单位为分
        data.put("spbill_create_ip", "123.12.12.123");// 服务器ip，其实写不写都行
        data.put("notify_url", "http://www.example.com/wxpay/notify");// 微信异步通知接口（回调接口）
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");// 商品id，用户自行定义

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
