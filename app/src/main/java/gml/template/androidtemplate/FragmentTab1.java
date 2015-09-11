package gml.template.androidtemplate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;

/**
 * Created by guomenglong on 15/3/24.
 */
public class FragmentTab1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View inflate = inflater.inflate(R.layout.fragmenttab1, container,false);
        inflate.findViewById(R.id.tab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(FragmentTabActivity.NextPage.SECOND);
            }
        });
        return inflate;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void onEvent(FragmentTabActivity.NextPage a){
        Log.d("点击下一页","第一页->第二页");
    }
}
