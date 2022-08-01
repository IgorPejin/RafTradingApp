package rs.raf.jun.igor_pejin_8420.data.models.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long?,
    val username: String,
    val email : String,
    val stanje : Float,
    val portfolio: Float,
)

