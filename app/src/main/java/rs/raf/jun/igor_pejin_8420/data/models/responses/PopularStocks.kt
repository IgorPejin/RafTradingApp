package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularStocks(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Float,
    val changeFromPreviousClose: Float,
    val percentChangeFromPreviousClose: Float,
    val marketName: String,
    val recommendation: Recommendation,
    val chart: Chart
)
