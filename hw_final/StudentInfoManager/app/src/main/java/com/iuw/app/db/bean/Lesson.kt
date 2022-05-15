package com.iuw.app.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Lesson(
    var name: String,//课程名
    var grade: String,//教授年级
    var teacherName: String,//教师姓名
    var age: Int,//教师年龄
    var sex: String,//教师性别
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0//id
) : Serializable {
    override fun toString(): String {
        return "$name-$teacherName"
    }
}