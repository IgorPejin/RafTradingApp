package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import rs.raf.jun.igor_pejin_8420.data.models.ui.PopularStocksUi

open class StocksState {
    data class AllStocks(val stocks: List<PopularStocksUi>): StocksState()
    data class AllStocksId(val stocks: List<PopularStocks>): StocksState()
    data class Error(val message: String): StocksState()
}