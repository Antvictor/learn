package yangchao.study.testwxpay;



import yangchao.study.testwxpay.sdk.IWXPayDomain;
import yangchao.study.testwxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public  class MyConfig extends WXPayConfig {

    private byte[] certData;


    protected String getAppID() {
        return "wx115781aa1e9ccf1b";
    }

    protected String getMchID() {
        return "1600252619";
    }

    protected String getKey() {
        return "wx115781aa1e9ccf1b1600252619tian";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    protected IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", true);
            }
        };
    }


}
