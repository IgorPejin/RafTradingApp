package rs.raf.jun.igor_pejin_8420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.jun.igor_pejin_8420.data.models.responses.DataSearchResponse
import rs.raf.jun.igor_pejin_8420.data.models.responses.DataStockResponse

interface PopStocksService {

    @GET("getIndexes")
    fun getAll(): Observable<DataStockResponse>

    @GET("searchQuotes")
    fun detailView(): Observable<DataSearchResponse>

}