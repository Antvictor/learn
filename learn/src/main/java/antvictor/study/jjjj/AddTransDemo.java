package antvictor.study.jjjj;

import cn.tdchain.Trans;
import cn.tdchain.TransHead;
import cn.tdchain.jbcc.Result;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * function：add a trans
 * <p>
 * datetime：2019-03-26 16:03
 * author：warne
 */
public class AddTransDemo extends BaseDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            sendTrans();
        }
    }

    public static void sendTrans() {
        //# 构建出一笔交易信息
        Trans trans = trans();
        trans.setKey("test");
        trans.setData("test");
        trans.setType("test");
        //# 发起一笔交易到云区块链服务中
        Result<TransHead> result = connection.addTrans(trans);

        if (result.isSuccess()) {
            //# 根据返回结果状态判断是否成功
            log.info("\n===> add trans success.");
        } else {
            log.info("\n===> add trans fail.");
        }

        System.out.println(result);
    }

    /**
     * 构造一笔交易信息
     *
     * @return
     */
    public static Trans trans() {
        Trans trans = new Trans();
        trans.setKey("warne");//# key是当前交易的维度
        Map<String, Object> data = new HashMap<>();
        data.put("name", "warne");
        data.put("age", 20);
        data.put("where", "I am tian de technology.");
        trans.setData(JSON.toJSONString(data));
        trans.setType("Test");
        trans.setTimestamp(System.currentTimeMillis());

        return trans;
    }
}
