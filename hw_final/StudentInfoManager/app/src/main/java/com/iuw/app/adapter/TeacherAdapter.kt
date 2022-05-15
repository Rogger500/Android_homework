package com.iuw.app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.iuw.app.EditInfoActivity
import com.iuw.app.R
import com.iuw.app.db.bean.Lesson


class TeacherAdapter(private val data: List<Lesson>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sutdent, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTeacher = data.get(position)
        holder.apply {
            icon.setImageResource(
                if (itemTeacher.sex == "男") {
                    R.drawable.teacher_man
                } else {
                    R.drawable.teacher_woman
                }
            )
            name.text = "姓名：${itemTeacher.teacherName}"
            grade.text = "年级：${itemTeacher.grade}"
            professional.text = "课程：${itemTeacher.name}"
            //点击编辑按钮跳转到编辑页面
            edit.setOnClickListener {
                val arrayAnim = arrayOf(
                    androidx.core.util.Pair(icon as View, "iconTransition"),
                )
                //页面跳转方法 跳转到编辑页面并把点击的老师条目数据传到编辑页面
                EditInfoActivity.toMeEdit(
                    edit.context, ActivityOptionsCompat.makeSceneTransitionAnimation(
                        edit.context as Activity,
                        *arrayAnim
                    ).toBundle(), EditInfoActivity.TEACHER//类型为教师
                    ,lesson = itemTeacher//教师信息
                )
            }
        }


    }

}