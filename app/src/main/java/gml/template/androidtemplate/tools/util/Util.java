package gml.template.androidtemplate.tools.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by guomenglong on 14/11/16.
 */
public class Util {

    /**
     * 查询当前手机的电话号码
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
            user.put("name",name);
            // 取得联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                    + contactId, null, null);

            // 取得电话号码(可能存在多个号码)
            while (phone.moveToNext()) {
                String strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                strPhoneNumber = strPhoneNumber.replaceAll("(\\+86)*[\\s\\-]", "");
                strPhoneNumber = EncryptUtil.encryptMobile(strPhoneNumber);
                user.put("mobile",strPhoneNumber);
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

}
