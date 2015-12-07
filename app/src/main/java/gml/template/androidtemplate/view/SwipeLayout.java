package gml.template.androidtemplate.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 15/3/3.
 */
public class SwipeLayout extends RelativeLayout {

    protected ViewDragHelper dragHelper;

    private int dragEdges; //拖动方向
    private int layoutStyle; //布局位置

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SwipeLayout);
        dragEdges = array.getInt(R.styleable.SwipeLayout_drag, DragDirection.RIGHT.ordinal());
        layoutStyle = array.getInt(R.styleable.SwipeLayout_style, LayoutStyle.PULLOUT.ordinal());
        dragHelper = ViewDragHelper.create(this, 0.1f, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View view, int i) {
//                Log.d("拖拽", "捕获试图=======");
                if (view == getSurfaceView())
                    return true;
                return false;
            }


            @Override
            public int getViewHorizontalDragRange(View child) {
                if (child == getSurfaceView()) {
                    int width = getBottomView().getWidth();
//                    Log.d("拖拽", "拖拽范围" + -width);
                    return -width;
                }
                return 0;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == getSurfaceView()) {
//                    Log.d("拖拽", "开始拖拽:left=" + left + ",dx=" + dx);
                    //拖拽最大距离
                    int i = -getBottomView().getWidth();
                    int max = Math.max(left, i);
                    return Math.min(max, 0);
                }
                return left;
            }

//            @Override
//            public int clampViewPositionVertical(View child, int top, int dy) {
//                if (child == getSurfaceView()) {
//                    int i = getHeight() - child.getHeight();
//                    int min = Math.min(top, i);
//                    return Math.max(min, 0);
//                }
//                return top;
//            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                Log.d("拖拽", "试图位置:left=" + left + ",top=" + top + ",dx=" + dx + ",dy=" + dy);
                if (changedView == getSurfaceView()) {
//                    getBottomView().offsetLeftAndRight(dx);
//                    getBottomView().offsetTopAndBottom(dy);
                }
                invalidate();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == getSurfaceView()) {
                    int abs = Math.abs(releasedChild.getLeft());
                    int i = getBottomView().getWidth() / 2;
                    if (abs > i) {
                        dragHelper.smoothSlideViewTo(getSurfaceView(), -getBottomView().getWidth(), getPaddingTop());
                    } else {
                        dragHelper.smoothSlideViewTo(getSurfaceView(), getPaddingLeft(), getPaddingTop());
                    }
//                    dragHelper.flingCapturedView(0,0,0,getHeight()-getSurfaceView().getHeight());
                    Log.d("滑动结果", "滑动到:左-->" + getPaddingLeft() + ",上-->" + getPaddingTop() + "x轴的速度" + xvel + "y轴的速度" + yvel);
                }
                invalidate();
                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.duang);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
//                    mediaPlayer.prepare();
                mediaPlayer.start();
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
                Log.d("滑动结果", "从边缘滑动");
            }
        });
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
//        TypedArray a = context.(attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("拖拽", "intercept被调用");
//        if(getSurfaceView().onTouchEvent(ev))
//            return false;
        dragHelper.shouldInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getSurfaceView().setPressed(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getSurfaceView().setPressed(false);
        }
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("child View must be two!");
        }
        View surfaceView = getSurfaceView();
        Rect rect = computeSurfaceRect();
        View bottomView = getBottomView();
        int measuredHeight0 = surfaceView.getMeasuredHeight();
        int measuredWidth0 = surfaceView.getMeasuredWidth();
        int measuredHeight1 = bottomView.getMeasuredHeight();
        int measuredWidth1 = bottomView.getMeasuredWidth();
        surfaceView.layout(rect.left, rect.top, rect.right, rect.bottom);
        bottomView.layout(measuredWidth0 - measuredWidth1, 0, measuredWidth0, measuredHeight0);
        bringChildToFront(surfaceView);
    }

    /**
     * 拉出布局 计算被拉出的组件的位置
     */
    private Rect computeSurfaceRect() {
        View surface = getSurfaceView();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int measuredWidth = surface.getMeasuredWidth();
        int measuredHeight = surface.getMeasuredHeight();
        Rect rect = new Rect(paddingLeft, paddingTop, paddingLeft + measuredWidth, paddingTop + measuredHeight);
        return rect;
    }

    private Rect computeBottomRect(int dragEdges) {
        View surface = getSurfaceView();
        View bottomView = getBottomView();
        Rect rect = null;
        if (dragEdges == DragDirection.LEFT.ordinal()) {

        } else if (dragEdges == DragDirection.TOP.ordinal()) {

        } else if (dragEdges == DragDirection.RIGHT.ordinal()) {

        } else {

        }
        return rect;
    }

    /**
     * 覆盖布局
     */
    private Rect layoutDown() {

        return null;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * 获取上层View
     *
     * @return
     */
    private View getSurfaceView() {
        return getChildAt(getChildCount() - 1);
    }

    /**
     * 获取下层View
     *
     * @return
     */
    private View getBottomView() {
        return getChildAt(0);
    }

    public enum DragDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }

    public enum LayoutStyle {
        PULLOUT, LAYDOWN
    }
}
