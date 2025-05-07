package antvictor.study.jna;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

/**
 * @author Antvictor
 * @date 2024/3/18
 **/
public interface MySign extends Library {
    MySign INSTANCE = Native.loadLibrary("Sign64", MySign.class);

    /**
     * 取卡号<br>
     * UINT GetCardID(BYTE* szCardID, UINT* nCardIDLen)
     *
     * @param szCardID
     *            卡号，以\0结束
     * @param nCardIDLen
     *            卡号长度（该参数输入时不能等于0，必须是szCardID实际开辟的空间大小）
     * @return 0 取卡号成功 -1 卡初始化错 -2 取卡号失败
     *
     */
    int GetCardID(Memory szCardID, IntByReference nCardIDLen);

    /**
     * 取证书号 <br>
     * UINT GetCertNo(BYTE* szCertNo, UINT* nCertNoLen)
     *
     * @param szCertNo
     *            证书号，以\0结束
     * @param nCardIDLen
     *            证书号长度（该参数输入时不能等于0，必须是szCertNo实际开辟的空间大小）
     * @return 0 取证书号成功 -1 卡初始化错 -2 取证书号失败
     *
     */
    int GetCertNo(Memory szCertNo, IntByReference nCardIDLen);

    /**签名
     * UINT Sign(BYTE *src, UINT srcLen, BYTE *sign, UINT* signLen, const
     * char * pwd)
     *
     * @param src
     *            待签名的原始数据
     * @param srcLen
     *            待签名的原始数据的长度
     * @param sign
     *            签名数据，至少分配128字节
     * @param signLen
     *            签名数据长度，应大于128个字节，输入时应等于szSignData实际分配的空间大小
     * @param pwd
     *            进行加签的卡密码
     * @return 0 签名成功 -1 卡初始化错 -2 卡口令不正确 -3 签名失败 -4 PEM编码失败
     *
     */
    int Sign(String src, int srcLen, Memory sign, IntByReference signLen, String pwd);
}
