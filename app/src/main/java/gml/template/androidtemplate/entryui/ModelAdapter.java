package gml.template.androidtemplate.entryui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gml.template.androidtemplate.R;

/**
 * 入口界面功能模块Adapter
 * Created by 58 on 2015/9/11.
 */
public class ModelAdapter extends BaseAdapter {
    private ModelItems[] modelItemses = new ModelItems[0];
    private Context context;

    public ModelAdapter(Context context) {
        this.context = context;
    }

    public void setModelItemses(ModelItems[] modelItemses) {
        this.modelItemses = modelItemses;
    }

    @Override
    public int getCount() {
        return modelItemses.length;
    }

    @Override
    public Object getItem(int position) {
        return modelItemses[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            viewHolder.item = convertView = LayoutInflater.from(context).inflate(R.layout.activity_main_item, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(modelItemses[position].getTitle());
        return convertView;
    }

    public class ViewHolder {
        public View item;
        public TextView title;
    }
}
