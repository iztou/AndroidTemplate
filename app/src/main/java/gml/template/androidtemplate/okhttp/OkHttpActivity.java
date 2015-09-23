package gml.template.androidtemplate.okhttp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DownloadRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.okhttp.Request;

import gml.template.androidtemplate.MyApplication;
import gml.template.androidtemplate.R;

/**
 * Volley + OKHttp
 */
public class OkHttpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner mUrlList; //URL 地址
    private TextView content; //内容
    private View parentView; //容器视图
    private String url;
    private RequestQueue requestQueue;
    private ProgressBar progress; //progress
    private Button download; //下载按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        requestQueue = MyApplication.getRequestQueue();
        initView();
    }

    private void initView() {
        parentView = findViewById(R.id.parent_layout);
        progress = (ProgressBar) findViewById(R.id.progress);
        download = (Button) findViewById(R.id.download);
        mUrlList = (Spinner) findViewById(R.id.urlList);
        content = (TextView) findViewById(R.id.content);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_url, android.R.layout.simple_spinner_dropdown_item);
        mUrlList.setAdapter(adapter);
        mUrlList.setOnItemSelectedListener(this);
        download.setOnClickListener(this);
        findViewById(R.id.fetch).setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        url = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void run(String url) {
        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                content.setText(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(content, "加载网页错误", Snackbar.LENGTH_LONG).setAction("Retry", OkHttpActivity.this).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download:
                download();
                break;
            case R.id.urlList:
                run(url);
                break;
        }
    }

    /**
     * 下载文件
     */
    private void download() {
        final DownloadRequest downloadRequest = new DownloadRequest("http://www.ycpai.com/statics/android/app-ycpai-release_YCPAI_sign.apk", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Snackbar.make(content,"文件下载完成",Snackbar.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(content,"文件下载失败",Snackbar.LENGTH_LONG).show();
            }
        });
        downloadRequest.setOnProgressListener(new Response.ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                if (progress.getMax() == 100)
                    progress.setMax((int) totalSize);
                progress.setProgress((int) transferredBytes);
            }
        });
        requestQueue.add(downloadRequest);
    }
}
