package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.bean.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by guomenglong on 16/2/3.
 */
public class RetrofitOkHttpActivity extends BaseActivity{

    public static final String URL = "users/{user}/repos";
    @Bind(R.id.msg)
    TextView msg;
    @Bind(R.id.startBtn)
    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }

    @OnClick(R.id.startBtn)
    void startRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Response<List<Repo>> response) {
                List<Repo> body = response.body();
                msg.setText(body.get(0).getGit_url());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public interface GitHubService {
        @GET(URL)
        Call<List<Repo>> listRepos(@Path("user") String user);
    }
}
