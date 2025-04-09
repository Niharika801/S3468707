package uk.ac.tees.mad.token.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_data")
data class FavoriteEntity(
    @PrimaryKey val id:String,
    val userId:String,
    val name:String,
    val symbol:String,
    val image:String,
    val currentPrice: Double,
    val priceChange: Double,
    val marketCap: Long,
    val volume: Long
)
