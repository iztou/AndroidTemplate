package gml.template.androidtemplate.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/16.
 */
public class FragmentTest2 extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.listfragment, container, false);
        textView = (TextView) inflate.findViewById(R.id.fragmentview2);
        return inflate;
    }
}
