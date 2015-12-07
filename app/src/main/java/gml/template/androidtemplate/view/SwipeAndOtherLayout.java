package gml.template.androidtemplate.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by guomenglong on 15/3/9.
 */
public class SwipeAndOtherLayout extends RelativeLayout {

    ViewDragHelper dragHelper;
    private int mdragRange;
    private int initPosition;
    public SwipeAndOtherLayout(Context context) {
        this(context, null);
    }

    public SwipeAndOtherLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeAndOtherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this,new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                if(child == getSwipeView())
                    return true;
                return false;
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return mdragRange;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if(child == getSwipeView()){

                }
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(releasedChild == getSwipeView()){
                    if(yvel > 0 )
                        dragHelper.settleCapturedViewAt(0,mdragRange);
//                        dragHelper.flingCapturedView(0,initPosition,0,mdragRange);
//                        dragHelper.smoothSlideViewTo(releasedChild,0,mdragRange);
                    else{
                        dragHelper.smoothSlideViewTo(releasedChild,0,initPosition);
                    }
                }
                invalidate();
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if(changedView == getSwipeView()){
                    View scaleView = getScaleView();
                    Log.d("测试拖动缩小","拖动缩小===>"+top);
                    float offset = (float) (top-initPosition) / (mdragRange-initPosition);
                    scaleView.offsetTopAndBottom(dy);
                    scaleView.setPivotX(scaleView.getWidth()/2);
                    scaleView.setPivotY(scaleView.getHeight());
                    scaleView.setScaleX(1-offset/2);
                    scaleView.setScaleY(1-offset/2);
                }
            }
        });

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public View getSwipeView(){
        return getChildAt(1);
    }

    public View getScaleView(){
        return getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mdragRange = getHeight() - getScaleView().getHeight();
        initPosition = getSwipeView().getTop();
    }
}
