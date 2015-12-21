package gml.template.androidtemplate.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gml.greendao.DaoMaster;
import gml.greendao.DaoSession;
import gml.greendao.User;
import gml.greendao.UserDao;
import gml.template.androidtemplate.R;

public class GreenDaoActivity extends Activity {

    @Bind(R.id.greenText)
    TextView greenText;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "gml-db", null);
        db = devOpenHelper.getWritableDatabase();
    }

    @OnClick(R.id.greenText)
    void insertValue(){
        User user = new User();
        user.setUsername("gml测试GreenDao");
        user.setPassword("mima123");
        user.setRemark("gml备注验证");
        user.setCreatetime(System.currentTimeMillis());
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        List<User> list = userDao.queryBuilder().build().list();
        if(list.isEmpty()){
            userDao.insert(user);
        }else{
            greenText.setText(list.get(0).getRemark());
        }
    }
}
