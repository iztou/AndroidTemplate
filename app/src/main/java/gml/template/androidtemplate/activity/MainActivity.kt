package gml.template.androidtemplate.activity

import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import gml.template.androidtemplate.R
import gml.template.androidtemplate.adapter.ModelAdapter
import gml.template.androidtemplate.adapter.ModelItems
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainActivity : BaseActivity() {

    internal var entryList: ListView ? = null //入口界面ListView
    internal var pullToRefresh: PtrClassicFrameLayout ? = null
    internal var mDrawerLayout: DrawerLayout ? = null

    private var mDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        entryList = findViewById(R.id.entryList) as ListView
        pullToRefresh = findViewById(R.id.mainLayout) as PtrClassicFrameLayout
        mDrawerLayout = findViewById(R.id.drawerLayout) as DrawerLayout
        pullToRefresh!!.isPullToRefresh = true
        initDrawerToggle()
        fillData()
        initListener()
    }

    private fun initDrawerToggle() {
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
            }
        }
        mDrawerToggle!!.syncState()
        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
    }

    private fun initListener() {
        toolbar.setOnMenuItemClickListener { item ->
            var msg = ""
            when (item.itemId) {
                R.id.action_test1 -> msg += "Click test1"
                R.id.action_test2 -> msg += "Click test2"
                R.id.action_settings -> msg += "Click setting"
            }

            if (msg != "") {
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
            true
        }
        pullToRefresh!!.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                Observable.create(Observable.OnSubscribe<kotlin.Int> { subscriber ->
                    var times = 5
                    var currentTime = System.currentTimeMillis()
                    while (true) {
                        val dyTime = System.currentTimeMillis()
                        if (((dyTime - currentTime) / 1000).equals(1)) {
                            subscriber.onNext(times--)
                            currentTime = dyTime
                            if (times == -1) {
                                subscriber.onCompleted()
                                break
                            }
                        }
                    }
                }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { integer ->
                    if (integer === 0) {
                        (pullToRefresh as PtrClassicFrameLayout).refreshComplete()
                    }
                }
            }
        })
        entryList?.setOnItemClickListener { adapterView, view, i, l ->
            val modelItems = entryList!!.adapter.getItem(i) as ModelItems
            val intent = Intent(this, modelItems.activity)
            startActivity(intent)
        }
    }

    /**
     * 填充数据
     */
    private fun fillData() {
        val modelAdapter = ModelAdapter(this)
        val itemsArrayList = ArrayList<ModelItems>()
        itemsArrayList.add(ModelItems.createNewInstance("向左滑动删除", SwipeLayoutActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("滑动固定", MyScrollActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("测试滑动过程中并缩小", SwipeOtherActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("RxJava测试", RxJavaActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("Android Studio生成Activity测试", FullscreenActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("图片选择器测试", PictureSelectorActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("自定义图片选择器示例", PhotoSelectedActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("Base64加解密示例", Base64Activity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("Volley+OkHttp示例", OkHttpActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("Retrofit+OkHttp示例", RetrofitOkHttpActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("ViewPager指示器示例", PageSlidingTabActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("GreenDao示例", GreenDaoActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("动画示例", AnimationActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("自定义形状示例", CustomizeShapeActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("Fresco示例", FrescoActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("RecyclerViewActivity示例", RecycleViewActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("ServiceActivity示例", ServiceActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("签字示例", SignatureActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("RxJava RecycleView示例", RecycleViewRxActivity::class.java))
        itemsArrayList.add(ModelItems.createNewInstance("IText 弹框示例", ITextActivity::class.java))
        modelAdapter.setModelItems(itemsArrayList.toArray<ModelItems>(arrayOfNulls<ModelItems>(0)))
        entryList!!.adapter = modelAdapter
    }

    class itemClickedListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            throw UnsupportedOperationException()
        }

    }
}
