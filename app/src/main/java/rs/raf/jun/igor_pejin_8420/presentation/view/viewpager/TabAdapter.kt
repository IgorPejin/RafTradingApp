package rs.raf.jun.igor_pejin_8420.presentation.view.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.NewsFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.PopularStocksFragment

class TabAdapter(fm: FragmentManager, requireContext: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val ITEM_COUNT = 2
    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            FRAGMENT_1 -> fragment = NewsFragment()
            else -> fragment = PopularStocksFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FRAGMENT_1 -> "News"
            else -> "Popular stocks"
        }
    }

    companion object {
        const val FRAGMENT_1 = 0
    }
}
