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
        System.out.println(encode("a石家庄"));
        try {
            String s = "新年快乐！";

            char[] chars = s.toCharArray(); // 把字符中转换为字符数组

            System.out.println("\n\n汉字 ASCII\n----------------------");
            for (int i = 0; i < chars.length; i++) {// 杈撳嚭缁撴灉

                System.out.println(" " + chars[i] + " " + (int) chars[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 鐧诲綍娉ㄥ唽鏃剁敤,缂撳瓨鍚嶇О鍔犲瘑
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
