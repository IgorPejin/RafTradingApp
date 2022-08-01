package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentDetailBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.Search
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.states.DetailsState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.StocksState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var simbol: TextView
    private lateinit var last: TextView
    private lateinit var open: TextView
    private lateinit var bid: TextView
    private lateinit var close: TextView
    private lateinit var ask: TextView
    private lateinit var mktcap: TextView
    private lateinit var eps: TextView
    private lateinit var ebit: TextView
    private lateinit var beta: TextView

    private lateinit var buyBtn: Button
    private lateinit var sellBtn: Button
    private lateinit var userId:String

    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        open = binding.open
        bid = binding.bid
        close = binding.close
        ask = binding.ask
        mktcap = binding.mktcap
        eps = binding.eps
        beta = binding.beta
        ebit = binding.ebit
        simbol = binding.simbol
        last = binding.last
        buyBtn = binding.button
        sellBtn = binding.button2
        initObservers()
        initUser()
    }

    private fun initListeners(details: Search)
    {
        buyBtn.setOnClickListener()
        {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, BuyFragment(details))
            transaction.commit()
        }

        sellBtn.setOnClickListener()
        {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, SellFragment(details))
            transaction.commit()
        }
    }

    private fun initObservers() {
        popStocksViewModel.detailsState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        popStocksViewModel.userState.observe(viewLifecycleOwner) {
            renderStateUser(it)
        }
        popStocksViewModel.stocksState.observe(viewLifecycleOwner) {
            renderStateStocks(it)
        }

        popStocksViewModel.detailedView()
    }

    private fun renderStateUser(state: UserState) {
        when (state) {
            is UserState.CurrentUser -> {
                userId=state.currentUser.id.toString()
            }
        }
    }

    private fun renderStateStocks(state: StocksState) {
        when (state) {
            is StocksState.AllStocks -> {
                if(state.stocks.size==0)
                {
                    sellBtn.isClickable=false
                }
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


    @SuppressLint("SetTextI18n")
    private fun renderState(state: DetailsState){
        when (state) {
            is DetailsState.Success -> {
                simbol.text = state.details.symbol
                last.text = state.details.last.toString() + "$"
                open.text = state.details.open.toString()
                bid.text = state.details.bid.toString()
                close.text = state.details.close.toString()
                ask.text = state.details.ask.toString()
                mktcap.text = state.details.metrics.marketCup.toString()
                eps.text = state.details.metrics.eps.toString()
                beta.text = state.details.metrics.beta.toString()
                ebit.text = state.details.metrics.ebit.toString()


                initChart(state.details)
                initListeners(state.details)

            }
            is DetailsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initChart(details: Search) {
        val barchart = binding.chartDetaljniPrikaz
        var i: Int = 0
        val barEntries =  ArrayList<BarEntry>()
        for (i in 0 until details.chart.bars.size) {
            barEntries.add(BarEntry(convertFloat(details.chart.bars[i].time),details.chart.bars[i].price))
        }
        val barDataSet = BarDataSet(barEntries,"Cena / Vreme")
        barDataSet.setColors(Color.YELLOW)

        val bardata = BarData(barDataSet)
        bardata.barWidth = 0.1f
        bardata.setDrawValues(false)
        barchart.data = bardata
        barchart.setBackgroundColor(R.color.mainBackRaspored)
        barchart.description.text=""
        barchart.invalidate()
    }

    private fun convertFloat(time: String): Float {
        val list = time.split("T")
        val vreme = list[1].split(":")
        return vreme[0].toFloat() + (vreme[1].toFloat()) / 100
    }
}