package rs.raf.jun.igor_pejin_8420.data.models.ui

data class PopularStocksUi(
    val id:Long?,
    val instrumentId: String,
    val name: String,
    val currency: String,
    val last: Float,
    val kolicina: Int,
    val userId: Long
)
