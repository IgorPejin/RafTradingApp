package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metrics (
    val alpha: Float,
    val beta: Float,
    val sharp: Float,
    val marketCup: Float,
    val eps: Float,
    val ebit: Float
    )
