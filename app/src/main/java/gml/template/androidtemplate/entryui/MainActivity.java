package gml.template.androidtemplate.entryui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import gml.template.androidtemplate.activity.EventBus1;
import gml.template.androidtemplate.activity.FragmentTabActivity;
import gml.template.androidtemplate.activity.FragmentTestActicity;
import gml.template.androidtemplate.activity.FullscreenActivity;
import gml.template.androidtemplate.activity.MyScrollActicity;
import gml.template.androidtemplate.activity.PictureSelectorActivity;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.activity.RxJavaActivity;
import gml.template.androidtemplate.activity.ScrollerActicity;
import gml.template.androidtemplate.activity.SwipeLayoutActivity;
import gml.template.androidtemplate.activity.SwipeOtherActivity;
import gml.template.androidtemplate.activity.TestGridLayoutActivity;
import gml.template.androidtemplate.encrypt.Base64Activity;
import gml.template.androidtemplate.okhttp.OkHttpActivity;
import gml.template.androidtemplate.pagerslidingtab.PageSlidingTabActivity;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView entryList; //入口界面ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //60000 倒计时60秒 间隔 1秒
//        MyCountTimer myCountTimer = new MyCountTimer(10000,1000);
//        myCountTimer.start();
//        TypefaceHelper.typeface(this);
        initView();
        fillData();
    }

    /**
     * 初始化界面
     */
    private void initView(){
        entryList = (ListView)findViewById(R.id.entryList);
        entryList.setOnItemClickListener(this);
    }

    /**
     * 填充数据
     */
    private void fillData(){
        ModelAdapter modelAdapter = new ModelAdapter(this);
        ArrayList<ModelItems> itemsArrayList = new ArrayList<>();
        itemsArrayList.add(ModelItems.createNewInstance("向左滑动删除", SwipeLayoutActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("滑动固定", MyScrollActicity.class));
        itemsArrayList.add(ModelItems.createNewInstance("测试滑动过程中并缩小", SwipeOtherActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("scroller测试", ScrollerActicity.class));
        itemsArrayList.add(ModelItems.createNewInstance("EventBus测试", EventBus1.class));
        itemsArrayList.add(ModelItems.createNewInstance("FragmentActivity测试", FragmentTestActicity.class));
        itemsArrayList.add(ModelItems.createNewInstance("RxJava测试", RxJavaActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Android Studio生成Activity测试", FullscreenActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("TabFragment 测试", FragmentTabActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("图片选择器测试", PictureSelectorActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("GridLayout测试", TestGridLayoutActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Base64加解密示例", Base64Activity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Volley+OkHttp示例", OkHttpActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("ViewPager指示器示例", PageSlidingTabActivity.class));
        modelAdapter.setModelItemses(itemsArrayList.toArray(new ModelItems[0]));
        entryList.setAdapter(modelAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Setting_ICON",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_test1:
                Toast.makeText(this,"Setting_TEST",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ModelItems modelItems = (ModelItems) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this,modelItems.getActivity());
        startActivity(intent);
    }

    private class MyCountTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            System.out.println(millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            System.out.println("倒计时结束...");
        }
    }

}
