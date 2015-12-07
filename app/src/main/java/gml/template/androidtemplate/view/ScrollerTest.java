package gml.template.androidtemplate.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by guomenglong on 15/3/12.
 */
public class ScrollerTest extends LinearLayout {
    private final Scroller scroller;

    public ScrollerTest(Context context) {
        this(context, null);
    }

    public ScrollerTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
//            postInvalidate();
        }
        super.computeScroll();
    }

    public void startScroll(){
        scroller.startScroll(0,0,0,100);
        invalidate();
    }
}
