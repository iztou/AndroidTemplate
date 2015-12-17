package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import gml.template.androidtemplate.R;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guomenglong on 15/3/19.
 */
public class RxJavaActivity extends Activity {
    private TextView show;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjavatest);
        show = (TextView) findViewById(R.id.showRxMsg);
        icon = (ImageView) findViewById(R.id.icon);
    }

    /**
     * 开始测试RxJava
     * @param view
     */
    public void begin(View view){
        hello(Build.VERSION.SDK_INT+"",Build.VERSION.RELEASE,"测试");
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
                subscriber.onNext(" SDK="+Build.VERSION.SDK_INT);
                subscriber.onNext(" Release="+Build.VERSION.RELEASE);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                show.append(s);
            }
        });
        Observable.just(R.drawable.ic_launcher).map(new Func1<Integer, Bitmap>() {
            @Override
            public Bitmap call(Integer resId) {
                return BitmapFactory.decodeResource(getResources(),resId);
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                icon.setImageBitmap(bitmap);
            }
        });
    }
}
