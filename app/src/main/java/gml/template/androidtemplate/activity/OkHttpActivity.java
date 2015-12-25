package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DownloadRequest;
import com.android.volley.toolbox.StringRequest;

import butterknife.Bind;
import butterknife.OnClick;
import gml.template.androidtemplate.MyApplication;
import gml.template.androidtemplate.R;

/**
 * Volley + OKHttp
 */
public class OkHttpActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.urlList)
    protected Spinner mUrlList; //URL 地址
    @Bind(R.id.content)
    protected TextView content; //内容
    @Bind(R.id.parent_layout)
    protected View parentView; //容器视图
    @Bind(R.id.progress)
    protected ProgressBar progress; //progress
    @Bind(R.id.download)
    protected Button download; //下载按钮

    private RequestQueue requestQueue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        requestQueue = MyApplication.getRequestQueue();
        initView();
    }

    private void initView() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_url, android.R.layout.simple_spinner_dropdown_item);
        mUrlList.setAdapter(adapter);
        mUrlList.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        url = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.fetch})
    public void run() {
        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                content.setText(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(content, "加载网页错误", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(OkHttpActivity.this, "返回", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * 下载文件
     */
    @OnClick(R.id.download)
    protected void download() {
        final DownloadRequest downloadRequest = new DownloadRequest("http://www.ycpai.com/statics/android/app-ycpai-release_YCPAI_sign.apk", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Snackbar.make(content, "文件下载完成", Snackbar.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(content, "文件下载失败", Snackbar.LENGTH_LONG).show();
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
