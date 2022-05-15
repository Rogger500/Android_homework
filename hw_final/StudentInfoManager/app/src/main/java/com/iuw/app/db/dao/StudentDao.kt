package com.iuw.app.db.dao

import androidx.room.*
import com.iuw.app.db.bean.Student


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