package com.diaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class DiaryActivity: AppCompatActivity() {

    //TODO Add back button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        // Do toolbar things
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.datePicker))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.entryEditor))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val adapter = PagerAdapter(
            supportFragmentManager,
            tabLayout.tabCount
        )
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}