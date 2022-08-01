package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.sourceInformation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentLoginBinding
import rs.raf.jun.igor_pejin_8420.data.models.ui.User
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.composable.LoginLayout
import rs.raf.jun.igor_pejin_8420.presentation.view.states.LoginState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.PopStocksState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private  var users: List<User>? = null

    private lateinit var currentUserEmail:String
    private lateinit var currentUsername:String

    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        val composeHolder=binding.composeHolderLogin
        composeHolder.setContent {
            LoginLayout(onClick = :: onLoginClick)
        }
        initObservers()
        users?.let { checkInsert(it) }
    }

    private fun initObservers() {

        val prefs: SharedPreferences by inject()
        val usernamePrefs = prefs.getString("user_key",null)
        val emailPrefs = prefs.getString("email_key",null)

        popStocksViewModel.loginState.observe(viewLifecycleOwner) {
            renderStateLogin(it)
        }
        popStocksViewModel.userState.observe(viewLifecycleOwner) {
            renderStateUser(it)
        }
        popStocksViewModel.getUserByUsernameAndEmail(usernamePrefs.toString(),emailPrefs.toString())
        popStocksViewModel.allUsers()
    }

    private fun renderStateUser(state: UserState) {
        when (state) {
            is UserState.CurrentUser -> {
                currentUsername=state.currentUser.username
                currentUserEmail=state.currentUser.email
            }
        }
    }

    private fun renderStateLogin(state: LoginState) {
        when (state) {
            is LoginState.AllUsers -> {
                users=state.users
            }
        }
    }

    private fun onLoginClick(username:String,email:String,password:String)
    {
        if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            if(checkEmail(email) && checkPassword(password)) {

                val prefs: SharedPreferences by inject()

                prefs.edit().clear().apply()
                prefs.edit().putString("user_key", username).apply()
                prefs.edit().putString("email_key", email).apply()
                prefs.edit().putString("password_key", password).apply()

                users?.let { checkInsert(it) }

                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.fragmentContainer, HomeFragment())
                transaction.commit()
            }else Toast.makeText(this.context,"Proverite vas email i password", Toast.LENGTH_LONG).show()
        }else Toast.makeText(this.context,"Unesite sve podatke", Toast.LENGTH_SHORT).show()
    }

    private fun checkPassword(password: String): Boolean {
        val mockPassword:String="12345";
        if(password.equals(mockPassword) && password.length>=5)
            return true
        return false
    }

    private fun checkEmail(email: String): Boolean {
        if(email!="" && email.contains("@")
            &&((email.contains(".com"))
                    || (email.contains(".net"))
                    || (email.contains(".rs"))
                    || (email.contains(".org")))
        )return true
        return false
    }

    private fun checkInsert(users: List<User>) {

        Timber.e("size")
        Timber.e(users.size.toString())

        val prefs: SharedPreferences by inject()
        val usernamePrefs = prefs.getString("user_key",null)
        val emailPrefs = prefs.getString("email_key",null)

        var brojac=0

        if(users.isNotEmpty()) {
            for (user in users) {
                Timber.e(user.toString())
                if (!user.email.equals(emailPrefs) && !user.username.equals(usernamePrefs)) {
                    Timber.e("Radim insert")
                    brojac++
                }
            }
            if(brojac==users.size)
            {
                popStocksViewModel.insertUser(usernamePrefs.toString(), emailPrefs.toString())
            }
        }
        else
        {
            popStocksViewModel.insertUser(usernamePrefs.toString(), emailPrefs.toString())
        }




    }


}