package gml.template.androidtemplate;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by guomenglong on 15/3/25.
 * 定制ViewPager
 * 不处理滑动动作
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    //    @Override
//    public void scrollTo(int x, int y) {
//        if(isCanScroll)
//            super.scrollTo(x, y);
//    }
}
