package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews

@JsonClass(generateAdapter = true)
data class TradeNewsItem(
    val newsItems: List<TradeNews>
)
