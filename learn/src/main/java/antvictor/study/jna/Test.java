package antvictor.study.jna;

import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;

/**
 * @author Antvictor
 * @date 2024/3/18
 **/
public class Test {
    public static void main(String[] args) {

        Memory memory = new Memory(100);
        IntByReference intby = new IntByReference(100);
        System.out.println("**********************取卡号**********************");
        int getCardID = MySign.INSTANCE.GetCardID(memory, intby);
        System.out.println(getCardID);
        System.out.println(intby.getValue());
        System.out.println(new String(memory.getByteArray(0, intby.getValue())));

        Memory memory1 = new Memory(100);
        System.out.println("**********************取证书号**********************");
        int getCertNo = MySign.INSTANCE.GetCertNo(memory1, intby);
        System.out.println("获取状态：" + getCertNo);
        System.out.println(intby.getValue());
        System.out.println("证书号:" + new String(memory1.getByteArray(0, intby.getValue())));

        Memory sign = new Memory(100);
        IntByReference intbyReference = new IntByReference(100);
        String src = "testsssssss";
        System.out.println("**********************数据加签**********************");
        int sign2 = MySign.INSTANCE.Sign(src, src.length(), sign, intbyReference, "12345678");
        System.out.println(sign2);
        System.out.println(new String(sign.getByteArray(0, intbyReference.getValue())));

    }
}
