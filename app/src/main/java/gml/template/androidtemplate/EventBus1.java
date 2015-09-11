package gml.template.androidtemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

/**
 * Created by guomenglong on 15/3/13.
 */
public class EventBus1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventtest1);
    }
    public void eventtest1(View view){
//        startActivity(new Intent(this,EventBus2.class));
//        EventBus.getDefault().postSticky(new String[]{"测试EventBus2"});
        EventBus.getDefault().post(new String[]{"测试EventBus2"});
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().register(new EventBus2());
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().unregister(new EventBus2());
        super.onStop();
    }

    public void onEvent(String[] a){
        Toast.makeText(this,"EventBus1",Toast.LENGTH_SHORT).show();
    }
}
