package com.iuw.app.db.dao

import androidx.room.*
import com.iuw.app.db.bean.Student

/**
 * @author sx  2021/12/20 15:55
 * @email  1668626317@qq.com
 */

@Dao
interface StudentDao {
    @Query("select * from student")
    fun loadAllStudent(): List<Student>

    @Insert(entity = Student::class)
    fun insertStudent(vararg student: Student)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg student: Student)

    @Delete
    fun del(student: Student)
}