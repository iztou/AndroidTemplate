package gml.template.androidtemplate.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by guomenglong on 16/2/3.
 */
public class LocalService extends Service {

    SimpleBinder simpleBinder;

    @Override
    public void onCreate() {
        simpleBinder = new SimpleBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return simpleBinder;
    }

    public class SimpleBinder extends Binder{

        public int add(int a,int b){
            return a + b;
        }
    }
}
