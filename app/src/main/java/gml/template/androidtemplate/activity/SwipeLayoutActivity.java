package gml.template.androidtemplate.activity;

import android.os.Bundle;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/3.
 */
public class SwipeLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipetest);
        toolbar.setTitle("左划布局");
    }

}
