package rs.raf.jun.igor_pejin_8420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import rs.raf.jun.igor_pejin_8420.data.models.responses.Search
import rs.raf.jun.igor_pejin_8420.data.models.ui.PopularStocksUi
import rs.raf.jun.igor_pejin_8420.data.models.ui.User

interface PopStocksRepo {

    fun fetch(): Observable<List<PopularStocks>>

    fun insertUser(username:String,email:String) : Completable

    fun insertStock(instrumentId: String, name: String, currency: String, last: Float, kol: Float, userId: Float): Completable

    fun updateRacun(userId: String, novoStanje: Float, noviPortfolio: Float) : Completable

    fun getByUsernameAndEmail(username:String,email:String) :  Observable<User>

    fun getAllUsers() : Observable<List<User>>

    fun getAllStocksFromUser(userId: String, instrumentId: String): Observable<List<PopularStocksUi>>

    fun getAllStocksFromUserById(userId: String): Observable<List<PopularStocksUi>>

    fun deleteAllStocksByIds(userId: String, instrumentId: String) : Completable

    fun detailedView(): Observable<Search>
}