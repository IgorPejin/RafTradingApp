package rs.raf.jun.igor_pejin_8420.data.repositories

import io.reactivex.Observable
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews

interface TradeNewsRepo {

    fun fetch(): Observable<List<TradeNews>>
}