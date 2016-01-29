package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.norbsoft.typefacehelper.TypefaceHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import gml.template.androidtemplate.R;

/**
 * Created by gml on 2015/12/25.
 */
public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.rootContent)
    protected FrameLayout rootContent;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceHelper.typeface(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        Fresco.initialize(this);
        rootContent = (FrameLayout) findViewById(R.id.rootContent);
        LayoutInflater.from(this).inflate(layoutResID, rootContent);
        ButterKnife.bind(this);
        initToolBar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_base);
        Fresco.initialize(this);
        rootContent = (FrameLayout) findViewById(R.id.rootContent);
        rootContent.addView(view);
        ButterKnife.bind(this);
        initToolBar();
    }


    /**
     * 初始化ToolBar
     */
    protected void initToolBar() {
        toolbar.setTitle("Android Template");
        toolbar.setTitleTextColor(0xffffffff);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
