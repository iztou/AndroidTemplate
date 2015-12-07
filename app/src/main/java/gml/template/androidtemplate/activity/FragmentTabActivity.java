package gml.template.androidtemplate.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import de.greenrobot.event.EventBus;
import gml.template.androidtemplate.view.CustomViewPager;
import gml.template.androidtemplate.fragment.FragmentTab1;
import gml.template.androidtemplate.fragment.FragmentTab2;
import gml.template.androidtemplate.fragment.FragmentTab3;
import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/24.
 */
public class FragmentTabActivity extends FragmentActivity {
    private CustomViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabfragment);
        pager = (CustomViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getFragmentManager()));
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 进入下一个页面
     * @param view
     */
    public void onNext(View view){
        EventBus.getDefault().post(FragmentTabActivity.NextPage.SECOND);
    }

    /**
     *
     * @param a
     */
    public void onEvent(NextPage a){
        int index = a.ordinal();
        pager.setCurrentItem(index);
    }

    public static enum NextPage{
        FIRST, SECOND, THIRD;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new FragmentTab1();
                    break;
                case 1:
                    fragment = new FragmentTab2();
                    break;
                case 2:
                    fragment = new FragmentTab3();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
