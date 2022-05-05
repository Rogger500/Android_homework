package com.iuw.app.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(
    var name: String,//学生姓名
    var sex: String,//性别
    var age: Int,//年龄
    var grade: String,//年级
    var lesson: String,//学习课程名
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0//id
):Serializable {

}
