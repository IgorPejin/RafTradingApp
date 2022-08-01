package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataStockResponse(
    val data: Quotes
)
