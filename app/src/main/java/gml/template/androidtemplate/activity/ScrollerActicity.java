package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import gml.template.androidtemplate.R;
import gml.template.androidtemplate.view.ScrollerTest;

/**
 * Created by guomenglong on 15/3/12.
 */
public class ScrollerActicity extends Activity {
    private ScrollerTest main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testscrollertest);
        main = (ScrollerTest)findViewById(R.id.main);
    }

    public void clickScroll(View view){
        main.startScroll();
    }
}
