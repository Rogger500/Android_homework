package com.iuw.app.db

import androidx.room.Room
import com.iuw.app.App

/**
 *Sqlite数据库
 */
object DBUtil {
    private val InfoManagerDB =
        Room.databaseBuilder(App.context, InfoManagerDB::class.java, "info_manager").allowMainThreadQueries().build()

    fun studentDao() = InfoManagerDB.studentDao()
    fun lessonDao() = InfoManagerDB.lessonDao()

}