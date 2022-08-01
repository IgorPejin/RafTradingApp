package rs.raf.jun.igor_pejin_8420.presentation.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.DiscoverFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.PortfolioFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.ProfileFragment

class HomePageAdapter(
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val itemCount = 3

    companion object{
        const val FRAGMENT1 = 0
        const val FRAGMENT2 = 1
        const val FRAGMENT3 = 2
    }

    override fun getCount(): Int {
        return itemCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT1 -> DiscoverFragment()
            FRAGMENT2 -> PortfolioFragment()
            else -> ProfileFragment()
        }
    }

}