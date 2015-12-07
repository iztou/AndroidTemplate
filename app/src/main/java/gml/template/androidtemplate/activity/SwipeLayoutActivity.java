package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/3.
 */
public class SwipeLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipetest);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this,"点击返回主页面",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 隐藏ActionBar
     * @param view
     */
    public void hideActionBar(View view){
        if(getActionBar().isShowing()){
            getActionBar().hide();
        }else{
            getActionBar().show();
        }
    }
}
