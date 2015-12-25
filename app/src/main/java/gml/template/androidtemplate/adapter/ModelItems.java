package gml.template.androidtemplate.adapter;

/**
 * ListView 列表item数据对象
 * Created by 58 on 2015/9/11.
 */
public class ModelItems {
    private String title; //功能标题
    private Class<?> activity;//功能实现Activity

    private ModelItems() {
    }

    public static ModelItems createNewInstance(String title, Class<?> activity) {
        ModelItems modelItems = new ModelItems();
        modelItems.title = title;
        modelItems.activity = activity;
        return modelItems;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getActivity() {
        return activity;
    }
}
