package com.iuw.app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.iuw.app.adapter.StudentAdapter
import com.iuw.app.db.DBUtil
import com.iuw.app.db.bean.Student
import com.iuw.app.event.ChangeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
    private val studentDate = ArrayList<Student>()
    private val adapter = StudentAdapter(studentDate)

    /**
     * activity生命周期
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置页面内容
        setContentView(R.layout.activity_main)
        //注册 用于接受页面更新事件
        EventBus.getDefault().register(this)
        //查询数据库中的所有学生信息
        val allStudent = DBUtil.studentDao().loadAllStudent()
        studentDate.addAll(allStudent)
        studentRecyclerView.adapter = adapter
        //添加菜单到toolBar中
        toolBar.inflateMenu(R.menu.menu_student )
        //为toolbar设置item的点击事件
        toolBar.setOnMenuItemClickListener(this)

    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                if (DBUtil.lessonDao().loadAll().isEmpty()) {
                    Toast.makeText(this, "请先添加教师信息", Toast.LENGTH_SHORT).show()
                    return false
                }
                EditInfoActivity.toMeInsert(this, EditInfoActivity.STUDENT)
            }
            R.id.viewTeachers -> {
                startActivity(Intent(this, TeacherListActivity::class.java))
            }
            else -> {
                EditInfoActivity.toMeInsert(this, EditInfoActivity.TEACHER)
            }
        }
        return false
    }

    //订阅发布的内容(当EventBus.getDefault().post(ChangeEvent())被调用时候，
    // 加了Subscribe注解并且参数类型为ChangeEvent的方法就会被调用)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onChangeEvent(event: ChangeEvent) {
        studentDate.clear()
        val allStudent = DBUtil.studentDao().loadAllStudent()
        studentDate.addAll(allStudent)
        adapter.notifyDataSetChanged()
    }

}