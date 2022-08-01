package rs.raf.jun.igor_pejin_8420.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.FragmentNewsBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews
import rs.raf.jun.igor_pejin_8420.presentation.contract.TradeNewsContract
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.adapter.TradeNewsAdapter
import rs.raf.jun.igor_pejin_8420.presentation.view.states.TradeNewsState
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.TradeNewsViewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val tradeNewsViewModel: TradeNewsContract.ViewModel by viewModel<TradeNewsViewModel>()
    private lateinit var adapter: TradeNewsAdapter

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
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
        binding.recTnews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = TradeNewsAdapter { tradeNews: TradeNews ->
            val url = tradeNews.link
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.recTnews.adapter = adapter
    }

    private fun initRecycler() {
        tradeNewsViewModel.tradeNewsState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        tradeNewsViewModel.fetchAllNews()
    }

    private fun renderState(state: TradeNewsState){
        when (state) {
            is TradeNewsState.Success -> {
                adapter.submitList(state.tradenews)
            }
            is TradeNewsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is TradeNewsState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }

}