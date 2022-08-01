package rs.raf.jun.igor_pejin_8420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.jun.igor_pejin_8420.data.models.responses.DataResponse

interface TradeNewsService {
    @GET("getNews")
    fun getAll(): Observable<DataResponse>
}