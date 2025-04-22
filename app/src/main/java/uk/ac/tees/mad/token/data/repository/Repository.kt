package uk.ac.tees.mad.token.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.token.data.local.FavoriteEntity
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.model.TokenData

interface Repository {
    suspend fun getTokenData(currency:String):List<TokenData>
    suspend fun getTokenDetails(id:String, currency:String):DetailData
    suspend fun addFavoriteData(entity: FavoriteEntity)
    suspend fun updateFavoriteData(entity: FavoriteEntity)
    fun getFavoriteTokens(userId:String): Flow<List<FavoriteEntity>>
    suspend fun deleteFavoriteData(entity: FavoriteEntity)
}