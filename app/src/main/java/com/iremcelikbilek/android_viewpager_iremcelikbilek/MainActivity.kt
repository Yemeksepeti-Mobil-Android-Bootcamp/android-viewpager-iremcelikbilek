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

class MainActivity : AppCompatActivity() {

    var viewPagerAdapter: ViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var nextBtn: Button? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null
    val onBoardingDataList: MutableList<OnBoardingData> = ArrayList()
    private val description: String = "Share a gift to your loved one this christmas \nwith ease, just with one or two clicks"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //We call this function because we need to check if the application is opened for the first time.
        //If isFirstTimeRun = true -> navigate to home page
        //If isFirstTimeRun = false -> continue this page
        if(restorePrefData()) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        //This function must be called before setContentView() to make this setting.
        setWindow()

        setContentView(R.layout.activity_main)

        initViews()

        initOnBoardingData()

        manageTabLayout()
    }

    private fun setWindow() {
        //For make to hide toolbar in window
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar!!.hide()
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tab_indicator)
        nextBtn = findViewById(R.id.next_btn)
    }

    private fun initOnBoardingData() {
        onBoardingDataList.add(OnBoardingData("Sharing made\nEasy", description, R.drawable.onboarding_1, R.color.onboarding_1))
        onBoardingDataList.add(OnBoardingData("No hassle, no\nStress", description, R.drawable.onboarding_2, R.color.onboarding_2))
        onBoardingDataList.add(OnBoardingData("Delivery to your\nDoorstep", description, R.drawable.onboarding_3, R.color.onboarding_3))

        setOnBoardingViewPagerAdapter(onBoardingDataList)
    }

    private fun manageTabLayout() {

        //To assign value to the position we need to get the current index of the view pager
        position = onBoardingViewPager!!.currentItem

        //To move with button in viewpager
        nextBtn?.setOnClickListener {
            //If the position is less than the list length, the position is increased and the current index of the viewpager needs to be updated.
            if(position < onBoardingDataList.size) {
                position++
                onBoardingViewPager!!.currentItem = position
            }

            //If the end of the list is reached, it will be redirected to the home page
            if(position == onBoardingDataList.size) {
                //We are calling this function because we need to record that the onBoarding screen is called for the first time.
                //Otherwise we will see the onboarding screen every time the app is opened.
                savePrefData()
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        //To move with tabLayout in viewpager
        tabLayout!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //If you are acting with tab layout, we need to update the position with the tab layout's position.
                position = tab!!.position

                //If we are at the end of the list, we must change the button text to switch to the next page
                //otherwise the button text should stay the same
                if(tab.position == onBoardingDataList.size - 1) {
                    nextBtn!!.text = "Get Started"
                }else {
                    nextBtn!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        } )
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingDataList: List<OnBoardingData>) {
        onBoardingViewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPagerAdapter = ViewPagerAdapter(this, onBoardingDataList)
        onBoardingViewPager!!.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }

    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData() : Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)

    }
}