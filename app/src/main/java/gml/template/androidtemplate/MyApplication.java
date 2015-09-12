package gml.template.androidtemplate;

import android.app.Application;
import android.graphics.Typeface;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Application
 * Created by 58 on 2015/9/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
    }

    /**
     * 初始化APP字体
     */
    private void initTypeface() {
        // Initialize typeface helper
        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), AppConstants.ASSETS_FONTS))
                .create();
        TypefaceHelper.init(typeface);
    }
}
