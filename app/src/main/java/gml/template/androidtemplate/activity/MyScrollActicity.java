package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Scroller;

import gml.template.androidtemplate.view.MyScrollView;
import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/11.
 */
public class MyScrollActicity extends Activity implements MyScrollView.OnScrollListener {
    private View fixed;
    private MyScrollView scroll;
    private int fixedposition = -1;
    private Scroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolltest);

        scroll = (MyScrollView) findViewById(R.id.scrollView);
        fixed = findViewById(R.id.fixed);
        View flying = findViewById(R.id.flying);
        scroll.setOnScrollListener(this);
//当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(scroll.getScrollY());
            }
        });
        scroller = new Scroller(this);
    }


    @Override
    public void onScroll(int scrollY) {
        if(fixedposition == -1)
            fixedposition = fixed.getTop();
//        View childAt = scroll.getChildAt(scroll.getChildCount() - 1);
//        if(childAt != fixed)
//            fixed.bringToFront();
        System.out.println("Y轴滑动距离---->" + scrollY + "top的位置是:" + fixed.getTop());
        int mBuyLayout2ParentTop = Math.max(scrollY, fixedposition);
        fixed.layout(0, mBuyLayout2ParentTop, fixed.getWidth(), mBuyLayout2ParentTop + fixed.getHeight());
    }

    /**
     * 测试Scroller例子
     * @param view
     */
    public void scrollTest(View view){
        scroller.forceFinished(true);
        scroller.startScroll(0,0,0,100);
        scroll.invalidate();
    }
}
