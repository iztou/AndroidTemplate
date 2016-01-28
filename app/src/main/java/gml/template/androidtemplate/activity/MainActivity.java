package gml.template.androidtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.adapter.ModelAdapter;
import gml.template.androidtemplate.adapter.ModelItems;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Bind(R.id.entryList)
    ListView entryList; //入口界面ListView
    @Bind(R.id.mainLayout)
    PtrClassicFrameLayout pullToRefresh;
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefresh.setPullToRefresh(true);
        initDrawerToggle();
        fillData();
        initListener();
    }

    private void initDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_test1:
                        msg += "Click test1";
                        break;
                    case R.id.action_test2:
                        msg += "Click test2";
                        break;
                    case R.id.action_settings:
                        msg += "Click setting";
                        break;
                }

                if (!msg.equals("")) {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        pullToRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        int times = 5;
                        long currentTime = System.currentTimeMillis();
                        while (true) {
                            long dyTime = System.currentTimeMillis();
                            if ((dyTime - currentTime) / 1000 == 1) {
                                subscriber.onNext(times--);
                                currentTime = dyTime;
                                if (times == -1) {
                                    subscriber.onCompleted();
                                    break;
                                }
                            }
                        }
                    }
                }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer == 0) {
                            pullToRefresh.refreshComplete();
                        }
                    }
                });
            }
        });
    }

    /**
     * 填充数据
     */
    private void fillData() {
        ModelAdapter modelAdapter = new ModelAdapter(this);
        ArrayList<ModelItems> itemsArrayList = new ArrayList<>();
        itemsArrayList.add(ModelItems.createNewInstance("向左滑动删除", SwipeLayoutActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("滑动固定", MyScrollActicity.class));
        itemsArrayList.add(ModelItems.createNewInstance("测试滑动过程中并缩小", SwipeOtherActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("RxJava测试", RxJavaActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Android Studio生成Activity测试", FullscreenActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("图片选择器测试", PictureSelectorActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("自定义图片选择器示例", PhotoSelectedActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Base64加解密示例", Base64Activity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Volley+OkHttp示例", OkHttpActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Retrofit+OkHttp示例", RetrofitOkHttpActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("ViewPager指示器示例", PageSlidingTabActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("GreenDao示例", GreenDaoActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("动画示例", AnimationActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("自定义形状示例", CustomizeShapeActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Fresco示例", FrescoActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("RecyclerViewActivity示例", RecycleViewActivity.class));
        modelAdapter.setModelItems(itemsArrayList.toArray(new ModelItems[0]));
        entryList.setAdapter(modelAdapter);
    }

    @OnItemClick(R.id.entryList)
    public void onItemClick(int position) {
        ModelItems modelItems = (ModelItems) entryList.getAdapter().getItem(position);
        Intent intent = new Intent(this, modelItems.getActivity());
        startActivity(intent);
    }

}
