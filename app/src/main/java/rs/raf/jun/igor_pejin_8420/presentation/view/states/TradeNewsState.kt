package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews

sealed class TradeNewsState {
    object DataFetched: TradeNewsState()
    data class Success(val tradenews: List<TradeNews>): TradeNewsState()
    data class Error(val message: String): TradeNewsState()
}