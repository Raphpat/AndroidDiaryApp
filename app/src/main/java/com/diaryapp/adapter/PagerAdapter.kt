package com.diaryapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.diaryapp.fragment.DatePickerFragment
import com.diaryapp.fragment.TextEditorFragment

internal class PagerAdapter(
    fragmentManager: FragmentManager?, private val mNumOfTabs:
    Int
) : FragmentStatePagerAdapter(
    fragmentManager!!,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DatePickerFragment()
            1 -> TextEditorFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}