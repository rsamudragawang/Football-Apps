package com.ganargatul.football_apps.ui.main.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.ui.main.ui.main.favoritematch.FavoriteMatch
import com.ganargatul.football_apps.ui.main.ui.main.favoriteteam.FavoriteTeam

private val TAB_TITLES = arrayOf(
    FavoriteMatch(), FavoriteTeam()
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return TAB_TITLES[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Favorite Match"
            else -> "Favorite Team"
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}