package uk.ac.tees.mad.token.util

import uk.ac.tees.mad.token.data.model.CryptoDetailDto
import uk.ac.tees.mad.token.data.model.Description
import uk.ac.tees.mad.token.data.model.ImageUrls
import uk.ac.tees.mad.token.data.model.MarketData
import uk.ac.tees.mad.token.data.model.SparklineData

object Constants {

    fun getData():CryptoDetailDto{
        val sampleCryptoDetail = CryptoDetailDto(
            id = "bitcoin",
            name = "Bitcoin",
            symbol = "BTC",
            image = ImageUrls(
                large = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png"
            ),
            market_data = MarketData(
                current_price = mapOf("usd" to 45000.0, "eur" to 41000.0),
                market_cap = mapOf("usd" to 800_000_000_000.0, "eur" to 730_000_000_000.0),
                price_change_percentage_24h = 2.5,
                sparkline_in_7d = SparklineData(
                    price = listOf(44000.0, 44500.0, 45000.0, 45500.0, 46000.0)
                )
            ),
            description = Description(
                en = "Bitcoin is the first decentralized digital currency, created in 2009 by an anonymous entity known as Satoshi Nakamoto."
            )
        )

        return  sampleCryptoDetail

    }
}