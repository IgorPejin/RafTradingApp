package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TradeNews(
    val title: String,
    val content: String,
    val link: String,
    val date: String,
    val image: String
)
