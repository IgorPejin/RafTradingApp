package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass
import rs.raf.jun.igor_pejin_8420.data.models.responses.Chart
import rs.raf.jun.igor_pejin_8420.data.models.responses.Metrics

@JsonClass(generateAdapter = true)
data class Search(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Float,
    val open: Float,
    val close: Float,
    val bid: Float,
    val ask: Float,
    val metrics: Metrics,
    val chart: Chart
)
