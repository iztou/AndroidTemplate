package gml.template.androidtemplate.pagerslidingtab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import gml.template.androidtemplate.R;

public class PageSlidingTabActivity extends Activity {

    private PagerSlidingTabStrip strip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sliding_tab_acticity);
        initView();
    }

    private void initView(){
        strip = (PagerSlidingTabStrip) findViewById(R.id.strip);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        SimplePagerAdapter simplePagerAdapter = new SimplePagerAdapter();
        viewPager.setAdapter(simplePagerAdapter);
        strip.setViewPager(viewPager);
    }

    class SimplePagerAdapter extends PagerAdapter{

        private final String[] titles = new String[]{"页面1","长标题页面","短","超长标题页面啊啊","页面5","页面6","页面7","页面8","页面9"};
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
            textView.setText("第" + ++position + "页");
            textView.setTextColor(0xff111111);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
