package gml.template.androidtemplate.tools.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Created by guomenglong on 14/11/16.
 */
public class Util {

    private static String mDeviceImei;

    /**
     * 查询当前手机的电话号码
     *
     * @param context
     */
    public static void queryUserContent(Context context) {
        //得到ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        JSONArray fastJSONArray = new JSONArray();
        while (cursor.moveToNext()) {
            JSONObject user = new JSONObject();
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String name = cursor.getString(nameFieldColumnIndex);
            user.put("name", name);
            // 取得联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                    + contactId, null, null);

            // 取得电话号码(可能存在多个号码)
            while (phone.moveToNext()) {
                String strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                strPhoneNumber = strPhoneNumber.replaceAll("(\\+86)*[\\s\\-]", "");
                strPhoneNumber = EncryptUtil.encryptMobile(strPhoneNumber);
                user.put("mobile", strPhoneNumber);
            }
            fastJSONArray.add(user);
            phone.close();
        }
        System.out.println("当前用户通讯录JSONObject====>" + fastJSONArray.toJSONString());
        cursor.close();
    }

    /**
     * 验证String 是否为空
     *
     * @param s
     * @return
     */
    public static boolean isNullStr(String s) {
        return s == null || "".equals(s) || "NULL".equalsIgnoreCase(s);
    }


    /**
     * 把中文转成Unicode码
     *
     * @param str
     * @return
     */
    public String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {//汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }


    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 通过反射设置某个类的字段可见
     *
     * @param clazz
     * @param fieldName
     */
    public static void setFieldVisible(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏虚拟键盘
     * @param activity  想要隐藏的Activity
     */
    public static void hideInputKeyBoard(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }


    /**
     * 当键盘显示以后
     * @param activity
     * @param rootid
     * @param callBack
     */
    public  static void checkKeyBoardVisible(Activity activity,int rootid,KeyBoardVisibleCallBack callBack){
        final View activityRootView = activity.findViewById(rootid);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                }
            }
        });
    }

    /**
     * 获取imei,如果获取不到，则生成一个15位号码
     *
     * @return 获取imei,如果获取不到，则生成一个15位号码
     */
    public static String getImei(final Context context) {
        if (!TextUtils.isEmpty(mDeviceImei)) {
            return mDeviceImei;
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei;

        try {
            imei = tm.getDeviceId();// imei
        } catch (Exception e) {
            imei = null;
        }

        // 　(ly) 转为小写
        if (imei != null) {
            mDeviceImei = imei = imei.toLowerCase(Locale.US);
        }
        return imei;
    }

    /**
     * 键盘显示后调用接口
     */
    public interface KeyBoardVisibleCallBack{
        void changed();
    }
}
