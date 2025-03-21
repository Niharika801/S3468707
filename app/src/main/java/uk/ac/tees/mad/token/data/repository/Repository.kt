package uk.ac.tees.mad.token.data.repository

import uk.ac.tees.mad.token.data.model.TokenData

interface Repository {
    suspend fun getTokenData():List<TokenData>
}