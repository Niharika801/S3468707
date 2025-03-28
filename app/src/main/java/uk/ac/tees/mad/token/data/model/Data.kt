package uk.ac.tees.mad.token.data.model

data class CryptoDetailDto(
    val id: String,
    val name: String,
    val symbol: String,
    val image: ImageUrls,
    val market_data: MarketData,
    val description: Description
)

data class ImageUrls(val large: String)
data class MarketData(
    val current_price: Map<String, Double>,
    val market_cap: Map<String, Double>,
    val price_change_percentage_24h: Double,
    val sparkline_in_7d: SparklineData
)
data class SparklineData(val price: List<Double>)
data class Description(val en: String)
