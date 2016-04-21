package gml.template.androidtemplate.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import gml.template.androidtemplate.R
import gml.template.androidtemplate.adapter.RecyclerViewAdapter
import gml.template.androidtemplate.view.DividerItemDecoration
import java.util.*

class RecycleViewActivity : BaseActivity() {

    internal var recyclerView: RecyclerView? = null
    private var viewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        fillData()
    }

    private fun fillData() {
        recyclerView = findViewById(R.id.recycleView) as RecyclerView?
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST))
        viewAdapter = RecyclerViewAdapter(this)
        recyclerView?.adapter = viewAdapter
        viewAdapter!!.setData(getInstalledApps(true))
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                Log.d("测试RecyclerView滚动距离", "x滚动距离=>$dx,y滚动距离=>$dy")
            }
        })
    }


    private fun getInstalledApps(getSysPackages: Boolean): ArrayList<PInfo> {
        val res = ArrayList<PInfo>()
        val packs = applicationContext.packageManager.getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs[i]
            if (!getSysPackages && p.versionName == null) {
                continue
            }
            val newInfo = PInfo()
            newInfo.appname = p.applicationInfo.loadLabel(applicationContext.packageManager).toString()
            newInfo.pname = p.packageName
            newInfo.versionName = p.versionName
            newInfo.versionCode = p.versionCode
            newInfo.icon = p.applicationInfo.loadIcon(applicationContext.packageManager)
            res.add(newInfo)
        }
        return res
    }

    class PInfo {
        var appname = ""
        var pname = ""
        var versionName = ""
        var versionCode = 0
        var icon: Drawable? = null
    }
}
