package rs.raf.jun.igor_pejin_8420.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentTransaction
import org.koin.android.ext.android.inject
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.ActivityMainBinding
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.HomeFragment
import rs.raf.jun.igor_pejin_8420.presentation.view.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val userkey = "user_key"
    val emailkey = "email_key"
    val passwordkey = "password_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        init()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init() {

        val prefs: SharedPreferences by inject()
        val user: String? = prefs.getString(userkey, null)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            if (user == null) {
                intent = Intent(this, HomeActivity::class.java)
                val login = "login"
                intent.putExtra("screen",login)
                startActivity(intent)
                finish()
            }
            else
            {
                intent = Intent(this, HomeActivity::class.java)
                val home = "home"
                intent.putExtra("screen",home)
                startActivity(intent)
                finish()
            }
            false
        }
    }

}
