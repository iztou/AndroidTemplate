package gml.template.androidtemplate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import gml.template.androidtemplate.R;
import gml.template.androidtemplate.activity.RecycleViewActivity;

/**
 * Created by gml on 2016/1/26.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<RecycleViewActivity.PInfo> pInfos = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<RecycleViewActivity.PInfo> data) {
        pInfos.clear();
        pInfos.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppListViewHolder viewHolder = new AppListViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycle_view_item, parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecycleViewActivity.PInfo pInfo = pInfos.get(position);
        AppListViewHolder listViewHolder = (AppListViewHolder) holder;
        listViewHolder.appIcon.setImageDrawable(pInfo.icon);
        listViewHolder.appName.setText(pInfo.appname);
    }

    @Override
    public int getItemCount() {
        return pInfos.size();
    }

    public static class AppListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.appIcon)
        public ImageView appIcon;
        @Bind(R.id.appName)
        public TextView appName;

        public AppListViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            ButterKnife.bind(this, itemView);
        }
    }
}
