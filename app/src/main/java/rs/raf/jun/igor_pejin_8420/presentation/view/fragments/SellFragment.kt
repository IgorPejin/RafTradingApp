package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentSellBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.Search
import rs.raf.jun.igor_pejin_8420.data.models.ui.PopularStocksUi
import rs.raf.jun.igor_pejin_8420.data.models.ui.User
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.states.SellState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.StocksState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber

class SellFragment(private val details: Search) : Fragment(R.layout.fragment_sell) {
    private var _binding: FragmentSellBinding? = null
    private val binding get() = _binding!!

    private lateinit var stanje: TextView
    private lateinit var switch: Switch

    private lateinit var deonica: TextView
    private lateinit var sellButton: Button
    private lateinit var brojDeonica: EditText

    private lateinit var userId:String
    private lateinit var portfolio:String


    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        initObservers()
        initUser()
    }

    private fun initListeners(stocks: List<PopularStocksUi>) {
        var kupovina:Boolean = false

        Timber.e("Klik")

        switch=binding.switchSell

        switch.setOnClickListener()
        {
            Timber.e("Klik")
            kupovina = switch.isChecked
        }
        sellButton.setOnClickListener()
        {
            try {
                if(kupovina==false) { // prodajemo odredjenu kolicinu deonica

                    Timber.e("kupovina false")

                    var pom=0
                    for(stock in stocks)
                    {
                        pom+=stock.kolicina
                    }

                    Timber.e(pom.toString()+"pom")

                    if(pom >= brojDeonica.text.toString().toInt())
                    {

                        val cena = brojDeonica.text.toString().toInt() * details.last
                        Timber.e(cena.toString()+"cena")
                        val novoStanje = stanje.text.toString().toFloat() + cena
                        Timber.e(novoStanje.toString()+"stanjenovo")

                        val noviPortfolio = portfolio.toFloat() - cena.toFloat()
                        Timber.e(noviPortfolio.toString()+"portfolio")

                        var preostalaKolicina = pom - brojDeonica.text.toString().toInt()
                        Timber.e(preostalaKolicina.toString()+"preostala kolicina")

                        if(preostalaKolicina==0)
                        {
                            Timber.e("kolicina 0")
                            popStocksViewModel.deleteAllStocksByIds(userId,details.instrumentId)
                        }
                        else
                        {
                            Timber.e("kolicina > 0")
                            Timber.e(details.instrumentId+""+details.name+""+details.currency+""+details.last+" " +preostalaKolicina.toFloat()+" "+userId.toFloat())
                            popStocksViewModel.deleteAllStocksByIds(userId,details.instrumentId)
                            popStocksViewModel.insertStock(details.instrumentId,details.name,details.currency,details.last,preostalaKolicina.toFloat(),userId.toFloat())
                        }
                        popStocksViewModel.updateRacun(userId, novoStanje, noviPortfolio)
                    }
                }
                else // prodajemo sve deonice
                {
                    var pom=0
                    for(stock in stocks)
                    {
                        pom+=stock.kolicina
                    }

                    brojDeonica.setText(pom.toString())

                    val cena = pom * details.last
                    val novoStanje = stanje.text.toString().toFloat() + cena
                    val noviPortfolio = portfolio.toFloat() - cena

                    popStocksViewModel.deleteAllStocksByIds(userId,details.instrumentId)
                    popStocksViewModel.updateRacun(userId, novoStanje, noviPortfolio)

                }
            }
            catch (e:Exception)
            {
                Toast.makeText(this.context,"Unesite sve podatke", Toast.LENGTH_LONG).show()
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
        popStocksViewModel.sellState.observe(viewLifecycleOwner) {
            renderStateSell(it)
        }
        popStocksViewModel.stocksState.observe(viewLifecycleOwner) {
            renderStocks(it)
        }
    }

    private fun renderStocks(state: StocksState) {
        when (state) {
            is StocksState.AllStocks -> {
                initListeners(state.stocks)
            }
        }
    }

    private fun renderStateSell(state: SellState) {
        when (state) {
            is SellState.AllStocks -> {
                Timber.e("States")
                initListeners(state.stocks)
            }
        }
    }

    private fun renderStateUser(state: UserState) {
        when (state) {
            is UserState.CurrentUser -> {
                userId=state.currentUser.id.toString()
                portfolio=state.currentUser.portfolio.toString()
                popStocksViewModel.getAllStocksFromUser(userId ,details.instrumentId)
                initView(state.currentUser)
            }
            is UserState.Success->
            {
                Toast.makeText(this.context,"Uspesno prodano!",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initView(currentUser: User) {
        deonica=binding.deonicaText
        stanje=binding.stanjeSell
        sellButton=binding.sellBtn
        brojDeonica=binding.brojDeonica

        deonica.text=details.name
        stanje.text=currentUser.stanje.toString()
    }

}