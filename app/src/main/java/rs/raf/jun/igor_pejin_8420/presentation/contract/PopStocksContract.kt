package rs.raf.jun.igor_pejin_8420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.igor_pejin_8420.presentation.view.states.*

interface PopStocksContract {

    interface ViewModel {
        val popStocksState: LiveData<PopStocksState>
        val detailsState: LiveData<DetailsState>
        val userState: LiveData<UserState>
        val loginState: LiveData<LoginState>
        val sellState: LiveData<SellState>
        val stocksState: LiveData<StocksState>

        fun fetchAllStocks()
        fun insertUser(username:String,email:String)
        fun getUserByUsernameAndEmail(username:String,email:String)

        fun insertStock(instrumentId: String, name: String, currency: String, last: Float, kol: Float, userId: Float)

        fun updateRacun(userId: String, novoStanje: Float, noviPortfolio: Float)

        fun allUsers()

        fun getAllStocksFromUser(userId: String, instrumentId: String)

        fun getAllStocksFromUserByUserId(userId: String)

        fun deleteAllStocksByIds(userId: String, instrumentId: String)


        fun detailedView()
    }

}