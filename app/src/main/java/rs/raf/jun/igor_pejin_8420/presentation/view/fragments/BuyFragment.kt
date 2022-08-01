package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentBuyBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.Search
import rs.raf.jun.igor_pejin_8420.data.models.ui.User
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber

class BuyFragment(private val details: Search) : Fragment(R.layout.fragment_buy) {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    private lateinit var naslovDeonica:TextView
    private lateinit var stanje:TextView
    private lateinit var buyButton:Button
    private lateinit var kolicinaNovca:EditText
    private lateinit var userId:String
    private lateinit var portfolio:String

    private lateinit var switch: Switch

    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        stanje=binding.stanjeText2
        buyButton=binding.buyButton
        initObservers()
        initUser()
        initListeners()
    }

    private fun initListeners() {

        var kupovina:Boolean = false

        switch=binding.buySwitch

        switch.setOnClickListener()
        {
            kupovina = switch.isChecked
        }
        buyButton.setOnClickListener()
        {
            try {
                if(!kupovina) { // kupujemo parama
                    if (details.last < kolicinaNovca.text.toString().toFloat() &&
                        kolicinaNovca.text.toString().isNotEmpty() &&
                        stanje.text.toString().toFloat() > kolicinaNovca.text.toString().toFloat()
                    ) {
                        var kol = kolicinaNovca.text.toString().toFloat() / details.last
                        val novoStanje = stanje.text.toString().toFloat() - kolicinaNovca.text.toString().toFloat()
                        val noviPortfolio = portfolio.toFloat() + kolicinaNovca.text.toString().toFloat()
                        popStocksViewModel.insertStock(details.instrumentId, details.name, details.currency, details.last, kol, userId.toFloat())
                        popStocksViewModel.updateRacun(userId, novoStanje, noviPortfolio)
                    } else {
                        Toast.makeText(this.context, "Nemate dovoljno novca na racunu.", Toast.LENGTH_LONG).show()
                    }
                }
                else
                {
                    if(kolicinaNovca.text.toString().isNotEmpty())
                    {
                        var cena = details.last * kolicinaNovca.text.toString().toFloat()
                        if(stanje.text.toString().toFloat() > cena)
                        {
                            val novoStanje = stanje.text.toString().toFloat() - cena
                            val noviPortfolio = portfolio.toFloat() + cena

                            popStocksViewModel.insertStock(details.instrumentId, details.name, details.currency, details.last, kolicinaNovca.text.toString().toFloat(), userId.toFloat())
                            popStocksViewModel.updateRacun(userId, novoStanje, noviPortfolio)
                        }
                    }

                }
            }
            catch (e:Exception)
            {
                Toast.makeText(this.context,"Unesite sve podatke",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initUser() {
        val prefs: SharedPreferences by inject()
        val username: String? = prefs.getString("user_key", null)
        val email: String? = prefs.getString("email_key", null)
        if (username != null && email!=null) {
            popStocksViewModel.getUserByUsernameAndEmail(username,email)
        };
    }

    private fun initObservers() {
        popStocksViewModel.userState.observe(viewLifecycleOwner) {
            renderStateUser(it)
        }
    }

    private fun renderStateUser(state: UserState) {
        when (state) {
            is UserState.CurrentUser -> {
                userId=state.currentUser.id.toString()
                initView(state.currentUser)
            }
            is UserState.Success ->
            {
                Toast.makeText(this.context,"Uspesno kupljena deonica!",Toast.LENGTH_LONG ).show()
            }
            is UserState.Update ->
            {
                Toast.makeText(this.context,"Stanje na racunu je promenjeno",Toast.LENGTH_LONG ).show()
            }
        }
    }

    private fun initView(currentUser: User) {
        kolicinaNovca=binding.kolicinaNovca
        naslovDeonica=binding.deonicaText
        switch=binding.buySwitch

        naslovDeonica.text=details.name
        stanje.text=currentUser.stanje.toString()
        portfolio=currentUser.portfolio.toString()
    }

}