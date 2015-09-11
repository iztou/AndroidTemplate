package gml.template.androidtemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //60000 倒计时60秒 间隔 1秒
//        MyCountTimer myCountTimer = new MyCountTimer(10000,1000);
//        myCountTimer.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Setting_ICON",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_test1:
                Toast.makeText(this,"Setting_TEST",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyCountTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            System.out.println(millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            System.out.println("倒计时结束...");
        }
    }

    /**
     * 滑动测试
     * @param v
     */
    public void startswipe(View v){
        startActivity(new Intent(this,SwipeLayoutActivity.class));
    }

    /**
     * 测试滑动过程中的缩小等
     * @param v
     */
    public void startswipeandother(View v){
        startActivity(new Intent(this,SwipeOtherActivity.class));
    }

    /**
     * 滑动测试
     * @param v
     */
    public void scrolltest(View v){
        startActivity(new Intent(this,MyScrollActicity.class));
    }
    /**
     * 滑动测试
     * @param v
     */
    public void scrollertest(View v){
        startActivity(new Intent(this,ScrollerActicity.class));
    }
    /**
     * Event测试
     * @param v
     */
    public void eventbusclick(View v){
        startActivity(new Intent(this,EventBus1.class));
    }
    /**
     * Fragment 测试
     * @param v
     */
    public void onFragmentTest(View v){
        startActivity(new Intent(this,FragmentTestActicity.class));
    }
    /**
     * RxJava测试
     * @param v
     */
    public void onRxJavaTest(View v){
        startActivity(new Intent(this,RxJavaActivity.class));
    }
    /**
     * Android生成代码测试
     * @param v
     */
    public void onFullscreenActivity(View v){
        startActivity(new Intent(this,FullscreenActivity.class));
    }
    /**
     * FragmentTab
     * @param v
     */
    public void onTabFragment(View v){
        startActivity(new Intent(this,FragmentTabActivity.class));
    }
    /**
     * 图片选择器测试
     * @param v
     */
    public void onPictureSelect(View v){
        startActivity(new Intent(this,PictureSelectorActivity.class));
    }
    /**
     * GridLayout测试
     * @param v
     */
    public void onGridLayoutTest(View v){
        startActivity(new Intent(this,TestGridLayoutActivity.class));
    }
}
