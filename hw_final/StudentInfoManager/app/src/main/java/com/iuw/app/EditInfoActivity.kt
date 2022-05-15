package com.iuw.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iuw.app.db.DBUtil
import com.iuw.app.db.bean.Lesson
import com.iuw.app.db.bean.Student
import com.iuw.app.event.ChangeEvent
import com.iuw.app.event.TeacherListUpdateEvent
import kotlinx.android.synthetic.main.activity_edit_info.*
import org.greenrobot.eventbus.EventBus

/**
 * 学生&老师 信息编辑公用页面
 * 根据type变量区分学生和老师 1学生 2老师
 */
class EditInfoActivity : AppCompatActivity() {

    companion object {
        const val STUDENT = 1
        const val TEACHER = 2

        /**
         * 插入数据跳转方法
         * @param context 哪个页面跳转过来的
         * @param type 类型 1学生 2老师
         */
        fun toMeInsert(context: Context, type: Int) {
            val intent = Intent(context, EditInfoActivity::class.java)
                .putExtra("type", type)
                .putExtra("isInsert", true)
            context.startActivity(intent)
        }
        /**
         * 插入数据跳转方法
         * @param context 哪个页面跳转过来的
         * @param type 类型 1学生 2老师
         * @param student 学生条目信息
         * @param lesson 老师条目信息
         */
        fun toMeEdit(
            context: Context,
            bundle: Bundle?,
            type: Int,
            student: Student? = null,
            lesson: Lesson? = null
        ) {
            val intent = Intent(context, EditInfoActivity::class.java)
                .putExtra("type", type)
                .putExtra("isInsert", false)
            if (type == STUDENT) {
                intent.putExtra("student", student)
            } else {
                intent.putExtra("lesson", lesson)
            }
            context.startActivity(intent, bundle)
        }
    }

    private var type = STUDENT //页面类型 学生-老师
    private var id = 0L //数据的id用于修改和删除（room框架中修改和删除数据库中的数据时用ID来判断所以记录上一个页面传过来的数据ID）
    private var sex = "男"//性别
    private var age = 0
    private var name = ""
    private var lessonName = ""
    private var grade = "大一"
    private val lessons = ArrayList<Lesson>()
    private val gradeData = arrayOf("大一", "大二", "大三", "大四")
    private val sexData = arrayOf("男", "女")
    private var isInsert = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)
        isInsert = intent.getBooleanExtra("isInsert", false)
        //初始化View
        initView()
        initData()
        initListener()

    }

    private fun initListener() {
        sexSpinner.setOnItemSelectedListener { _, _, _, item ->
            sex = item as String
            setIcon()
        }
        lessonSpinner.setOnItemSelectedListener { _, _, _, item ->
            lessonName = (item as Lesson).toString()
        }
        gradeSpinner.setOnItemSelectedListener { _, _, _, item ->
            grade = item.toString()
        }

        save.setOnClickListener {
            if (type == TEACHER) {
                //一些信息合法化的判断开始
                name = nameEdit.text.toString().trim()
                if (name.isEmpty()) {
                    nameEdit.error = "请输入教师姓名"
                    return@setOnClickListener
                }
                val ageString = ageEdit.text.toString().trim()
                if (ageString.isEmpty()) {
                    ageEdit.error = "请输入教师年龄"
                    return@setOnClickListener
                }
                age = ageString.toInt()
                lessonName = lessonEdit.text.toString().trim()
                if (lessonName.isEmpty()) {
                    lessonEdit.error = "请输入教师所授课程"
                    return@setOnClickListener
                }
                //一些信息合法化的判断结束
                //
                val teacher = Lesson(lessonName, grade, name, age, sex)
                //如果是插入调用插入
                if (isInsert) {
                    DBUtil.lessonDao().insert(teacher)
                    //通知上个页面更新
                    EventBus.getDefault().post(TeacherListUpdateEvent())
                }else{
                    //编辑和删除需要根据id来
                    teacher.id = id
                    DBUtil.lessonDao().update(teacher)
                    //通知上个页面更新
                    EventBus.getDefault().post(TeacherListUpdateEvent())
                }
                finish()
                Toast.makeText(this, "教师信息编辑成功", Toast.LENGTH_SHORT).show()
            } else {
                name = nameEdit.text.toString().trim()
                if (name.isEmpty()) {
                    nameEdit.error = "请输入学生姓名"
                    return@setOnClickListener
                }
                val ageString = ageEdit.text.toString().trim()
                if (ageString.isEmpty()) {
                    ageEdit.error = "请输入学生年龄"
                    return@setOnClickListener
                }
                age = ageString.toInt()
                val student = Student(name, sex, age, grade, lessonName)
                if (isInsert) {
                    DBUtil.studentDao().insertStudent(student)
                    //通知上个页面更新
                    EventBus.getDefault().post(ChangeEvent())
                    Log.d("TAG", "新增: $student")
                } else {
                    student.id = id
                    DBUtil.studentDao().update(student)
                    //通知上个页面更新
                    EventBus.getDefault().post(ChangeEvent())
                    Log.d("TAG", "修改: $student")

                }
                finish()
                Toast.makeText(this, "学生信息编辑成功", Toast.LENGTH_SHORT).show()
            }
        }
        del.setOnClickListener {
            if (type== STUDENT){
                val student = Student(name, sex, age, grade, lessonName, id)
                DBUtil.studentDao().del(student)
                //通知上个页面更新
                EventBus.getDefault().post(ChangeEvent())
            }else{
                val lesson = Lesson(lessonName, grade, name, age, sex, id)
                DBUtil.lessonDao().del(lesson)
                //通知上个页面更新
                EventBus.getDefault().post(TeacherListUpdateEvent())
            }
            finish()
        }
    }

    private fun initView() {
        //获取type type分为两种  STUDENT(1) TEACHER(2)
        type = intent.getIntExtra("type", STUDENT)
        if (type == TEACHER) {
            lessonInput.visibility = View.VISIBLE
            lessonSelect.visibility = View.GONE
            if (isInsert) {
                toolBar.title = "新增老师信息"
            } else {
                toolBar.title = "修改老师信息"
            }
        } else {
            lessonInput.visibility = View.GONE
            lessonSelect.visibility = View.VISIBLE
            if (isInsert) {
                toolBar.title = "新增学生信息"
            } else {
                toolBar.title = "修改学生信息"
            }
            lessons.addAll(DBUtil.lessonDao().loadAll())
            lessonSpinner.setItems(lessons)
            lessonName = lessons[0].name
        }
        gradeSpinner.setItems(*gradeData)
        gradeSpinner.selectedIndex = 0
        sexSpinner.setItems(*sexData)
        sexSpinner.selectedIndex = 0
        setIcon()
    }

    private fun initData() {
        if (!isInsert) {
            if (type == STUDENT) {
                del.visibility = View.VISIBLE
                val student = intent.getSerializableExtra("student") as Student
                name = student.name
                age = student.age
                sex = student.sex
                grade = student.grade
                lessonName = student.lesson
                id = student.id
                nameEdit.setText(name)
                ageEdit.setText(age.toString())
                sexSpinner.selectedIndex = sexData.indexOf(sex)
                gradeSpinner.selectedIndex = gradeData.indexOf(grade)
                val queryLessonByLessonName =
                    DBUtil.lessonDao().queryByLessonName(lessonName)
                Log.d("TAG", "lessonName: $lessonName")
                Log.d("TAG", "initData: $queryLessonByLessonName")
                if (queryLessonByLessonName.isEmpty()) {
                    lessonSpinner.selectedIndex = 0
                } else {
                    lessonSpinner.selectedIndex = lessons.indexOf(queryLessonByLessonName[0])
                }
            } else {
                val lesson = intent.getSerializableExtra("lesson") as Lesson
                name = lesson.teacherName
                age = lesson.age
                sex = lesson.sex
                grade = lesson.grade
                lessonName = lesson.name
                id = lesson.id
                nameEdit.setText(name)
                ageEdit.setText(age.toString())
                lessonEdit.setText(lessonName)
                sexSpinner.selectedIndex = sexData.indexOf(sex)
                gradeSpinner.selectedIndex = gradeData.indexOf(grade)
            }
            setIcon()
        } else {
            del.visibility = View.GONE
        }
    }

    private fun setIcon() {
        if (type == STUDENT) {
            if (sex == "男") {
                icon.setImageResource(R.drawable.man)
            } else {
                icon.setImageResource(R.drawable.woman)
            }
        } else {
            if (sex == "男") {
                icon.setImageResource(R.drawable.teacher_man)
            } else {
                icon.setImageResource(R.drawable.teacher_woman)
            }
        }
    }
}