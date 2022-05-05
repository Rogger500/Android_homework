package com.iuw.app.db.dao

import androidx.room.*
import com.iuw.app.db.bean.Lesson

/**
 * @author sx  2021/12/22 10:41
 * @email  1668626317@qq.com
 */
@Dao
interface LessonDao {
    @Query("select * from Lesson")
    fun loadAll(): List<Lesson>

    @Update
    fun update(lesson: Lesson)

    @Delete
    fun del(lesson: Lesson)

    @Insert(entity = Lesson::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg lesson: Lesson)

    @Query("select * from Lesson where name = :lessonName")
    fun queryByLessonName(lessonName: String): List<Lesson>
}