package com.iremcelikbilek.android_viewpager_iremcelikbilek

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.iremcelikbilek.android_viewpager_iremcelikbilek.adapter.ViewPagerAdapter
import com.iremcelikbilek.android_viewpager_iremcelikbilek.model.OnBoardingData

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //This function must be called before setContentView() to make this setting.
        setWindow()
        setContentView(R.layout.activity_home)
    }

    private fun setWindow() {
        //For make to hide toolbar in window
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar!!.hide()
    }
}