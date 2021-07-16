package com.iremcelikbilek.android_viewpager_iremcelikbilek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.iremcelikbilek.android_viewpager_iremcelikbilek.R
import com.iremcelikbilek.android_viewpager_iremcelikbilek.model.OnBoardingData

class ViewPagerAdapter(private var context: Context, private  var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {

    override fun getCount(): Int = onBoardingDataList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null)
        val imageView: ImageView = view.findViewById(R.id.onBoarding_image)
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val layout: LinearLayout = view.findViewById(R.id.onBoarding_linear_layout)

        imageView.setImageResource(onBoardingDataList[position].imgUrl)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].description
        layout.setBackgroundResource(onBoardingDataList[position].color)

        container.addView(view)

        return view
    }
}