package gml.template.androidtemplate.activity;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import gml.template.androidtemplate.R;

/**
 * Created by gml on 2016/1/18.
 */
public class AnimationActivity extends BaseActivity {

    @Bind(R.id.imageExam)
    ImageView mImageExam;
    @Bind(R.id.info)
    TextView mInfo;
    @Bind(R.id.scaleButton)
    TextView mScaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    @OnClick(R.id.startBtn)
    void startBtnClick() {
        startPropertyAnimation();
    }

    @OnClick(R.id.getInfo)
    void getImageInfo() {
        int[] location = new int[2];
        int[] locationInWin = new int[2];
        mImageExam.getLocationOnScreen(location);
        mImageExam.getLocationInWindow(locationInWin);
        Rect rect = new Rect();
        Rect focusRect = new Rect();
        mImageExam.getDrawingRect(rect);
        mImageExam.getFocusedRect(focusRect);
        StringBuilder builder = new StringBuilder();
        builder.append("Left:").append(mImageExam.getLeft()).append("\n");
        builder.append("Top:").append(mImageExam.getTop()).append("\n");
        builder.append("Right:").append(mImageExam.getRight()).append("\n");
        builder.append("Bottom:").append(mImageExam.getBottom()).append("\n");
        builder.append("Width:").append(mImageExam.getWidth()).append("\n");
        builder.append("Height:").append(mImageExam.getHeight()).append("\n");
        builder.append("ButtonLeft:").append(mScaleButton.getLeft()).append("\n");
        builder.append("ButtonTop:").append(mScaleButton.getTop()).append("\n");
        builder.append("ButtonRight:").append(mScaleButton.getRight()).append("\n");
        builder.append("ButtonBottom:").append(mScaleButton.getBottom()).append("\n");
        builder.append("Width:").append(mImageExam.getWidth()).append("\n");
        builder.append("Height:").append(mImageExam.getHeight()).append("\n");
        builder.append("LocationOnScreen_Left:").append(location[0]).append("\n");
        builder.append("LocationOnScreen_Top:").append(location[1]).append("\n");
        builder.append("LocationInWindow_Left:").append(locationInWin[0]).append("\n");
        builder.append("LocationInWindow_Top:").append(locationInWin[1]).append("\n");
        builder.append("Rect_Left:").append(rect.left).append("\n");
        builder.append("Rect_Top:").append(rect.top).append("\n");
        builder.append("Rect_Right:").append(rect.right).append("\n");
        builder.append("Rect_Bottom:").append(rect.bottom).append("\n");
        builder.append("FocusedRect_Left:").append(focusRect.left).append("\n");
        builder.append("FocusedRect_Top:").append(focusRect.top).append("\n");
        builder.append("FocusedRect_Right:").append(focusRect.right).append("\n");
        builder.append("FocusedRect_Bottom:").append(focusRect.bottom).append("\n");
        mInfo.setText(builder.toString());
    }

    /**
     * 开始属性动画
     */
    private void startPropertyAnimation() {
        ObjectAnimator.ofFloat(mImageExam, "scaleX", 0.5f).setDuration(300).start();
        ViewWrapper wrapper = new ViewWrapper(mScaleButton);
//        ObjectAnimator.ofInt(wrapper, "width", mScaleButton.getWidth() / 2).setDuration(300).start();
        startPropertyAnimation(mScaleButton,mScaleButton.getWidth(),mScaleButton.getWidth()/2);
    }

    private void startPropertyAnimation(final View target, final int startValue, final int endValue){
        final IntEvaluator intEvaluator=new IntEvaluator();
        //将动画值限定在(1,100)之间
        ValueAnimator valueAnimator=ValueAnimator.ofInt(1,100);
        //动画持续时间
        valueAnimator.setDuration(300);
        //监听动画的执行
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //得到当前瞬时的动画值,在(1,100)之间
                Integer currentAnimatedValue=(Integer) valueAnimator.getAnimatedValue();
                //计算得到当前系数fraction
                float fraction=currentAnimatedValue/100f;
                System.out.println("currentAnimatedValue="+currentAnimatedValue+",fraction="+fraction);
                //评估出当前的宽度其设置
                target.getLayoutParams().width=intEvaluator.evaluate(fraction, startValue, endValue);
                target.requestLayout();
            }
        });
        //开始动画
        valueAnimator.start();
    }

    private class ViewWrapper {

        private View mTargetView;

        public ViewWrapper(View target) {
            mTargetView = target;
        }

        public int getWidth() {
            return mTargetView.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTargetView.getLayoutParams().width = width;
            mTargetView.requestLayout();
        }
    }
}
