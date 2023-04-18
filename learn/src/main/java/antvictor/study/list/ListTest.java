package antvictor.study.list;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author exccedy
 * @date 2022/7/25
 **/
public abstract class ListTest {
    List<String>  s = new ArrayList<>();

    public abstract void add();

    public void print() {
        System.out.println(JSONArray.toJSONString(s));
    }
}
