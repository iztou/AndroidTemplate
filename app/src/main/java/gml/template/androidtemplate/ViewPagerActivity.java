package gml.template.androidtemplate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guomenglong on 14/11/27.
 */
public class ViewPagerActivity extends Activity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        List<View> listViews = new ArrayList<View>();
        View v = new View(this);
        v.setTag(0);
        v.setLayoutParams(new ViewPager.LayoutParams());
        v.setBackgroundResource(android.R.color.background_dark);
        listViews.add(v);

        v = new View(this);
        v.setTag(1);
        v.setLayoutParams(new ViewPager.LayoutParams());
        v.setBackgroundResource(android.R.color.darker_gray);
        listViews.add(v);

//        v = new View(this);
//        v.setTag(2);
//        v.setLayoutParams(new ViewPager.LayoutParams());
//        v.setBackgroundResource(android.R.color.holo_green_light);
//        listViews.add(v);

//        v = new View(this);
//        v.setTag(3);
//        v.setLayoutParams(new ViewPager.LayoutParams());
//        v.setBackgroundResource(android.R.color.holo_purple);
//        listViews.add(v);

        MyPageAdapter ma = new MyPageAdapter();
        ma.addList(listViews);

        vp = (ViewPager) findViewById(R.id.testViewPager);
        vp.setAdapter(ma);
        vp.setCurrentItem(listViews.size() * 100);
    }

    class MyPageAdapter extends PagerAdapter {

        private List<View> list;

        public void addList(List<View> list) {
            this.list = list;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (list.size() > 3) {
                System.out.println("======>移除第" + (position % list.size()) + "页");
                container.removeView(list.get(position % list.size()));
            }
        }

        @Override
        public int getCount() {
            return list.size() <= 2 ? list.size() : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("===========>预加载第" + (position % list.size()) + "页,真实位置:" + position);
            if (list.get(position % list.size()).getParent() != null)
                container.removeView(list.get(position % list.size()));
            container.addView(list.get(position % list.size()));
            return list.get(position % list.size());
        }

    }

}
