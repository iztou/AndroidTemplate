package gml.template.androidtemplate.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.service.LocalService;

/**
 * Created by guomenglong on 16/2/3.
 */
public class ServiceActivity extends BaseActivity {
    @Bind(R.id.bindBtn)
    Button bindBtn;

    private ServiceConnection serviceConnection;
    private boolean bindFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initConnection();
    }

    /**
     * 初始化连接
     */
    private void initConnection(){
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LocalService.SimpleBinder simpleBinder = (LocalService.SimpleBinder) service;
                int add = simpleBinder.add(3, 4);
                Log.v("3 + 4 = ",String.valueOf(add));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.v("Service ","Unbind Service"+name);
            }
        };
    }

    @OnClick(R.id.bindBtn)
    void bindService(){
        if(!bindFlag){
            bindFlag = true;
            Intent intent = new Intent(this, LocalService.class);
            bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        }else{
            bindFlag = false;
            unbindService(serviceConnection);
        }
    }
}
