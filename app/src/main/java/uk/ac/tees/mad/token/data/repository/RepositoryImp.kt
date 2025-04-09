package uk.ac.tees.mad.token.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.token.data.local.FavoriteEntity
import uk.ac.tees.mad.token.data.local.FavoriteTokenDao
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.model.TokenData
import uk.ac.tees.mad.token.data.remote.CryptoApiService
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val api:CryptoApiService,
    private val dao:FavoriteTokenDao
) :Repository{
    override suspend fun getTokenData(): List<TokenData> {
        return api.getCryptoData()
    }

    override suspend fun getTokenDetails(id:String): DetailData {
        return api.getCryptoDetails(
            id = id
        )
    }

    override suspend fun addFavoriteData(entity: FavoriteEntity) {
        dao.addFavoriteData(entity)
    }

    override fun getFavoriteTokens(userId: String): Flow<List<FavoriteEntity>> {
        return dao.getFavoriteTokens(userId)
    }

    override suspend fun deleteFavoriteData(entity: FavoriteEntity) {
        dao.deleteFavoriteData(entity)
    }
}