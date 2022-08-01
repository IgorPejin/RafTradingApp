package rs.raf.jun.igor_pejin_8420.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.ActivityMainBinding
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.HomeFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.LoginFragment

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        val bundle = intent.extras

        val screen= bundle?.get("screen")

        if(screen=="login")
        {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, LoginFragment())
            transaction.commit()
        }
        else
        {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, HomeFragment())
            transaction.commit()
        }
    }
}