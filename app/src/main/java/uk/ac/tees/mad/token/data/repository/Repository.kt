package uk.ac.tees.mad.token.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.token.data.local.FavoriteEntity
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.model.TokenData

interface Repository {
    suspend fun getTokenData():List<TokenData>
    suspend fun getTokenDetails(id:String):DetailData
    suspend fun addFavoriteData(entity: FavoriteEntity)
    fun getFavoriteTokens(userId:String): Flow<List<FavoriteEntity>>
    suspend fun deleteFavoriteData(entity: FavoriteEntity)
}