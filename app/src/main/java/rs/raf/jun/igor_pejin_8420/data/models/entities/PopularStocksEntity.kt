package rs.raf.jun.igor_pejin_8420.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popularstocks")
data class PopularStocksEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    val instrumentId: String,
    val name: String,
    val currency: String,
    val last: Float,
    val kolicina: Int,
    val userId: Long
)