package yangchao.study.utils;

import cn.tdchain.Trans;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import yangchao.study.config.JbccClient;
import yangchao.study.entity.Test1;
import yangchao.study.entity.User;
import yangchao.study.testwxpay.MyConfig;
import yangchao.study.testwxpay.sdk.WXPay;
import yangchao.study.testwxpay.sdk.WXPayConstants;
import yangchao.study.testwxpay.sdk.WXPayUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import static org.apache.http.client.methods.RequestBuilder.put;

public class Demo {


    @Test
    public void test() {
//        Map<Long,Integer> longIntegerMap = new HashMap<>();
//        longIntegerMap.put(123456l,1);
//        longIntegerMap.put(133456l,1);
//        longIntegerMap.put(143456l,1);
//        longIntegerMap.put(153456l,1);
//
//        String jsonA = JSONArray.toJSONString(longIntegerMap);
//        System.out.println("jsonA:" + jsonA);
//
//        String json = JSON.toJSONString(jsonA);
//        System.out.println("json:" + json);
//
//        Object p = JSON.parse(json);
//        Map<Long,Integer> j =   JSONArray.parseObject(p.toString(),new TypeReference<Map<Long,Integer>>(){});
//
//        j.entrySet().forEach(System.out::println);


////        boolean flag = 1 / 0 ==0;
//        int num = 2147483647 ;
//        num += 2 ;
//        System.out.println(num) ;

     /*   TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
        simpleFormatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        Date zero = calendar.getTime();
        System.out.println(simpleFormatter.format(zero));
        System.out.println(zero.getTime());
        System.out.println(new Date());
        System.out.println(calendar.getTimeInMillis());*/

//       int a =  (int) Math.ceil((double) 15 / 10);
//        System.out.println(a);
//
//        List<Test1> test1s = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Test1 test1 = new Test1();
//            test1.setName("test" + i);
//            test1.setId(i);
//            test1s.add(test1);
//        }
//        List<String> id = test1s.stream().filter(i -> i.getId() == 4).map(Test1::getName).collect(Collectors.toList());
//
//        System.out.println(id.get(0));
//        System.out.println(JSON.toJSONString(test1s));

        // todo list集合是不是先进先出
        // 是
//        List<Integer> test = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            test.add(i);
//        }
//
//        test.forEach(System.out::println);
//
//        Map<String, String> test2 = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            test2.put(i + "i",i + "tt");
//        }
//        test2.values().forEach(v -> {
//            System.out.println(v);
//        });
//
//
//        int t = 1;
//        long ttt = 1;
//        System.out.println( t + ttt);
       /* Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("1","1");
        System.out.println(stringStringMap.toString());
        System.out.println(JSON.toJSONString(stringStringMap));
        System.out.println(JSON.toJSON(stringStringMap).toString());
        System.out.println(JSON.toJSON(stringStringMap));*/

//       Test1 test1 = new Test1();
//       Test1 test2 = new Test1();
//       test1.setId(3);
//       test2.setId(2);
//       test1.setName("呵呵");
//       int t = 0;
//        System.out.println(test1.getId());
//        testParam(test1, test2);
//        System.out.println(test1.getId());

     /*   JSONObject jsonObject = new JSONObject();
        jsonObject.put("reportId",1);
        jsonObject.put("fileId",2);
        jsonObject.put("reportPath",3);
        jsonObject.put("reportName",4);
        jsonObject.put("createTime",5);
        jsonObject.put("pdfMd5",6);


        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("key","report");
        paramMap.put("type","reportInfo");
        paramMap.put("data",jsonObject.toJSONString());
        System.out.println("====console.infocontent====="+jsonObject.toJSONString());
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        try {
            HttpClientResult httpClientResult = HttpClientUtil.doPost("http://open.tdchain.cn/jbccrest/api/trans", headerMap,paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
//        System.out.println(StringUtils.substringAfterLast("test.1.2.3.4.ppt","."));
//        Long i = 10050l;
//        Long y = 5 * 1024 * 1024l;
//        Double z = 4.0;
//        double s = 4.4;
//        System.out.println((i / y));
//
//        System.out.println(Math.ceil((z / s)));

//        byte[] test = new byte[]{1,2,3,4,5,6,7,8,9,10,11};
//        System.out.println(JSON.toJSONString(test));
//        for (int i = 0; i < 2; i++) {
//            byte[] ww = new  byte[24];
//
//            System.arraycopy(test,0, ww, i * 11, (i + 1) * 12);
//            System.out.println(JSON.toJSONString(ww));
//        }
//     Test1 test1 = new Test1();
//     test1.setName("telangpu·sb");
//
//        System.out.println(JSON.toJSONString(test1));
//        System.out.println("====console.infocontent====="+httpClientResult.getContent());
//        System.out.println("====console.info====="+httpClientResult.getCode());

    }

    public void testParam(Test1 test1, Test1 t){
        test1.setId(test1.getId() + t.getId());
    }
    public void testParam(Integer x){
        x = 5;
    }

//    static class  BugDemo{
//
//        public static void testBug() {
//            String[] strings = new String[5];
//
//            for (int i = 0; i < 6; i++) {
//                strings[i] = i + "";
//
//            }
//        }
//    }

    public static void main(String[] args){
//        Trans trans = new Trans();
//        trans.setKey("test");
//        trans.setData("test");
//
//        System.out.println(JbccClient.connection.getAccount());
//
//        JbccClient.connection.addTrans(trans);
       /* long z = 0;
        long x = 0;
        for (int i = 0; i < 10; i++) {

            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            z += startTime - endTime;
            x += (startTime - endTime);
        }

        System.out.println(z);
        System.out.println(x);*/

    /*   List<String> test = new ArrayList<>();
       List<String> test1 = new LinkedList<>();

       test.add("1");
       test.add("2");
       test1.add("1");
       test1.add("2");



       String[] strings = new String[10];

       strings[0] = "1";

        System.out.println(test);
        System.out.println(JSON.toJSONString(test));
        System.out.println(test1);
        System.out.println(strings);*/
        System.out.println(RandomUtils.nextInt());
        System.out.println(RandomUtils.nextInt(0,10000));
        System.out.println(RandomStringUtils.randomAlphabetic(5));
        System.out.println(RandomStringUtils.randomAlphanumeric(5));
        System.out.println(RandomStringUtils.randomAscii(5));
        System.out.println(RandomStringUtils.randomGraph(5));
        System.out.println(RandomStringUtils.randomNumeric(5));
        System.out.println(RandomStringUtils.randomPrint(5));
    }

    @Test
    public void testMapNull() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("12");
        strings.add("13");
        strings.add("4");
        strings.forEach(s -> {
            System.out.println("t");
        });
        List<String> stringList = strings.stream().filter(s -> {return s.equals("1") || s.equals("4");}).collect(Collectors.toList());
        System.out.println(stringList);
        System.out.println("没报错");
    }

    @Test
    public void testLabel() {

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");


        label:
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size() ; j++) {
                if (strings.get(j).equals("3")) {
                    break label;
                }
                System.out.println("??" + j);
            }
            System.out.println("++++++" + strings.get(i));

        }

        System.out.println("----------------");

        System.out.println("----------------");
        System.out.println("forEach方法测试");
        strings.forEach(s -> {
            if (s.equals("3")) {

            }
            System.out.println(s);
        });

        System.out.println("直接结束？");
        System.out.println("----------------");

        System.out.println("forEach lom 测试");
        test: for (String string : strings) {
            for (String s: strings) {
                if (s.equals("3")) {
                    break test;
                }
                System.out.println("内循环" + s);
            }
            System.out.println(string + "----");
        }
        System.out.println("---------");
        for (String string : strings) {
            for (String s: strings) {
                if (s.equals("3")) {
                    return;
                }
                System.out.println("内循环" + s);
            }
            System.out.println(string + "----");
        }

        System.out.println("----------------");

        for (String string : strings) {
            if (string.equals("3")) {
                continue;
            }
            System.out.println(string + "====");
        }

        System.out.println("----------------");


        for (String string : strings) {
            if (string.equals("3")) {
                return;
            }
            System.out.println(string + "====");
        }


        System.out.println("直接结束？");

    }

    @Test
    public void testLabel2() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");

        label:
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size() ; j++) {
                if (strings.get(j).equals("3")) {
                     continue label;
                }
                System.out.println("??1   " + strings.get(j));
            }
            System.out.println("++++++1  " + strings.get(i));

        }

        label:
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size() ; j++) {
                if (strings.get(j).equals("3")) {
                    break label;
                }
                System.out.println("??2  " + strings.get(j));
            }
            System.out.println("++++++2  " + strings.get(i));

        }

    }

    @Test
    public void testReturn() {
        System.out.println("hahah");
        testR();
        System.out.println("hahah");
    }

    public void testR() {
        System.out.println("呵呵呵");
        return;
    }

    @Test
    public void testJSONString() throws Exception {
       /* User user  = JSON.parseObject("{}", User.class);
        System.out.println(user);

        String [] strings = new String[]{};
        System.out.println(strings.length -1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = simpleDateFormat.parse("20141030133525");
        System.out.println(date);
        System.out.println(date.getTime());

        Map<String, String> map = new ConcurrentHashMap();
        map.put("2","3");
        map.put("3","3");
        map.put("1","2");
        ((ConcurrentHashMap<String, String>) map).forEach((k,v) -> {
            if ("3".equals(v)) {
                map.remove(k);
            }
        });*/

//        System.out.println(map);
        // {transaction_id=4200000771202009176663843100, nonce_str=2dkq6nNwnPcgJhDUd19wWB0zyM7n91rP, bank_type=OTHERS, openid=oT_YhwYWPTUX38UWzI
        //dtb_fXhf7Q, sign=AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237, fee_type=CNY, mch_id=1600252619, cash_fee=1, out_trade_no=
        //cae9a3fd314c42888dcd1570833714c7, appid=wx115781aa1e9ccf1b, total_fee=1, trade_type=NATIVE, result_code=SUCCESS, time_end=20200917174427, is_
        //subscribe=Y, return_code=SUCCESS}
        Map map = new HashMap(){{
            put("transaction_id","4200000771202009176663843100");
            put("nonce_str","2dkq6nNwnPcgJhDUd19wWB0zyM7n91rP");
            put("bank_type","OTHERS");
            put("openid","oT_YhwYWPTUX38UWzIdtb_fXhf7Q");
            put("sign","AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237");
            put("fee_type","CNY");
            put("mch_id","1600252619");
            put("cash_fee","1");
            put("out_trade_no","cae9a3fd314c42888dcd1570833714c7");
            put("appid","wx115781aa1e9ccf1b");
            put("total_fee","1");
            put("trade_type","NATIVE");
            put("result_code","SUCCESS");
            put("time_end","20200917174427");
            put("is_subscribe","Y");
            put("return_code","SUCCESS");
        }};

        map.put(WXPayConstants.FIELD_SIGN_TYPE,WXPayConstants.HMACSHA256);
        MyConfig myConfig = new MyConfig();
        WXPay wxPay = new WXPay(myConfig);

        System.out.println(wxPay.isPayResultNotifySignatureValid(map));

        System.out.println(WXPayUtil.generateSignature(map, "wx115781aa1e9ccf1b1600252619tian", WXPayConstants.SignType.HMACSHA256));

        //D997ADD1CED2869C2F6ADDA60A0505F5   没有sign
        // D997ADD1CED2869C2F6ADDA60A0505F5 有sign

        // sha256   AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237
        // AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237
//        System.out.println("AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237".equals("AF028057FB214BFA620F20CD23C2133878151F11D9C3849689409252CB0E8237"));
    }


}
