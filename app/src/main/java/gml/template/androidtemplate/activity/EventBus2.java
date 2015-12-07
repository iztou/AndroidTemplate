package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/13.
 */
public class EventBus2 extends Activity {
    static String eventString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventtest2);
        EventBus.getDefault().register(this);
    }
    public void eventtest2(View view){
        EventBus.getDefault().post(new String[]{"111"});
        Toast.makeText(this, eventString + "EventBus2", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("EventBusTest", "EventBus2 start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("EventBusTest","EventBus2 onResume");
    }

    @Override
    protected void onDestroy() {
        Log.d("EventBusTest","EventBus2 destory");
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(String[] a){
//        eventString = a;
        Log.d("EventBusTest","EventBus2");
    }
}
