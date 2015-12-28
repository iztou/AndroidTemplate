package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gml.template.androidtemplate.R;
import gml.template.androidtemplate.fragment.FragmentTab1;
import gml.template.androidtemplate.fragment.FragmentTab2;
import gml.template.androidtemplate.fragment.FragmentTab3;
import gml.template.androidtemplate.view.CustomViewPager;

/**
 * Created by guomenglong on 15/3/24.
 */
public class FragmentTabActivity extends FragmentActivity {
    private CustomViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabfragment);
        pager = (CustomViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static enum NextPage {
        FIRST, SECOND, THIRD;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
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
