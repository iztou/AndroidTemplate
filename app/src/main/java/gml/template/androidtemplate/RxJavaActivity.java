package gml.template.androidtemplate;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by guomenglong on 15/3/19.
 */
public class RxJavaActivity extends Activity {
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjavatest);
        show = (TextView) findViewById(R.id.showRxMsg);
    }

    /**
     * 开始测试RxJava
     * @param view
     */
    public void begin(View view){
        hello(Build.VERSION.SDK_INT+"",Build.VERSION.RELEASE,"测试");
//        show.setText(Build.VERSION.SDK_INT+" "+Build.VERSION.RELEASE+" "+"测试");
        Toast.makeText(this,Build.VERSION.SDK_INT+" "+Build.VERSION.RELEASE+" "+"测试",Toast.LENGTH_LONG).show();
    }

    public void hello(String... names) {
        Observable.from(names).skip(1).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
//                show.setText(show.getEditableText()+s);
//                show.setText(s);
                show.append(s);
            }

        });
    }
}
