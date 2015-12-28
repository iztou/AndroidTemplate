package gml.template.androidtemplate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/24.
 */
public class FragmentTab3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragmenttab3, container, false);
        inflate.findViewById(R.id.tab3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return inflate;
    }

    /**
     * 进入下一个页面
     *
     * @param view
     */
    public void onNext(View view) {
    }

}
