package com.gml.photoselector.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.GridView;

import com.gml.photoselector.R;

/**
 * Created by gml on 2015/12/30.
 * 图片选择GridView
 */
public class PhotoSelectGridView extends GridView {

    public static final int COLUMN_H = 3;
    public static final int COLUMN_V = 6;
    public static int mDivide;
    private int mItemWidth = -1; //item宽

    public PhotoSelectGridView(Context context) {
        this(context, null);
    }

    public PhotoSelectGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoSelectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化基础数据
     *
     * @param context
     */
    protected void init(Context context) {
        mDivide = context.getResources().getDimensionPixelOffset(R.dimen.space_size);
        registListener();
    }

    protected void registListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
                @Override
                public void onDraw() {
                    int columnCount = getWidth() > getHeight() ? COLUMN_V : COLUMN_H;
                    int width = Math.max(getWidth(),getHeight());
                    mItemWidth = (width - mDivide * (columnCount - 1)) / columnCount;
                }
            });
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mItemWidth == -1)
            return;
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View item = getChildAt(i);
                AbsListView.LayoutParams layoutParams = (LayoutParams) item.getLayoutParams();
                layoutParams.height = mItemWidth;
                layoutParams.width = mItemWidth;
            }
        }
    }
}
