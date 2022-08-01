package rs.raf.jun.igor_pejin_8420.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.igor_pejin_8420.data.datasources.local.PopStocksDao
import rs.raf.jun.igor_pejin_8420.data.datasources.remote.PopStocksService
import rs.raf.jun.igor_pejin_8420.data.models.entities.PopularStocksEntity
import rs.raf.jun.igor_pejin_8420.data.models.entities.UserEntity
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import rs.raf.jun.igor_pejin_8420.data.models.responses.Search
import rs.raf.jun.igor_pejin_8420.data.models.ui.PopularStocksUi
import rs.raf.jun.igor_pejin_8420.data.models.ui.User

class PopStocksImpl(
    private val remoteDataSource: PopStocksService,
    private val localDataSource : PopStocksDao
    ): PopStocksRepo {

    override fun fetch(): Observable<List<PopularStocks>> {
        return remoteDataSource
            .getAll()
            .map {
                it.data.quotes
            }
    }

    override fun insertUser(username: String, email: String): Completable {
        val userEntity = UserEntity(null,username,email,10000F,0F)
        return localDataSource
            .insertUser(userEntity)
    }

    override fun insertStock(instrumentId: String, name: String, currency: String, last: Float, kol: Float, userId: Float): Completable {
        val popStock = PopularStocksEntity(null,instrumentId,name,currency,last,kol.toInt(),userId.toLong())
        return localDataSource
            .insertStock(popStock)
    }

    override fun updateRacun(userId: String, novoStanje: Float, noviPortfolio: Float): Completable {
        return localDataSource.updateRacun(userId.toLong(),novoStanje,noviPortfolio)
    }

    override fun getByUsernameAndEmail(username: String, email: String): Observable<User> {

        return localDataSource
            .getByUsernameAndEmail(username,email)
            .map {
                    User(it.id, it.username,it.email,it.stanje,it.portfolio)
            }
    }

    override fun getAllUsers(): Observable<List<User>> {
        return localDataSource
            .getAllUsers()
            .map {
                it.map {
                    User(it.id, it.username,it.email,it.stanje,it.portfolio)
                }
            }
    }

    override fun getAllStocksFromUser(userId: String, instrumentId: String): Observable<List<PopularStocksUi>> {
        return localDataSource
            .getAllStocksFromUser(userId.toLong(),instrumentId)
            .map {
                it.map {
                    PopularStocksUi(it.id,it.instrumentId,it.name,it.currency,it.last,it.kolicina,it.userId)
                }
            }
    }

    override fun getAllStocksFromUserById(userId: String): Observable<List<PopularStocksUi>> {
        return localDataSource
            .getAllStocksFromUserById(userId.toLong())
            .map {
                it.map {
                    PopularStocksUi(it.id,it.instrumentId,it.name,it.currency,it.last,it.kolicina,it.userId)
                }
            }
    }

    override fun deleteAllStocksByIds(userId: String, instrumentId: String): Completable {
        return localDataSource.deleteAllStocksByIds(userId.toLong(),instrumentId)
    }


    override fun detailedView(): Observable<Search> {
        return remoteDataSource
            .detailView()
            .map {
                it.data
            }
    }
}