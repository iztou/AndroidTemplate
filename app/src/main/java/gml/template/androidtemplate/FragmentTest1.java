package gml.template.androidtemplate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

/**
 * Created by guomenglong on 15/3/16.
 */
public class FragmentTest1 extends Fragment {
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View inflate = inflater.inflate(R.layout.singlefragment, container, false);
        textView = (TextView) inflate.findViewById(R.id.fragmentview1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("gotoPostFragmentTest2");
            }
        });
        return inflate;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void gotoFragmentTest2(View view){

    }

    public void onEvent(String a){
        Log.d("测试Fragment","Fragment Test1");
//        textView.setText(a);
    }
}
