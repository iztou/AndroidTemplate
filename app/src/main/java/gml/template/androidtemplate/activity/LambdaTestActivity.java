package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/13.
 */
public class LambdaTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lambdatest);
    }

    public void lambdaTest(View view){
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
    }

    public void getString(String a){

    }
}
