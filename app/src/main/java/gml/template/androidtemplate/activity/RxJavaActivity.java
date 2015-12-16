package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import gml.template.androidtemplate.R;
import rx.Observable;
import rx.Subscriber;
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
        Toast.makeText(this,Build.VERSION.SDK_INT+" "+Build.VERSION.RELEASE+" "+"测试",Toast.LENGTH_LONG).show();
    }

    public void hello(String... names) {
        Observable.from(names).skip(1).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(String.valueOf(Build.VERSION.SDK_INT));
                subscriber.onNext(String.valueOf(Build.VERSION.RELEASE));
                subscriber.onCompleted();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                show.append(s);
            }
        });
    }
}
