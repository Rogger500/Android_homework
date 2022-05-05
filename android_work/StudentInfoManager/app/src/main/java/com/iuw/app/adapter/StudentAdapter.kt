package com.iuw.app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.iuw.app.EditInfoActivity
import com.iuw.app.R
import com.iuw.app.db.bean.Student

/**
 * 学生列表的Adapter用于将数据绑定到列表上
 */
class StudentAdapter(private val data: List<Student>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * 创建itemView和ViewHolder
     * ViewHolder itemView持有者，将itemView传入进去，在里面对item中的控件进行赋值(findViewById())
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sutdent, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * 绑定列表item信息
     * holder之前创建的ViewHolder ,position item的下标
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemStudent:Student = data[position]
        holder.apply {
            icon.setImageResource(
                if (itemStudent.sex == "男") {
                    R.drawable.man
                } else {
                    R.drawable.woman
                }
            )
            name.text = "姓名：${itemStudent.name}"
            grade.text = "年级：${itemStudent.grade}"
            professional.text = "课程：${itemStudent.lesson}"
            //点击编辑按钮跳转到编辑页面
            edit.setOnClickListener {
                val arrayAnim = arrayOf(
                    androidx.core.util.Pair(icon as View, "iconTransition"),
                )
                //页面跳转方法 跳转到编辑页面并把点击的学生条目数据传到编辑页面
                EditInfoActivity.toMeEdit(
                    edit.context, ActivityOptionsCompat.makeSceneTransitionAnimation(
                        edit.context as Activity,
                        *arrayAnim
                    ).toBundle(), EditInfoActivity.STUDENT//信息类型为学生
                    , itemStudent//点击的学生信息
                )
            }
        }


    }

}