package antvictor.study.design.liskov;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Antvictor
 * @date 2023/12/14
 **/
public class Son extends Father{

    /**
     * 实验子类入参比父类小
     * @param map
     */
    public void min(HashMap map) {
        System.out.println("this is son min");
    }


    /**
     * 实验子类入参比父类大
     * @param map
     */
    public void max(Map map) {
        System.out.println("this is son max");
    }

    /**
     * 实现重写父类方法
     * @param map
     */
    @Override
    public void eq(HashMap map) {
        System.out.println("this is son eq");
    }
}
