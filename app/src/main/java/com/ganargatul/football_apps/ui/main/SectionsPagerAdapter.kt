package com.ganargatul.football_apps.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ganargatul.football_apps.ui.main.detailleague.League
import com.ganargatul.football_apps.ui.main.nextevent.NextEvent
import com.ganargatul.football_apps.ui.main.pastevent.PastEvent
import com.ganargatul.football_apps.ui.main.standingleague.StandingLeague
import com.ganargatul.football_apps.ui.main.teamleague.TeamLeague

private val TAB_TITLES = arrayOf(
   NextEvent(), PastEvent(), StandingLeague(), TeamLeague(), League()
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
            0 -> "Next Match"
            1 -> "Past Match"
            2 -> "Standing"
            3 -> "Team"
            else -> "Detail League"
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}