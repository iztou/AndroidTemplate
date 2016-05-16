package gml.template.androidtemplate.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

/**
 * Created by guomenglong on 16/5/16.
 */
public class AlertDialog extends Dialog {
    public AlertDialog(Context context) {
        this(context,0);
    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public void init(){
        getWindow().setGravity(Gravity.CENTER);
    }
}
