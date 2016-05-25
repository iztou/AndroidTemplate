package gml.template.androidtemplate.activity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 16/5/24.
 */

public class XMLParseActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlparse_act);
    }

    public void beginParse(View view) {
        getData();
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        XmlResourceParser xrp = getResources().getXml(R.xml.ceshi);
        try {
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("t")) {
//                        System.out.println("标签名字===>" + tagName + ",标签内容====>" + xrp.next());
//                        Map<String, String> map = new HashMap<String, String>();
//                        String id = xrp.getAttributeValue(null, "id");// 通过属性名来获取属性值
//                        map.put("id", id);
//                        String url = xrp.getAttributeValue(1);// 通过属性索引来获取属性值
//                        map.put("url", url);
//                        map.put("name", xrp.nextText());
//                        list.add(map);
                    }
                }
                if (xrp.getEventType() == XmlResourceParser.TEXT) {
                    String result = xrp.getText();
                    if (xrp.next() != XmlResourceParser.END_TAG) {
                        throw new XmlPullParserException("event TEXT it must be immediately followed by END_TAG", xrp, null);
                    }
                    System.out.println("标签名字===>" + xrp.getName() + ",标签内容====>" + result);
                } else if (xrp.getEventType() == XmlResourceParser.END_TAG) {
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
}
