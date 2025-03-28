package uk.ac.tees.mad.token.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.token.R
import uk.ac.tees.mad.token.ui.theme.TokenTheme
import uk.ac.tees.mad.token.util.Constants

@Composable
fun DetailScreen(
) {
    val crypto = Constants.getData()
    val selectedCurrency by remember { mutableStateOf("usd") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        crypto.let { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
    //                    AsyncImage(
    //                        model = data.image.large,
    //                        contentDescription = data.name,
    //                        modifier = Modifier.size(100.dp)
    //                    )
                    Image(
                        painter = painterResource(R.drawable.splash_icon),
                        contentDescription = "Image",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${data.name} (${data.symbol.uppercase()})",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${data.market_data.current_price[selectedCurrency]?.let { price -> "$price $selectedCurrency".uppercase() }}",
                        fontSize = 20.sp,
                        color = Color(0xFFFFC107),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Market Cap: ${data.market_data.market_cap[selectedCurrency]?.let { cap -> "$cap $selectedCurrency".uppercase() }}",
                        color = Color(0xFFB0BEC5)
                    )
                    Text(
                        text = "24h Change: ${data.market_data.price_change_percentage_24h}%",
                        color = if (data.market_data.price_change_percentage_24h >= 0) Color.Green else Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (data.market_data.sparkline_in_7d.price.isNotEmpty()) {
                        CryptoSparklineChart(data.market_data.sparkline_in_7d.price)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Description", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = data.description.en, maxLines = 5, overflow = TextOverflow.Ellipsis, color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPrev() {
    TokenTheme {
        DetailScreen()
    }
}
