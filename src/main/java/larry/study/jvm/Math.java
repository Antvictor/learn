package larry.study.jvm;

import com.alibaba.fastjson.JSONArray;
import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

/**
 * @author exccedy
 * @date 2022/10/14
 **/
public class Math {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println(Math.class.getClassLoader());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("bootstrapClassLoader: " + bootstrapClassLoader);
        System.out.println("extClassLoader: " + extClassLoader);
        System.out.println("appClassLoader: " + appClassLoader);


        System.out.println();
        System.out.println("bootstrapClassLoader 加载：");
        System.out.println(JSONArray.toJSONString(Launcher.getBootstrapClassPath().getURLs()));

        System.out.println();
        System.out.println("extClassLoader 加载：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader 加载：");
        System.out.println(System.getProperty("java.class.path"));


    }
}
