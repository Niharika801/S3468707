package uk.ac.tees.mad.token.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteData(entity: FavoriteEntity)

    @Update
    suspend fun updateFavoriteData(entity: FavoriteEntity)

    @Query("SELECT * FROM favorite_data WHERE userId = :userId")
    fun getFavoriteTokens(userId:String):Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavoriteData(entity: FavoriteEntity)
}