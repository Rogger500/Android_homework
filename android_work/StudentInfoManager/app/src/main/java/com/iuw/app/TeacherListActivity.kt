package com.iuw.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iuw.app.adapter.TeacherAdapter
import com.iuw.app.db.DBUtil
import com.iuw.app.db.bean.Lesson
import com.iuw.app.event.TeacherListUpdateEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TeacherListActivity : AppCompatActivity() {
    private val data = ArrayList<Lesson>()
    private val adapter = TeacherAdapter(data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)
        toolBar.title = "所有教师"
        val allTeacher = DBUtil.lessonDao().loadAll()
        data.addAll(allTeacher)
        studentRecyclerView.adapter = adapter

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onTeacherListUpdate(event: TeacherListUpdateEvent) {
        data.clear()
        val allTeacher = DBUtil.lessonDao().loadAll()
        data.addAll(allTeacher)
        adapter.notifyDataSetChanged()
    }
}