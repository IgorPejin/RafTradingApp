package rs.raf.jun.igor_pejin_8420.presentation.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.DiscoverFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.PortfolioFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.ProfileFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val ITEM_COUNT = 3
    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            FRAGMENT_1 -> fragment = DiscoverFragment()
            FRAGMENT_2 -> fragment = PortfolioFragment()
            else -> fragment = ProfileFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FRAGMENT_1 -> "1"
            FRAGMENT_2 -> "2"
            else -> "3"
        }
    }

    companion object {
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
    }
}