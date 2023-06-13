@file:Suppress("DEPRECATION")
package com.rentaloca.rentalocaapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rentaloca.rentalocaapp.ui.home.all.HomeAllFragment
import com.rentaloca.rentalocaapp.ui.home.foryou.HomeForYouFragment

class SectionPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence {
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
        val fragment : Fragment
        return when(position) {
            0 -> {
                fragment = HomeAllFragment()
                fragment
            }
            1 -> {
                fragment = HomeForYouFragment()
                fragment
            }
            else -> {
                fragment = HomeAllFragment()
                fragment
            }
        }
    }

}