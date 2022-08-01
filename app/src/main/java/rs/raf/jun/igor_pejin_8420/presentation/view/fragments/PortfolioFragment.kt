package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentPortfolioBinding
import rs.raf.jun.igor_pejin_8420.data.models.ui.User
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.adapter.TradeNewsAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.adapter.PopStocksAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPortfolio.adapter.PortfolioAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.states.StocksState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!

    private lateinit var stanje: TextView
    private lateinit var portfolio: TextView
    private lateinit var username: TextView
    private lateinit var userId:String

    private lateinit var adapter: PopStocksAdapter

    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        initUser()
        initObservers()
    }

    private fun initView(currentUser: User) {
        stanje=binding.stanjeText
        portfolio=binding.vrednostText
        username=binding.usernamePort
        username.text= currentUser.username
        stanje.text=currentUser.stanje.toString()
        portfolio.text=currentUser.portfolio.toString()
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

        popStocksViewModel.stocksState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderStateUser(state: UserState) {
        when (state) {
            is UserState.CurrentUser -> {
                userId=state.currentUser.id.toString()
                popStocksViewModel.getAllStocksFromUserByUserId(userId)
                initView(state.currentUser)
                initRecycler()
            }
        }
    }

    private fun initRecycler() {
        binding.recPortfolio.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        popStocksViewModel.stocksState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        adapter = PopStocksAdapter {
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, DetailFragment())
            transaction.commit()
        }
        binding.recPortfolio.adapter = adapter
    }

    private fun renderState(state: StocksState) {
        when (state) {
//            is StocksState.AllStocks -> {
//                adapter.submitList(state.stocks)
//                Timber.e(state.stocks.toString()+"stocks lista")
//            }
        }
    }
}