package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentProfileBinding
import rs.raf.jun.igor_pejin_8420.presentation.view.activities.MainActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var button: Button;
    private lateinit var username: TextView;
    private lateinit var email: TextView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        val prefs: SharedPreferences by inject()

        button=binding.logout
        username=binding.username
        email=binding.email

        var username2 = prefs.getString("user_key",null);
        var email2 = prefs.getString("email_key",null);

        username.text = username2
        email.text = email2

        button.setOnClickListener() {
            prefs.edit().clear().apply()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}