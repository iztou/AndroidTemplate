package gml.template.androidtemplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.norbsoft.typefacehelper.TypefaceHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import gml.template.androidtemplate.R;

/**
 * 入口界面功能模块Adapter
 * Created by gml on 2015/9/11.
 */
public class ModelAdapter extends BaseAdapter {
    private ModelItems[] modelItems = new ModelItems[0];
    private Context context;

    public ModelAdapter(Context context) {
        this.context = context;
    }

    public void setModelItems(ModelItems[] modelItems) {
        this.modelItems = modelItems;
    }

    @Override
    public int getCount() {
        return modelItems.length;
    }

    @Override
    public Object getItem(int position) {
        return modelItems[position];
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
            convertView = viewHolder.item;
            convertView.setTag(viewHolder);
            TypefaceHelper.typeface(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(modelItems[position].getTitle());
        return convertView;
    }

    public class ViewHolder {
        public View item;
        @Bind(R.id.title)
        public TextView title;

        public ViewHolder(){
            item = LayoutInflater.from(context).inflate(R.layout.activity_main_item, null);
            ButterKnife.bind(this,item);
        }
    }
}
