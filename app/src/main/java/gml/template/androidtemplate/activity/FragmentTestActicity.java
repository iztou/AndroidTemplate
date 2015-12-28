package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import gml.template.androidtemplate.R;
import gml.template.androidtemplate.fragment.FragmentTest1;
import gml.template.androidtemplate.fragment.FragmentTest2;

/**
 * Created by guomenglong on 15/3/16.
 */
public class FragmentTestActicity extends FragmentActivity {

    private FragmentTest1 test1;
    private FragmentTest2 test2;
    private static int fade = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentactivitytest);
        if(findViewById(R.id.singleView)!=null){
            if(savedInstanceState!=null)
                return;
        }
        test1 = new FragmentTest1();
        test1.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction().add(R.id.singleView,test1).commit();
        Log.d("测试转屏",++fade + "");
    }

    @Override
    protected void onDestroy() {
        Log.d("测试转屏","触发销毁对象");
        super.onDestroy();
    }

    public void fragmentView2Click(View view){
        Log.d("测试","Fragment点击事件");
    }
}
