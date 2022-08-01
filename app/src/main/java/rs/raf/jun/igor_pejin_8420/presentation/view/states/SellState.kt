package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.ui.PopularStocksUi
import rs.raf.jun.igor_pejin_8420.data.models.ui.User

open class SellState {
    data class AllStocks(val stocks: List<PopularStocksUi>): SellState()
    data class Error(val message: String): SellState()
}