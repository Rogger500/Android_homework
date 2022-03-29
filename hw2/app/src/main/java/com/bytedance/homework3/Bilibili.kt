package com.bytedance.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class Bilibili : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private var buttonView:  Button? = null
    private var likeView:  ImageView? = null
    private var like2View:  ImageView? = null
    private var coinView:  ImageView? = null
    private var favoriteView:  ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bilibili)
        buttonView = findViewById(R.id.home_page_btn)
        buttonView!!.setOnClickListener(this)
        likeView = findViewById(R.id.like_image)
        likeView!!.setOnClickListener(this)
        likeView!!.setOnLongClickListener(this)
        like2View = findViewById(R.id.like2_image)
        coinView = findViewById(R.id.coin_image)
        coinView!!.setOnClickListener(this)
        favoriteView = findViewById(R.id.favorite_image)
        favoriteView!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.like_image -> {
                val loadAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.like_single_click)
                likeView!!.startAnimation(loadAnimation)
            }
            R.id.coin_image -> {
                val loadAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.coin_single_click)
                coinView!!.startAnimation(loadAnimation)
            }
            R.id.favorite_image -> {
                val loadAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.favorite_single_click)
                favoriteView!!.startAnimation(loadAnimation)
            }
            R.id.home_page_btn -> {
                startActivity(Intent(this, MainActivity().javaClass))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    override fun onLongClick(v: View): Boolean {
        var loadAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.like_long_click)
        likeView!!.startAnimation(loadAnimation)
        likeView!!.visibility=View.INVISIBLE
        like2View!!.visibility = View.VISIBLE
        return true
    }
}