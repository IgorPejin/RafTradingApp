package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks

@JsonClass(generateAdapter = true)
data class Quotes(
    val quotes: List<PopularStocks>
)
