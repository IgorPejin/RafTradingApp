package rs.raf.jun.igor_pejin_8420.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.igor_pejin_8420.data.models.entities.PopularStocksEntity
import rs.raf.jun.igor_pejin_8420.data.models.entities.UserEntity

@Dao
abstract class PopStocksDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertUser(entities: UserEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertStock(entities: PopularStocksEntity): Completable

    @Query("UPDATE user SET stanje = :novoStanje,portfolio=:noviPortfolio WHERE id = :userId ")
    abstract fun updateRacun(userId:Long,novoStanje:Float,noviPortfolio:Float) : Completable

    @Query("SELECT * FROM user WHERE username LIKE :username AND :email LIKE email ")
    abstract fun getByUsernameAndEmail(username:String,email:String): Observable<UserEntity>

    @Query("SELECT * FROM user")
    abstract fun getAllUsers(): Observable<List<UserEntity>>

    @Query("SELECT * FROM popularstocks WHERE userId = :userId AND instrumentId LIKE :instrumentId")
    abstract fun getAllStocksFromUser(userId: Long, instrumentId: String): Observable<List<PopularStocksEntity>>

    @Query("SELECT * FROM popularstocks WHERE userId = :userId ")
    abstract fun getAllStocksFromUserById(userId: Long): Observable<List<PopularStocksEntity>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<PopularStocksEntity>): Completable

    @Query("DELETE FROM popularstocks")
    abstract fun deleteAll()

    @Query("DELETE FROM popularstocks WHERE userId = :userId AND instrumentId = :instrumentId ")
    abstract fun deleteAllStocksByIds(userId: Long, instrumentId: String) : Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<PopularStocksEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}