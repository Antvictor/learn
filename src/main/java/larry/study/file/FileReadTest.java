package larry.study.file;

import cn.tdchain.cipher.utils.StringUtils;
import com.sun.imageio.plugins.common.ReaderUtil;
import com.sun.xml.internal.messaging.saaj.util.CharReader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author exccedy
 * @date 2022/12/13
 **/
public class FileReadTest {
    private static Pattern p =  Pattern.compile("[\u4e00-\u9fa5]");
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/exccedy/project/common/study/src/main/java/larry/study/future/FutureDemo.java");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String s = null;

        while ((s = reader.readLine()) != null) {
            System.out.println(chineseStr(s));
        }

    }


    private static String chineseStr(String str) {
        Matcher m = null;
        String value = "";
        m = p.matcher(str);
        if (StringUtils.isNotBlank(str)) {
            while (m.find()) {
                if (m.group(0) != null) {
                    value += m.group(0);
                }
            }
        }
        return value;

    }
}
