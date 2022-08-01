package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentHomeBinding
import rs.raf.jun.igor_pejin_8420.presentation.view.adapters.HomePageAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.viewpager.PagerAdapter

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initViewPager()
        initNavigation()
    }
    private fun initViewPager(){
        viewPager = binding.viewPager
        viewPager.adapter = HomePageAdapter(childFragmentManager)
    }
    private fun initNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_1) {
                viewPager.setCurrentItem(HomePageAdapter.FRAGMENT1, false)
            }else if(item.itemId == R.id.navigation_2){
                viewPager.setCurrentItem(HomePageAdapter.FRAGMENT2, false)
            }else if(item.itemId == R.id.navigation_3){
                viewPager.setCurrentItem(HomePageAdapter.FRAGMENT3, false)
            }
            return@setOnItemSelectedListener true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}