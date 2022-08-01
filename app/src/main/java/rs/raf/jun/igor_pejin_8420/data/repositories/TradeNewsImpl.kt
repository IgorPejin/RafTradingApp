package rs.raf.jun.igor_pejin_8420.data.repositories

import io.reactivex.Observable
import rs.raf.jun.igor_pejin_8420.data.datasources.remote.TradeNewsService
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews

class TradeNewsImpl(
    private val remoteDataSource: TradeNewsService
):TradeNewsRepo {
    override fun fetch(): Observable<List<TradeNews>> {
        return remoteDataSource
            .getAll()
            .map {
                it.data.newsItems
            }
    }
}