package com.iuw.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iuw.app.db.bean.Lesson
import com.iuw.app.db.bean.Student
import com.iuw.app.db.dao.LessonDao
import com.iuw.app.db.dao.StudentDao


@Database(entities = [Student::class, Lesson::class], version = 1)
abstract class InfoManagerDB : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun  lessonDao(): LessonDao
}