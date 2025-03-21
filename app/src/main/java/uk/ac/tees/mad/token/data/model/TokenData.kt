package uk.ac.tees.mad.token.data.model

import com.google.gson.annotations.SerializedName

data class TokenData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("image") val image:String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("price_change_percentage_24h") val priceChange: Double,
    @SerializedName("market_cap") val marketCap: Long,
    @SerializedName("total_volume") val volume: Long,
)
