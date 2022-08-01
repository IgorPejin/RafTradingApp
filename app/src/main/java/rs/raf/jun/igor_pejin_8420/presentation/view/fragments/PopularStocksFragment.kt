package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentPopstocksBinding
import rs.raf.jun.igor_pejin_8420.data.models.ui.User
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.adapter.PopStocksAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.states.UserState
import rs.raf.jun.igor_pejin_8420.presentation.view.states.PopStocksState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel
import timber.log.Timber


class PopularStocksFragment : Fragment(R.layout.fragment_popstocks) {

    private var _binding: FragmentPopstocksBinding? = null
    private val binding get() = _binding!!

    private val popStocksViewModel: PopStocksContract.ViewModel by sharedViewModel<PopStocksViewModel>()

    private lateinit var adapter: PopStocksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopstocksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        initRecycler()
        initObservers()
    }

    private fun initObservers() {
        popStocksViewModel.popStocksState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        popStocksViewModel.fetchAllStocks()
    }


    private fun initRecycler() {
        binding.recPopStock.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = PopStocksAdapter {
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainer, DetailFragment())
            transaction.commit()
        }
        binding.recPopStock.adapter = adapter
    }

    private fun renderState(state: PopStocksState){
        when (state) {
            is PopStocksState.Success -> {
                adapter.submitList(state.popstocks)
            }
            is PopStocksState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is PopStocksState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }


}
