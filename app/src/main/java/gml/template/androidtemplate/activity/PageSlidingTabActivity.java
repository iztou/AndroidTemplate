package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.phrase.ListPhrase;
import com.squareup.phrase.Phrase;

import java.util.ArrayList;
import java.util.List;

import gml.template.androidtemplate.R;
import gml.template.androidtemplate.util.ColorPhrase;

public class PageSlidingTabActivity extends Activity implements LocationListener {

    private PagerSlidingTabStrip strip;
    private ViewPager viewPager;
    private double latitude;
    private double longitude;
    private double altitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sliding_tab_acticity);
        initView();
        startLocationService();
    }

    private void initView() {
        strip = (PagerSlidingTabStrip) findViewById(R.id.strip);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        SimplePagerAdapter simplePagerAdapter = new SimplePagerAdapter();
        viewPager.setAdapter(simplePagerAdapter);
        strip.setViewPager(viewPager);
    }

    /**
     * 获取位置信息服务
     */
    private void startLocationService() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();     //经度
            longitude = location.getLongitude(); //纬度
            altitude = location.getAltitude();     //海拔
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        // log it when the location changes
        if (location != null) {
            Log.i("SuperMap", "Location changed : Lat: "
                    + location.getLatitude() + " Lng: "
                    + location.getLongitude());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
            CharSequence location = Phrase.from("经度：{latitude} \n 维度：{longitude} \n 海拔:{altitude}")
                    .put("latitude", String.valueOf(latitude))
                    .put("longitude", String.valueOf(longitude))
                    .put("altitude", String.valueOf(altitude))
                    .format();
            List<String> formatString = new ArrayList<>();
            formatString.add("1");
            formatString.add("2");
            formatString.add("3");
            CharSequence result = ListPhrase.from(",").join(formatString);
            CharSequence chars = ColorPhrase.from("ColorPhrase字符颜色替换处理：\n I'm {Chinese},I love {China} \n").innerColor(0xFFE6454A).outerColor(0xFF666666).format();
            if (position == 0) {
                textView.setText(chars);
                textView.append(formatted + "\n" + result + "\n" + location);
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
