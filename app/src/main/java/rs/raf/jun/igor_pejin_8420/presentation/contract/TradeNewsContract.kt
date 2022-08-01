package rs.raf.jun.igor_pejin_8420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.igor_pejin_8420.presentation.view.states.TradeNewsState

interface TradeNewsContract {
    interface ViewModel {
        val tradeNewsState: LiveData<TradeNewsState>
        fun fetchAllNews()
    }
}