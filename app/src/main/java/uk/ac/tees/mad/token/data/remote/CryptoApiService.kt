package uk.ac.tees.mad.token.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.model.TokenData

interface CryptoApiService {
    @GET("coins/markets")
    suspend fun getCryptoData(
        @Query("vs_currency") currency: String,
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<TokenData>

    @GET("coins/{id}")
    suspend fun getCryptoDetails(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("sparkline") sparkline: Boolean = true,
        @Query("vs_currency") currency:String
    ): DetailData
}