package gml.template.androidtemplate.activity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.Bind;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.adapter.RecyclerViewAdapter;
import gml.template.androidtemplate.view.DividerItemDecoration;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guomenglong on 16/5/12.
 */
public class RecycleViewRxActivity extends BaseActivity {

    @Bind(R.id.recycleView)
    RecyclerView mRecycleView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        fillData();

    }

    private void fillData() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecycleView.setAdapter(mRecyclerViewAdapter);
        Observable<List<PackageInfo>> listObservable = Observable.fromCallable(new Callable<List<PackageInfo>>() {
            @Override
            public List<PackageInfo> call() throws Exception {
                return getPackageManager().getInstalledPackages(0);
            }
        });
        listObservable.map(new Func1<List<PackageInfo>, List<RecycleViewActivity.PInfo>>() {
            @Override
            public List<RecycleViewActivity.PInfo> call(List<PackageInfo> packageInfos) {
                List<RecycleViewActivity.PInfo> pInfos = new ArrayList<RecycleViewActivity.PInfo>();
                for (PackageInfo info : packageInfos) {
                    RecycleViewActivity.PInfo pInfo = new RecycleViewActivity.PInfo();
                    pInfo.setAppname(info.applicationInfo.loadLabel(getPackageManager()).toString());
                    pInfo.setPname(info.packageName);
                    pInfo.setIcon(info.applicationInfo.loadIcon(getPackageManager()));
                    pInfos.add(pInfo);
                }
                return pInfos;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RecycleViewActivity.PInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<RecycleViewActivity.PInfo> pInfos) {
                        mRecyclerViewAdapter.setData((ArrayList<RecycleViewActivity.PInfo>) pInfos);
                    }
                });
    }
}
