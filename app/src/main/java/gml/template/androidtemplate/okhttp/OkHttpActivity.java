package gml.template.androidtemplate.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import gml.template.androidtemplate.R;

public class OkHttpActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner mUrlList; //URL 地址
    OkHttpClient client = new OkHttpClient();
    private TextView content; //内容
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();
    }

    private void initView() {
        mUrlList = (Spinner) findViewById(R.id.urlList);
        content = (TextView) findViewById(R.id.content);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_url, android.R.layout.simple_spinner_dropdown_item);
        mUrlList.setAdapter(adapter);
        mUrlList.setOnItemSelectedListener(this);
        findViewById(R.id.fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    run(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        url = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                content.setText(response.body().string());
            }
        });
    }
}
