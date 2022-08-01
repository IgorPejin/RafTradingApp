package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import rs.raf.jun.igor_pejin_8420.data.models.ui.User

open class PopStocksState {
    object DataFetched: PopStocksState()

    data class SuccessInsert(val user: User): PopStocksState()
    data class Success(val popstocks: List<PopularStocks>): PopStocksState()
    data class Error(val message: String): PopStocksState()
}