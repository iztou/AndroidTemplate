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
public class FragmentTab2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragmenttab2, container,false);
        inflate.findViewById(R.id.tab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(FragmentTabActivity.NextPage.THIRD);
            }
        });
        return inflate;
    }

    public void onEvent(FragmentTabActivity.NextPage a){
        Log.d("点击下一页", "第一页->第二页");
    }
}
