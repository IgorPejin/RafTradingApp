package rs.raf.jun.igor_pejin_8420.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jun.igor_pejin_8420.data.models.entities.PopularStocksEntity
import rs.raf.jun.igor_pejin_8420.data.models.entities.UserEntity

@Database(
    entities = [PopularStocksEntity::class,UserEntity::class],
    version = 12,
    exportSchema = false)
@TypeConverters()
abstract class RafTradingDatabase : RoomDatabase() {
    abstract fun getPopStocksDao(): PopStocksDao
}