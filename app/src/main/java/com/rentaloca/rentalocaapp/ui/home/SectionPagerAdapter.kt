package com.rentaloca.rentalocaapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rentaloca.rentalocaapp.ui.home.all.HomeAllFragment

class SectionPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "All"
            1 -> "For You"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = HomeAllFragment()
                return fragment
            }
            1 -> {
                fragment = HomeAllFragment()
                return fragment
            }
            else -> {
                fragment = HomeAllFragment()
                return fragment
            }
        }
    }

}