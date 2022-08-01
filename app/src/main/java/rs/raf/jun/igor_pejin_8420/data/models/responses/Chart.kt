package rs.raf.jun.igor_pejin_8420.data.models.responses

import com.squareup.moshi.JsonClass
import rs.raf.jun.igor_pejin_8420.data.models.responses.Bar

@JsonClass(generateAdapter = true)
data class Chart(
    val bars: List<Bar>
)
