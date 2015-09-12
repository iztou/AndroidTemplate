package gml.template.androidtemplate.encrypt;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.norbsoft.typefacehelper.TypefaceHelper;

import gml.template.androidtemplate.R;
import gml.template.androidtemplate.tools.util.EncryptUtil;

/**
 * 加密样例Activity
 */
public class Base64Activity extends Activity implements View.OnClickListener{

    private EditText inputText; //输入加密框
    private TextView afterEncrypt; //加密后结果
    private TextView afterDecrypt; //解密后结果
    private TextView start; //加密按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);
        TypefaceHelper.typeface(this);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView(){
        inputText = (EditText)findViewById(R.id.inputText);
        start = (TextView)findViewById(R.id.start);
        afterEncrypt = (TextView)findViewById(R.id.afterEncrypt);
        afterDecrypt = (TextView)findViewById(R.id.afterDecrypt);
        start.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base64, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                encryptInputText();
        }
    }

    /**
     * 加密输入的内容
     */
    private void encryptInputText(){
        Editable inputTextText = inputText.getText();
        if(TextUtils.isEmpty(inputTextText)){
            return;
        }
        String encryptMobile = EncryptUtil.encryptMobile(inputTextText.toString());
        afterEncrypt.setText(encryptMobile);
        afterDecrypt.setText(EncryptUtil.decryptMobile(encryptMobile));
    }
}
