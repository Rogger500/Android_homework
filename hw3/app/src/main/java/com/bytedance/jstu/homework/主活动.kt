package com.bytedance.jstu.homework

import android.animation.TimeAnimator
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.math.floor

class 主活动 : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.zhuhuodong)

		// 时钟app：实现一个电子表，与机械表联动；可以实现切换到手动拨动指针的模式。
		val 那个时钟视图 = findViewById<指针式时钟视图>(R.id.时钟视图)
		val 时间文字 = findViewById<TextView>(R.id.数字时钟文字)
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			(那个时钟视图.parent as ViewGroup).visibility = ViewGroup.GONE
		}
		TimeAnimator().apply {
			setTimeListener { _, _, _ ->
				if (!那个时钟视图.触摸时修改时分秒) 那个时钟视图.当前值 = Calendar.getInstance().let {
					it.get(Calendar.HOUR) * 3600 + it.get(Calendar.MINUTE) * 60 + it.get(Calendar.SECOND) + it.get(Calendar.MILLISECOND) / 1000f
				}
				那个时钟视图.invalidate()
				时间文字.text = String.format("%02.0f:%02.0f:%02.0f",
					floor(那个时钟视图.当前值 / 3600f),
					floor(那个时钟视图.当前值.mod(3600f) / 60f),
					floor(那个时钟视图.当前值.mod(60f)),
				)
			}
		}.start()

		val 词典搜索框 = findViewById<EditText>(R.id.词典搜索框)
		词典搜索框.setOnEditorActionListener { 搜索框, 动作, _ ->
			if (动作 != EditorInfo.IME_ACTION_SEARCH) return@setOnEditorActionListener false
			if (搜索框.text.isNotBlank()) {
				startActivity(Intent(this@主活动, 词典释义活动::class.java).apply {
					putExtra("求", 搜索框.text.toString())
				})
			}
			true
		}
	}

}
