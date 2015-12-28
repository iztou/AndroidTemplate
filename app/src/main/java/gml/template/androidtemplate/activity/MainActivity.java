package gml.template.androidtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.adapter.ModelAdapter;
import gml.template.androidtemplate.adapter.ModelItems;

public class MainActivity extends BaseActivity{

    @Bind(R.id.entryList)
    ListView entryList; //入口界面ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
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
        itemsArrayList.add(ModelItems.createNewInstance("FragmentActivity测试", FragmentTestActicity.class));
        itemsArrayList.add(ModelItems.createNewInstance("RxJava测试", RxJavaActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Android Studio生成Activity测试", FullscreenActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("TabFragment 测试", FragmentTabActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("图片选择器测试", PictureSelectorActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("GridLayout测试", TestGridLayoutActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Base64加解密示例", Base64Activity.class));
        itemsArrayList.add(ModelItems.createNewInstance("Volley+OkHttp示例", OkHttpActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("ViewPager指示器示例", PageSlidingTabActivity.class));
        itemsArrayList.add(ModelItems.createNewInstance("GreenDao示例", GreenDaoActivity.class));
        modelAdapter.setModelItems(itemsArrayList.toArray(new ModelItems[0]));
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

    @OnItemClick(R.id.entryList)
    public void onItemClick(int position) {
        ModelItems modelItems = (ModelItems) entryList.getAdapter().getItem(position);
        Intent intent = new Intent(this,modelItems.getActivity());
        startActivity(intent);
    }

}
