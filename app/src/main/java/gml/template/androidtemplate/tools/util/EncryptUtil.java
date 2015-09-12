package gml.template.androidtemplate.tools.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by 58 on 2015/9/12.
 */
public final class EncryptUtil {
    public static final String ENCRYPT_KEY = "yseifjgjcgdIueIeNUbn";

    private EncryptUtil() {
    }

    /**
     * 利用Base64进行加密
     *
     * @param mobile
     * @return
     */
    public static String encryptMobile(String mobile) {
        if (Util.isNullStr(mobile)) {
            return null;
        }
        char[] chars = mobile.toCharArray();
        char[] keys = ENCRYPT_KEY.toCharArray();
        String temp = "";
        for (int i = 0; i < chars.length; i++) {
            temp += (char) (chars[i] ^ keys[i % keys.length]);
        }
        try {
            byte[] b = temp.getBytes("utf-8");
            return Base64.encodeToString(b, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用Base64进行解密
     *
     * @param encryptStr
     * @return
     */
    public static String decryptMobile(String encryptStr) {
        if (Util.isNullStr(encryptStr)) {
            return null;
        }
        byte[] decode = Base64.decode(encryptStr, Base64.DEFAULT);
        String decodeStr = new String(decode);
        char[] chars = decodeStr.toCharArray();
        char[] keys = ENCRYPT_KEY.toCharArray();
        String temp = "";
        for (int i = 0; i < chars.length; i++) {
            temp += (char) (chars[i] ^ keys[i % keys.length]);
        }
        return temp;
    }
}
