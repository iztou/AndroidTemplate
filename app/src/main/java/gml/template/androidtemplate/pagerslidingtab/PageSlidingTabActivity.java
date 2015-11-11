package gml.template.androidtemplate.pagerslidingtab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.phrase.ListPhrase;
import com.squareup.phrase.Phrase;

import java.util.ArrayList;
import java.util.List;

import gml.template.androidtemplate.MyApplication;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.util.ColorPhrase;

public class PageSlidingTabActivity extends Activity {

    private PagerSlidingTabStrip strip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sliding_tab_acticity);
        initView();
    }

    private void initView() {
        strip = (PagerSlidingTabStrip) findViewById(R.id.strip);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        SimplePagerAdapter simplePagerAdapter = new SimplePagerAdapter();
        viewPager.setAdapter(simplePagerAdapter);
        strip.setViewPager(viewPager);
    }

    class SimplePagerAdapter extends PagerAdapter {

        private final String[] titles = new String[]{"页面1", "长标题页面", "短", "超长标题页面啊啊", "页面5", "页面6", "页面7", "页面8", "页面9"};

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(PageSlidingTabActivity.this);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            CharSequence formatted = Phrase.from("Phrase 字符串处理类示例: \n Hi {first_name}, you are {age} years old.")
                    .put("first_name", "颜龙")
                    .put("age", "19")
                    .format();
            List<String> formatString = new ArrayList<>();
            formatString.add("1");
            formatString.add("2");
            formatString.add("3");
            CharSequence result = ListPhrase.from(",").join(formatString);
            CharSequence chars = ColorPhrase.from("ColorPhrase字符颜色替换处理：\n I'm {Chinese},I love {China} \n").innerColor(0xFFE6454A).outerColor(0xFF666666).format();
            if (position == 0) {
                textView.setText(chars);
                textView.append(formatted + "\n" + result + "\n");
            } else {
                textView.setText("第" + ++position + "页");
            }
            textView.setTextColor(0xff111111);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
