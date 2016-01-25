package gml.template.androidtemplate.activity;

import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import gml.template.androidtemplate.R;

/**
 * Created by gml on 2016/1/25.
 * Fresco 演示Activity
 */
public class FrescoActivity extends BaseActivity {

    @Bind(R.id.imageView)
    SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        simpleDraweeView.setImageURI(Uri.parse("http://www.fresco-cn.org/static/fresco-logo.png"));
    }
}
