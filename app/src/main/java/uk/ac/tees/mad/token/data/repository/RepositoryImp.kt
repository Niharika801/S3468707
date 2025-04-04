package uk.ac.tees.mad.token.data.repository

import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.model.TokenData
import uk.ac.tees.mad.token.data.remote.CryptoApiService
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val api:CryptoApiService
) :Repository{
    override suspend fun getTokenData(): List<TokenData> {
        return api.getCryptoData()
    }

    override suspend fun getTokenDetails(id:String): DetailData {
        return api.getCryptoDetails(
            id = id
        )
    }
}