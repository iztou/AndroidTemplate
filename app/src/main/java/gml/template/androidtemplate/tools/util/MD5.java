package gml.template.androidtemplate.tools.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public MD5() {
    }

    public static String encode(String source) {
        try {
            char result[];
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte sbs[] = source.getBytes("UTF8");
            digester.update(sbs);
            byte rbs[] = digester.digest();
            int j = rbs.length;
            result = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = rbs[i];
                result[k++] = hexs[b >>> 4 & 15];
                result[k++] = hexs[b & 15];
            }

            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        System.out.println(encode("aʯ��ׯ"));
        try {
            String s = "������֣�";

            char[] chars = s.toCharArray(); // ���ַ���ת��Ϊ�ַ�����

            System.out.println("\n\n���� ASCII\n----------------------");
            for (int i = 0; i < chars.length; i++) {// 输出结果

                System.out.println(" " + chars[i] + " " + (int) chars[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 登录注册时用,缓存名称加密
     *
     * @param sourceStr
     * @return
     */
    public static String MD532(String sourceStr) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
        }
        return buf.toString();
    }

    private static char hexs[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };
}
